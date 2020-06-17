package ro.esolacad.microservices.messaging.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.messaging.PaymentMessageGateway;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final PaymentMessageGateway paymentMessageGateway;

    @StreamListener("orderChannel")
    public void consumeOrder(Message<OrderModel> orderModelMessage) {
        log.info("Order received: " + orderModelMessage.getPayload());

        OrderModel orderModel = orderModelMessage.getPayload();

        if(orderModel.getState().equals(OrderState.PENDING)) {
            BigDecimal sumOfOrderItems = orderModel.getOrderItems()
                    .stream()
                    .map(orderItemModel -> orderItemModel.getTotalLineValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            PaymentStatus paymentStatus = sumOfOrderItems
                    .compareTo(BigDecimal.valueOf(50)) < 0
                    ? PaymentStatus.APPROVED
                    : PaymentStatus.DENIED;

            PaymentClientInformation paymentClientInformation = PaymentClientInformation.builder()
                    .clientCode(orderModel.getClientCode())
                    .orderId(orderModel.getId())
                    .status(paymentStatus)
                    .build();

            paymentMessageGateway.sendPayment(paymentClientInformation);
            log.info("Payment info sent:" + paymentClientInformation);
        }
    }
}
