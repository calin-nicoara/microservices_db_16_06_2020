package ro.esolacad.microservices.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ro.esolacad.microservices.messaging.consumer.PaymentClientInformation;

@MessagingGateway
public interface PaymentMessageGateway {

    @Gateway(requestChannel = "paymentChannel")
    void sendPayment(PaymentClientInformation paymentClientInformation);
}
