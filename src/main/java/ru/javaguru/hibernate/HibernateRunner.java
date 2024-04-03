package ru.javaguru.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javaguru.hibernate.entity.*;
import ru.javaguru.hibernate.util.HibernateUtil;

import java.time.LocalDate;
import java.time.Month;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        Company company1 = Company.builder()
                .name("Google")
                .build();

        Company company2 = Company.builder()
                .name("Yandex")
                .build();

        User user1 = User.builder()
                .userName("Ivan@mail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Ivan")
                        .lastName("Petrov")
                        .birthDate(new Birthday(
                                LocalDate.of(2000, Month.DECEMBER, 21)))
                        .build())
                .role(Role.ADMIN)
                .company(company1)
                .build();

        User user2 = User.builder()
                .userName("Petr@mail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Petr")
                        .lastName("Ivanov")
                        .birthDate(new Birthday(
                                LocalDate.of(1999, Month.NOVEMBER, 22)))
                        .build())
                .role(Role.USER)
                .company(company2)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(company1);
                session1.saveOrUpdate(company2);

                session1.saveOrUpdate(user1);
                session1.saveOrUpdate(user2);

                session1.getTransaction().commit();
            }
        }
    }
}