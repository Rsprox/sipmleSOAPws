package ru.hse.sipmlews.service;

import org.springframework.stereotype.Service;
import ru.hse.sipmlews.dbwork.Order;
import ru.simplews.my.DataRequest;
import ru.simplews.my.DataResponse;


@Service
public class SaveOrderService {


    // логика обработки запроса
    public DataResponse saveAdnResponse(DataRequest request){
        DataResponse response = new DataResponse();
        Order order = new Order(request.getFirstName(), request.getFamilyName(), request.getPatronymic(),
                request.getBirthDate(), request.getEmail(), request.getPhoneNumber(),
                request.getProduct(), request.getComment());

        // инсертим запись в БД
        try {
            order.addOrder();
            response.setOrderID(order.getUuid().toString());
            response.setResult(order.getFirstName() + ", Привет! Заказ был сохранён!");
            response.setComment("Вы заказали - " + order.getProduct() +
                    ". Спасибо что выбрали нас! В ближайшее время мы свяжемся с вами.");
        } catch (Exception e) {
            response.setResult("Что-то пошло не так :(");
            response.setComment(e + " " + e.getMessage());
        }

        return response;
    }

}
