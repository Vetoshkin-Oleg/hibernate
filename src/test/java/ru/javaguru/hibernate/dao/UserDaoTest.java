package ru.javaguru.hibernate.dao;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.javaguru.hibernate.entity.User;
import ru.javaguru.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoTest {
    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    /*private final UserDao userDao = UserDao.getInstance();*/

    /*@BeforeAll
    public void initDB() {
        TestDataImporter.importData(sessionFactory);
    }*/

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        /*List<User> results = userDao.findAll(session);
        assertThat(results).hasSize(5);*/

        /*List<String> fullNames = results.stream().map(User::fullName).collect(Collectors.toList());
        assertThat(fullNames).containsExactlyInAnyOrder("Bill Gates", "Steve Jobs",
                "Sergey Brin", "Tim Cook", "Diane Grande");*/

        session.getTransaction().commit();
    }

    @Test
    void findAllByFirstName() {
    }
}