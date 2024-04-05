package ru.javaguru.hibernate.dto;

import ru.javaguru.hibernate.entity.PersonalInfo;
import ru.javaguru.hibernate.entity.Role;

public record UserReadDto(
        Long id, PersonalInfo personalInfo, String username,
        Role role, CompanyReadDto company) {
}