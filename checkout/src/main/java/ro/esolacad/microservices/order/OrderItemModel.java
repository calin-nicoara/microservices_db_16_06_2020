package ro.esolacad.microservices.order;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {
    private Long id;
    private String productCode;
    private Integer quantity;
    private BigDecimal totalLineValue;
}
