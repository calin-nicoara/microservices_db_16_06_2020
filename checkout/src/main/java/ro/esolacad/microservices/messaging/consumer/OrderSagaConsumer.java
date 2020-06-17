package ro.esolacad.microservices.messaging.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.order.OrderSaga;
import ro.esolacad.microservices.order.OrderSagaRepository;
import ro.esolacad.microservices.order.ShopOrder;
import ro.esolacad.microservices.order.ShopOrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderSagaConsumer {

    private final ShopOrderRepository shopOrderRepository;
    private final OrderSagaRepository orderSagaRepository;

    @StreamListener("paymentChannel")
    public void consumePayment(Message<PaymentClientInformation> paymentClientInformationMessage) {
        log.info("Payment recevied for saga " + paymentClientInformationMessage);

        PaymentClientInformation paymentInfo = paymentClientInformationMessage.getPayload();

        Optional<OrderSaga> orderSageOptional = orderSagaRepository.findByShopOrderIdAndStep(
                paymentInfo.getOrderId(),
                OrderSaga.Step.APPROVE_PAYMENT);

        if(orderSageOptional.isPresent()) {
            OrderSaga orderSaga = orderSageOptional.get();

            ShopOrder shopOrder = orderSaga.getShopOrder();
            if(paymentInfo.getStatus().equals(PaymentStatus.DENIED)) {
                shopOrder.setState(ShopOrder.State.CANCELED);
                orderSaga.setStep(OrderSaga.Step.CANCELLED);
            } else {
                shopOrder.setState(ShopOrder.State.PAYMENT_APPROVED);
                orderSaga.setStep(OrderSaga.Step.RESERVE_STOCK);
            }

            shopOrderRepository.save(shopOrder);
            orderSagaRepository.save(orderSaga);

            log.info("Current saga " + orderSaga);

            if(orderSaga.getStep().equals(OrderSaga.Step.RESERVE_STOCK)) {

            }
        }

    }
}
