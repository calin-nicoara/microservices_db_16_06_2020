package ro.esolacad.microservices.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.kafka.support.KafkaHeaders;

import ro.esolacad.microservices.order.OrderModel;
import ro.esolacad.microservices.order.saga.OrderPaymentApprovalModel;

@MessagingGateway
public interface OrderMessagingGateway {

    @Gateway(requestChannel = "orderChannel",
        headers = {@GatewayHeader(name = KafkaHeaders.MESSAGE_KEY, expression = "#args[0].id")})
    void sendOrder(OrderModel orderModel);

    @Gateway(requestChannel = "approvePaymentForOrder")
    void sendApprovePaymentForOrder(OrderPaymentApprovalModel orderPaymentApprovalModel);

}
