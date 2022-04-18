package ru.hse.sipmlews.dbwork;

import ru.simplews.my.DataRequest;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Order extends DataRequest {

    // инстанс класса подключения к БД
    static DBConnector dbConnector;

    static {
        try {
            dbConnector = DBConnector.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }
    // костыль! как-то неправильно работает грегорианский календарь из xml
    private String birthDateSTR;

    public String getBirthDateSTR() {
        return birthDateSTR;
    }



    // Конструктор
    public Order(String firstName, String familyName, String patronymic,
                 XMLGregorianCalendar birthDate, String email, String product ,String comment) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.birthDate = birthDate;
        this.birthDateSTR = birthDate.toString();
        this.email = email;
        this.comment = comment;
        this.uuid = UUID.randomUUID();
        this.patronymic = patronymic;
        this.product = product;
    }

    // Добавление продукта в БД
    public void addOrder() throws SQLException {
        // Создадим подготовленное выражение для инсерта
        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(
                "INSERT INTO Orders(`firstName`, `familyName`, `patronymic`, `birthDate`," +
                        " `email`, `product`, `comment`, `uuid`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, this.getFirstName());
            statement.setObject(2, this.getFamilyName());
            statement.setObject(3, this.getPatronymic());
            statement.setObject(4, this.getBirthDateSTR());
            statement.setObject(5, this.getEmail());
            statement.setObject(6, this.getProduct());
            statement.setObject(7, this.getComment());
            statement.setObject(8, this.getUuid().toString());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }


    public String toString() {
        return String.format("UUID: %s | Имя: %s | Фамилия: %s | Отчество: %s | ДатаРождения: %s | Емейл: %s | Комментарий: %s",
                this.uuid.toString(), this.firstName, this.familyName, this.patronymic, this.birthDateSTR, this.email, this.comment);
    }
}
