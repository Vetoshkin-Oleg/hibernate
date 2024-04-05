package ru.javaguru.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findUserByName", query = """
        select u from User u
                where u.personalInfo.firstName = :firstname
                """)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"company", "profile", "userChats"})
@EqualsAndHashCode(of = "userName")
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User implements Comparable<User>, BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Embedded
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<UserChat> payments = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return userName.compareTo(o.userName);
    }

    public String fullName() {
        return getPersonalInfo().getFirstName() + " " + getPersonalInfo().getLastName();
    }
}