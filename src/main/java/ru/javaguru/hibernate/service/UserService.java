package ru.javaguru.hibernate.service;

import lombok.RequiredArgsConstructor;
import ru.javaguru.hibernate.dao.UserRepository;
import ru.javaguru.hibernate.dto.UserCreateDto;
import ru.javaguru.hibernate.dto.UserReadDto;
import ru.javaguru.hibernate.mapper.UserCreateMapper;
import ru.javaguru.hibernate.mapper.UserReadMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    public Long create(UserCreateDto userCreateDto) {
        var userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
}