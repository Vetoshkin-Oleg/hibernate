package ru.javaguru.hibernate.mapper;

import lombok.RequiredArgsConstructor;
import ru.javaguru.hibernate.dto.UserReadDto;
import ru.javaguru.hibernate.entity.User;

@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {
    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User object) {
        return new UserReadDto(object.getId(), object.getPersonalInfo(),
                object.getUserName(), object.getRole(),
                companyReadMapper.mapFrom(object.getCompany()));
    }
}