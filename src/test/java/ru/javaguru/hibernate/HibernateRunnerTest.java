package ru.javaguru.hibernate;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import ru.javaguru.hibernate.entity.*;
import ru.javaguru.hibernate.util.HibernateUtil;

import java.time.Instant;

public class HibernateRunnerTest {
    @Test
    public void checkH2() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = Company.builder()
                .name("Mail")
                .build();
        session.save(company);
        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = Chat.builder()
                .name("javaguru")
                .build();
        session.save(chat);

        User user = session.get(User.class, 2L);

        UserChat userChat = UserChat.builder()
                .createdAt(Instant.now())
                .createdBy("Darth Vader")
                .build();
        userChat.setChat(chat);
        userChat.setUser(user);
        session.save(userChat);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder()
                .userName("Anna@mail.com")
                .build();

        Profile profile = Profile.builder()
                .language("RU")
                .street("Pobedy")
                .build();

        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphanRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        /*Создаем нового пользователя и трудоустраиваем его в существующую организацию.*/
        Company company3 = session.get(Company.class, 1);
        User user3 = User.builder()
                .userName("Anna@mail.com")
                .build();
        company3.addUser(user3);
        session.saveOrUpdate(company3);
        /*После этого шага в базе данных есть две организации и три пользователя.*/

        /*При первом запуске теста эту строку закомментировать,
        чтобы в БД стало три сотрудника и две организации.
        Перед вторым запуском теста посмотреть, какие пользователи, организации и ID есть в БД.
        Соответствующее значение поставить в тест.
        Убедиться, что тип переменной id класса User и тип проверочного значения в тесте совпадают.
        Перед вторым запуском теста убрать каскадное удаление в классе User*/
        company3.getUsers().removeIf(user -> user.getId().equals(3L));

        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company3 = Company.builder()
                .name("Mail")
                .build();

        User user3 = User.builder()
                .userName("Anna@mail.com")
                .build();

        company3.addUser(user3);

        session.save(company3);
        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getName());
        /*НЕ забыть написать строку
         * @ToString(exclude = "company")
         * в классе User для успешного прохождения теста. Объяснение этому на 27й минуте видео.*/
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }
}