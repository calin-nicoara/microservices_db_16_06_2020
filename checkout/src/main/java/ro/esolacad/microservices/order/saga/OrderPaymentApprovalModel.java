package ro.esolacad.microservices.order.saga;

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
