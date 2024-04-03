package ru.javaguru.hibernate;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import ru.javaguru.hibernate.entity.*;
import ru.javaguru.hibernate.util.HibernateUtil;

import java.time.Instant;

public class HibernateRunnerTest {
    @Test
    public void checkInheritance() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = Company.builder()
                .name("Yandex")
                .build();
        session.save(company);

        var programmer = Programmer.builder()
                .userName("Ivan@gmail.com")
                .language(Language.JAVA)
                .company(company)
                .build();
        session.save(programmer);

        var manager = Manager.builder()
                .userName("Anna@gmail.com")
                .project("JAVA Enterprise")
                .company(company)
                .build();
        session.save(manager);

        session.flush();
        session.clear();

        var programmer1 = session.get(Programmer.class, 1L);
        var manager1 = session.get(User.class, 2L);

        session.getTransaction().commit();
    }
}