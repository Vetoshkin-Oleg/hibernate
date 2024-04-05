package ru.javaguru.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import ru.javaguru.hibernate.dao.CompanyRepository;
import ru.javaguru.hibernate.dto.UserCreateDto;
import ru.javaguru.hibernate.entity.User;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .personalInfo(object.personalInfo())
                .userName(object.username())
                .role(object.role())
                .company(companyRepository.findById(object.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}