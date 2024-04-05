package ru.javaguru.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate birthDay) implements Serializable {
    public long getAge() {
        return ChronoUnit.YEARS.between(birthDay, LocalDate.now());
    }
}