package ru.hse.sipmlews.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.hse.sipmlews.service.SaveOrderService;
import ru.simplews.my.DataRequest;
import ru.simplews.my.DataResponse;

import java.sql.SQLException;

/*
    конфигурируем эндпоинт
 */
@Endpoint
public class ServiceEndpoint {
    public static final String TARGET_NAMESPACE="http://my.simplews.ru";

    // наш сервис
    @Autowired
    private SaveOrderService service;

    // вызывает метод сервиса и возвращает ответ
    // берётся из класса запроса
    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "DataRequest")
    @ResponsePayload
    public DataResponse saveOrder(@RequestPayload DataRequest request) throws SQLException {
        return service.saveAdnResponse(request);
    }
}