package ru.javaguru.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javaguru.hibernate.entity.Birthday;
import ru.javaguru.hibernate.entity.PersonalInfo;
import ru.javaguru.hibernate.entity.Role;
import ru.javaguru.hibernate.entity.User;
import ru.javaguru.hibernate.util.HibernateUtil;

import java.time.LocalDate;
import java.time.Month;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        User user = User.builder()
                .userName("Ivan1@mail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Ivan")
                        .lastName("Petrov")
                        .birthDate(new Birthday(
                                LocalDate.of(2000, Month.DECEMBER, 21)))
                        .build())
                .role(Role.ADMIN)
                .build();
        System.out.println("************************************************");
        log.info("User object is transient state: {} ", user);
        System.out.println("************************************************");
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.saveOrUpdate(user);

                /*user.setFirstName("Anna");
                System.out.println("************************************************");
                log.warn("User firstName is changed: {} ", user);
                System.out.println(user);
                System.out.println("************************************************");*/

                log.debug("User: {}, session: {} ", user, session1);
                session1.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            throw e;
        }
    }
}