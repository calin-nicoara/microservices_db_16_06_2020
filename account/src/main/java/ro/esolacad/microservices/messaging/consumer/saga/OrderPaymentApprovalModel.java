package ro.esolacad.microservices.messaging.consumer.saga;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentApprovalModel {

    private Long orderId;
    private String clientCode;
    private BigDecimal totalOrderValue;
}
