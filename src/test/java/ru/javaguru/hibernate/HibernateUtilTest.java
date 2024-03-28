package ru.javaguru.hibernate;

public class HibernateUtilTest {
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