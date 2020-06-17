package ro.esolacad.microservices.email;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.esolacad.microservices.messaging.consumer.OrderModel;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailResource {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Boolean> sendOrderEmail(@RequestBody OrderModel orderModel) {
        boolean emailWasSent = emailService.sendEmail(orderModel);

        return ResponseEntity.ok(emailWasSent);
    }
}
