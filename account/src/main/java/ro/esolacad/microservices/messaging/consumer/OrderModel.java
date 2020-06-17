package ro.esolacad.microservices.messaging.consumer;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private Long id;
    private String clientCode;
    private OrderState state;

    private List<OrderItemModel> orderItems;
}
