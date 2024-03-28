package ru.javaguru.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javaguru.hibernate.converter.BirthdayConverter;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        return configuration.buildSessionFactory();
    }
}