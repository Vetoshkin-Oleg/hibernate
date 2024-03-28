package ru.javaguru.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javaguru.hibernate.converter.BirthdayConverter;
import ru.javaguru.hibernate.entity.Birthday;
import ru.javaguru.hibernate.entity.Role;
import ru.javaguru.hibernate.entity.User;

import java.time.LocalDate;
import java.time.Month;

public class HibernateUtil {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .userName("Ivan@mail.com")
                    .firstName("Ivan")
                    .lastName("Petrov")
                    .birthDate(new Birthday(
                            LocalDate.of(2000, Month.DECEMBER, 21)))
                    .role(Role.ADMIN)
                    .build();

            session.saveOrUpdate(user);
            /*session.save(user);
            session.update(user);
            session.delete(user);*/

            User user1 = session.get(User.class, "Ivan@mail.com");
            System.out.print("**************************************************");
            System.out.print("**************************************************");
            System.out.println("**************************************************");
            System.out.println(user1);
            System.out.print("**************************************************");
            System.out.print("**************************************************");
            System.out.println("**************************************************");
            session.getTransaction().commit();
        }
    }
}