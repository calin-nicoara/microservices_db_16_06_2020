package ro.esolacad.microservices.messaging;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestMessageConsumer {

    @StreamListener("testChannel")
    public void consumeFromTestChannel(Message<String> stringMessage) {
        String payload = stringMessage.getPayload();

        log.info("Received payload:" + payload);
    }
}
