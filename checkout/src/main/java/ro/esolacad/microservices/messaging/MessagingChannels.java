package ro.esolacad.microservices.messaging;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessagingChannels {

    @Output
    MessageChannel orderChannel();

    @Output
    MessageChannel approvePaymentForOrder();

    @Input
    SubscribableChannel paymentChannel();
}
