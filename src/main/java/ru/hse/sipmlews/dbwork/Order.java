package ru.hse.sipmlews.dbwork;

import ru.simplews.my.DataRequest;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.UUID;

public class Order extends DataRequest {
    public UUID getUuid() {
        return uuid;
    }

    private UUID uuid;
    // костыль! как-то неправильно работает грегорианский календарь из xml
    private String birthDateSTR;

    public String getBirthDateSTR() {
        return birthDateSTR;
    }



    // Конструктор
    public Order(String firstName, String familyName, String patronymic, XMLGregorianCalendar birthDate, String email, String comment) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.birthDate = birthDate;
        this.birthDateSTR = birthDate.toString();
        this.email = email;
        this.comment = comment;
        this.uuid = UUID.randomUUID();
        this.patronymic = patronymic;
    }


    public String toString() {

        return String.format("UUID: %s | Имя: %s | Фамилия: %s | Отчество: %s | ДатаРождения: %s | Емейл: %s | Комментарий: %s",
                this.uuid.toString(), this.firstName, this.familyName, this.patronymic, this.birthDateSTR, this.email, this.comment);
    }
}
