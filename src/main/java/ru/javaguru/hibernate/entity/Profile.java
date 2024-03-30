package ru.javaguru.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Profile {
    /*НЕ забыть изменить файл hibernate.cfg.xml: прописать адрес класса Profile*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String street;
    private String language;

    public void setUser(User user) {
        this.user = user;
        user.setProfile(this);
    }
}