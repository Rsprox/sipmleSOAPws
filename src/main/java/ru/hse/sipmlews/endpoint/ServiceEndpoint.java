package ru.hse.sipmlews.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.hse.sipmlews.service.SaveOrderService;
import ru.simplews.my.DataRequest;
import ru.simplews.my.DataResponse;

/*
    конфигурируем эндпоинт
 */
@Endpoint // регистрирует класс как кандидата на обработку входящих сообщений
public class ServiceEndpoint {
    public static final String TARGET_NAMESPACE="http://my.simplews.ru";

    // инстанс сервиса
    @Autowired
    private SaveOrderService service;

    // вызывает метод сервиса и возвращает ответ
    // берётся из класса запроса
    @PayloadRoot(namespace = TARGET_NAMESPACE, localPart = "DataRequest") // вызов метода обработчика по namespace и localPart
    @ResponsePayload // маппит возвращаемое значение в payload ответа
    public DataResponse saveOrder(@RequestPayload DataRequest request){
        return service.saveAdnResponse(request);
    }
}
