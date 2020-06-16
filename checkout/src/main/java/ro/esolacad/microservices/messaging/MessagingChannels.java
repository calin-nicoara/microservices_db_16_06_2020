package ro.esolacad.microservices.messaging;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessagingChannels {

    @Output
    MessageChannel orderChannel();
}
