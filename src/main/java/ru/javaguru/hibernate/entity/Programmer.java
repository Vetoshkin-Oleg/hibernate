package ru.javaguru.hibernate.entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Programmer extends User {
    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Long id, String userName, PersonalInfo personalInfo, Role role, Company company,
                      Profile profile, List<UserChat> userChats, Language language) {
        super(id, userName, personalInfo, role, company, profile, userChats);
        this.language = language;
    }
}