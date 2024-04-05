package ru.javaguru.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import ru.javaguru.hibernate.entity.*;
import ru.javaguru.hibernate.util.HibernateUtil;
import ru.javaguru.hibernate.util.TestDataImporter;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Session session1 = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            session1.beginTransaction();

            var payment = session.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            payment.setAmount(payment.getAmount() + 10);

            var samePayment = session.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            samePayment.setAmount(payment.getAmount() + 20);

            session.getTransaction().commit();
            session1.getTransaction().commit();
        }
    }
}