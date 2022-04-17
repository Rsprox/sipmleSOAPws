package ru.hse.sipmlews.service;

import org.springframework.stereotype.Service;
import ru.hse.sipmlews.dbwork.DBConnector;
import ru.hse.sipmlews.dbwork.Order;
import ru.simplews.my.DataRequest;
import ru.simplews.my.DataResponse;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class SaveOrderService {
    static DBConnector dbConnector;

    public SaveOrderService() throws SQLException {
        dbConnector = DBConnector.getInstance();
    }

    // логика обработки запроса
    public DataResponse saveAdnResponse(DataRequest request){
        DataResponse response = new DataResponse();
        System.out.println(request.getFirstName());
        Order order = new Order(request.getFirstName(), request.getFamilyName(), request.getPatronymic(),
                request.getBirthDate(), request.getEmail(), request.getComment());

        // инсертим запись в БД
        addProduct(order);

        response.setOrderID(order.getUuid().toString());
        response.setResult(order.getFirstName() + ", Привет! Заказл был сохранён!");
        response.setComment(order.getComment() + " Когда ж оно заработает((");

        return response;
    }
    // Добавление продукта в БД
    private void addProduct(Order order) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        //System.out.println("adding " + order.toString());
        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(
                "INSERT INTO Orders(`firstName`, `familyName`, `patronymic` , `birthDate` , `email`, `comment`, `uuid`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, order.getFirstName());
            statement.setObject(2, order.getFamilyName());
            statement.setObject(3, order.getPatronymic());
            statement.setObject(4, order.getBirthDateSTR());
            statement.setObject(5, order.getEmail());
            statement.setObject(6, order.getComment());
            statement.setObject(7, order.getUuid().toString());
            // Выполняем запрос
            //System.out.println(statement.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
