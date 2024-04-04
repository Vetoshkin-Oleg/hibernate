package ru.javaguru.hibernate.dao;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import ru.javaguru.hibernate.entity.*;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    /*
    * Возвращает всех сотрудников
    * */
    public List<User> findAll(Session session) {
        return null;
    }

    /*
     * Возвращает всех сотрудников с указанным именем
    * */
    public List<User> findAllByFirstName(Session session, String firstName) {
        return null;
    }

    /*
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     * */
    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
        return null;
    }

    /*
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     * */
    public List<User> findAllPaymentsByCompanyName(Session session, int limit) {
        return null;
    }
}