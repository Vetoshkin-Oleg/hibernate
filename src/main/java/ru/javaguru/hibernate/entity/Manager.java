package ru.javaguru.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager extends User {
    private String project;

    @Builder
    public Manager(Long id, String userName, PersonalInfo personalInfo, Role role, Company company,
                   Profile profile, List<UserChat> userChats, String project) {
        super(id, userName, personalInfo, role, company, profile, userChats);
        this.project = project;
    }
}