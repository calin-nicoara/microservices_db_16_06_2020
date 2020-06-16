package ro.esolacad.microservices.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ro.esolacad.microservices.order.OrderModel;

@MessagingGateway
public interface OrderMessagingGateway {

    @Gateway(requestChannel = "orderChannel")
    void sendOrder(OrderModel orderModel);
}
