package ru.javaguru.hibernate.dao;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import ru.javaguru.hibernate.dto.PaymentFilter;
import ru.javaguru.hibernate.entity.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

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

    /*
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     * */
    public Double findAveragePaymentAmountByFirstAndLastNames(Session session, PaymentFilter filter) {
        /*List<Predicate> predicates = new ArrayList<>();
        if (filter.getFirstName() != null) {
            predicates.add(user.pesonalInfo().firstname.eq(filter.getFirstname()));
        }
        if (filter.getLastName() != null) {
            predicates.add(user.pesonalInfo().lastname.eq(filter.getLastname()));
        }*/

        /*var predicate = QPredicate.builder()
                .add(filter.getFirstname(), user.pesonalInfo().firstname::eq)
                .add(filter.getLastname(), user.pesonalInfo().lastname::eq).buildOr();*/

        /*return new JPAQuery<Double>(session)
                .select(payment.amount.avg()).from(payment)
                .join(payment.receiver(), user)
                .where(predicate).fetchOne();*/
        return null;
    }

    /*
     * Возвращает для каждой компании: название, среднюю зарплату всех её сотрудников.
     * Компании упорядочены по названию
     * */
    public List<Tuple> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(Session session) {
        /*return new JPAQuery<Tuple>(session)
                .select(company.name, payment.amount.avg())
                .from(company)
                .join(company.users, user)
                .join(user.payments, payment)
                .groupBy(company.name)
                .orderedBy(company.name.asc())
                .fetch();*/
        return null;
    }

    /*
     * Возвращает список: сотрудник (объект User), средний размер выплат, но только для тех сотрудников, чей.
     * больше среднего размеры выплат всех сотрудников.
     * Упорядочить по имени сотрудника.
     * */
    public List<Tuple> isItPossible(Session session) {
        /*return new JPAQuery<Tuple>(session)
                .select(user, payment.amount.avg())*/
        return null;
    }
}