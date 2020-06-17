package ro.esolacad.microservices.messaging.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentClientInformation {

    private String clientCode;
    private PaymentStatus status;
    private Long orderId;
}
