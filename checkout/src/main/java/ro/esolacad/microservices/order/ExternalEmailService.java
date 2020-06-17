package ro.esolacad.microservices.order;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ro.esolacad.microservices.externalclients.EmailClient;

@Service
@RequiredArgsConstructor
public class ExternalEmailService implements EmailService{

    private final EmailClient emailClient;

    @Override
    public Boolean sendEmail(final OrderModel orderModel) {
        return emailClient.sendOrderEmail(orderModel).getBody();
    }
}
