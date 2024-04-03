package ru.javaguru.hibernate.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Programmer extends User {
    private Language language;

    @Builder
    public Programmer(Long id, String userName, PersonalInfo personalInfo, Role role, Company company,
                      Profile profile, List<UserChat> userChats, Language language) {
        super(id, userName, personalInfo, role, company, profile, userChats);
        this.language = language;
    }
}