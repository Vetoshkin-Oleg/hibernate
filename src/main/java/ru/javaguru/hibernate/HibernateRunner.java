package ru.javaguru.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import ru.javaguru.hibernate.entity.*;
import ru.javaguru.hibernate.util.HibernateUtil;
import ru.javaguru.hibernate.util.TestDataImporter;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                TestDataImporter.importData(sessionFactory);
                session.beginTransaction();

                var userGraph = session.createEntityGraph(User.class);
                userGraph.addAttributeNodes("company", "userChats");
                var userChatsSubgraph = userGraph.addSubgraph("userChats", UserChat.class);
                userChatsSubgraph.addAttributeNodes("chat");

                Map<String, Object> properties = Map.of(
//                        GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat")
                        GraphSemantic.LOAD.getJpaHintName(), userGraph
                );
                var user = session.find(User.class, 1L, properties);
                System.out.println(user.getCompany().getName());
                System.out.println(user.getUserChats().size());

                var users = session.createQuery(
                                "select u from User u", User.class)
                        .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
                        .list();

                session.getTransaction().commit();
            }
        }
    }
}