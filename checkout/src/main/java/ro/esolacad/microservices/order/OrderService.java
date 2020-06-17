package ro.esolacad.microservices.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.messaging.OrderMessagingGateway;
import ro.esolacad.microservices.order.saga.OrderPaymentApprovalModel;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ShopOrderRepository shopOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMessagingGateway orderMessagingGateway;
    private final OrderSagaRepository orderSagaRepository;
    private final EmailService emailService;

    public void createOrderAndSendEmailSync(OrderModel orderModel) {
        saveOrder(orderModel);
        boolean emailSent = emailService.sendEmail(orderModel);

        log.info("Was the email send? : " + emailSent);
    }

    public void createOrderAndSendEmailAsync(OrderModel orderModel) {
        saveOrder(orderModel);
        emailService.sendEmail(orderModel);
    }

    public void createOrderWithChoreography(OrderModel orderModel) {
        saveOrder(orderModel);
        orderMessagingGateway.sendOrder(orderModel);

        boolean emailSent = emailService.sendEmail(orderModel);

        log.info("Was the email send? : " + emailSent);
    }

    public void createOrderWithOrchestration(OrderModel orderModel) {
        ShopOrder shopOrder = saveOrder(orderModel);

        OrderSaga orderSaga = OrderSaga.builder()
                .shopOrder(shopOrder)
                .step(OrderSaga.Step.APPROVE_PAYMENT)
                .build();

        orderSagaRepository.save(orderSaga);

//        orderMessagingGateway.sendOrder(orderModel);
        BigDecimal sumOfOrderItems = orderModel.getOrderItems()
                .stream()
                .map(orderItemModel -> orderItemModel.getTotalLineValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderPaymentApprovalModel paymentApprovalModel = OrderPaymentApprovalModel.builder()
                .orderId(orderModel.getId())
                .clientCode(orderModel.getClientCode())
                .totalOrderValue(sumOfOrderItems)
                .build();


        orderMessagingGateway.sendApprovePaymentForOrder(paymentApprovalModel);
    }

    private ShopOrder saveOrder(final OrderModel orderModel) {
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
        orderModel.setState(shopOrder.getState());

        return shopOrder;
    }
}
