package ro.esolacad.microservices.order;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Service
public class NewEmailService implements EmailService {

    @StreamListener("sendOrderEmailIn")
    public Boolean sendEmail(OrderModel orderModel) {
        log.info("Send email for order" + orderModel);

        return true;
    }
}
