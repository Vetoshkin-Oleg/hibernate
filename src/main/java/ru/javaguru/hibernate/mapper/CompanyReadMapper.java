package ru.javaguru.hibernate.mapper;

import ru.javaguru.hibernate.dto.CompanyReadDto;
import ru.javaguru.hibernate.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(object.getId(), object.getName());
    }
}