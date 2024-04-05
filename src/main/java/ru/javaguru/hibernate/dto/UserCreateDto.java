package ru.javaguru.hibernate.dto;

import ru.javaguru.hibernate.entity.PersonalInfo;
import ru.javaguru.hibernate.entity.Role;

public record UserCreateDto(PersonalInfo personalInfo, String username,
                            Role role, Integer companyId) {
}