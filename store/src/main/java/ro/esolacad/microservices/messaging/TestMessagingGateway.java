package ro.esolacad.microservices.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TestMessagingGateway {

    @Gateway(requestChannel = "testChannel")
    void sendMessage(String testMessage);
}
