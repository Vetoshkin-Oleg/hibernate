package ru.javaguru.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javaguru.hibernate.dao.CompanyRepository;
import ru.javaguru.hibernate.dao.UserRepository;
import ru.javaguru.hibernate.dto.UserCreateDto;
import ru.javaguru.hibernate.entity.Birthday;
import ru.javaguru.hibernate.entity.PersonalInfo;
import ru.javaguru.hibernate.entity.Role;
import ru.javaguru.hibernate.mapper.CompanyReadMapper;
import ru.javaguru.hibernate.mapper.UserCreateMapper;
import ru.javaguru.hibernate.mapper.UserReadMapper;
import ru.javaguru.hibernate.service.UserService;
import ru.javaguru.hibernate.util.HibernateUtil;

import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.time.Month;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);

            var userRepository = new UserRepository(session);
            var companyRepository = new CompanyRepository(session);
            var userCreateMapper = new UserCreateMapper(companyRepository);
            var userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findById(1L).ifPresent(System.out::println);

            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstName("Anna")
                            .lastName("Petrova")
                            .birthDate(new Birthday(LocalDate.of(2000, Month.DECEMBER, 21)))
                            .build(),
                    "Anna@gmail.com",
                    Role.USER,
                    1
            );
            System.out.println(userService.create(userCreateDto));

            session.getTransaction().commit();
        }
    }
}