package ro.esolacad.microservices.messaging.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

//    @StreamListener("orderChannel")
    public void consumerOrder() {

        // Listening to PAYMENT_APPORVED orders
        // Check if product in stock
        // Send stock of product
    }
}
