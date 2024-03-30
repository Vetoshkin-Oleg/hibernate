package ru.javaguru.hibernate;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import ru.javaguru.hibernate.entity.Company;
import ru.javaguru.hibernate.entity.User;
import ru.javaguru.hibernate.util.HibernateUtil;

public class HibernateRunnerTest {

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

    /*@Test
    public void testHibernateApiVer1() throws SQLException, IllegalAccessException {
         User user = User.builder()
                .userName("Ivan2@mail.com")
                .firstName("Ivan2")
                .lastName("Petrov2")
                .birthDate(LocalDate.of(2000, Month.DECEMBER, 23))
                .age(23)
                .build();

         String sql = """
                 insert into
                 %s
                 (%s)
                 values
                 (%s)
                 """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        String columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class)).map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(","));

        String columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(","));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "password");

        PreparedStatement preparedStatement = connection
                .prepareStatement(sql.formatted(tableName, columnNames, columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            preparedStatement.setObject(i + 1, fields[i].get(user));
        }

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }*/
}