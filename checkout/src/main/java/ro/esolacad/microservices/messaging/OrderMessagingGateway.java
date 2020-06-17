package ro.esolacad.microservices.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ro.esolacad.microservices.order.OrderModel;
import ro.esolacad.microservices.order.saga.OrderPaymentApprovalModel;

@MessagingGateway
public interface OrderMessagingGateway {

    @Gateway(requestChannel = "orderChannel")
    void sendOrder(OrderModel orderModel);

    @Gateway(requestChannel = "approvePaymentForOrder")
    void sendApprovePaymentForOrder(OrderPaymentApprovalModel orderPaymentApprovalModel);

}
