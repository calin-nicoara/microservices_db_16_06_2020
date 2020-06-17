package ro.esolacad.microservices.email;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.messaging.consumer.OrderModel;

@Slf4j
@Service
public class EmailService {

    public boolean sendEmail(OrderModel orderModel) {
        log.info("Send email for order from email service" + orderModel);

        return true;
    }
}
