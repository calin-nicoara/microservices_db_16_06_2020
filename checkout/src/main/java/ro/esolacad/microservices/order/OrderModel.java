package ro.esolacad.microservices.order;

import java.util.List;

import javax.persistence.Entity;

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
    private ShopOrder.State state;

    private List<OrderItemModel> orderItems;
}
