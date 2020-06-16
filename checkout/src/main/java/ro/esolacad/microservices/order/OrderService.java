package ro.esolacad.microservices.order;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import ro.esolacad.microservices.messaging.OrderMessagingGateway;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ShopOrderRepository shopOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMessagingGateway orderMessagingGateway;

    public void createOrder(OrderModel orderModel) {
        ShopOrder shopOrder = ShopOrder.builder()
                .clientCode(orderModel.getClientCode())
                .state(ShopOrder.State.PENDING)
                .build();

        ShopOrder saveShopOrder = shopOrderRepository.save(shopOrder);

        List<OrderItem> orderItems = orderModel.getOrderItems()
                .stream()
                .map(orderItemModel -> {
                    return OrderItem.builder()
                            .productCode(orderItemModel.getProductCode())
                            .quantity(orderItemModel.getQuantity())
                            .totalLineValue(orderItemModel.getTotalLineValue())
                            .shopOrder(saveShopOrder)
                            .build();
                })
                .collect(Collectors.toList());

        List<OrderItem> saveOrderItems = orderItemRepository.saveAll(orderItems);

        System.out.println(saveOrderItems);
        System.out.println(shopOrder);

        orderModel.setId(shopOrder.getId());
        orderMessagingGateway.sendOrder(orderModel);
    }
}
