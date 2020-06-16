package ro.esolacad.microservices.messaging.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.order.ShopOrder;
import ro.esolacad.microservices.order.ShopOrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final ShopOrderRepository shopOrderRepository;

//    @StreamListener("paymentChannel")
    public void consumePaymentInfoForChoreography(Message<PaymentClientInformation> paymentClientInformationMessage) {
        PaymentClientInformation paymentInfo = paymentClientInformationMessage.getPayload();

        shopOrderRepository.findById(paymentInfo.getOrderId())
                .ifPresent(shopOrder -> {
                    if(paymentInfo.getStatus().equals(PaymentStatus.DENIED)) {
                        shopOrder.setState(ShopOrder.State.CANCELED);
                    } else {
                        shopOrder.setState(ShopOrder.State.PAYMENT_APPROVED);
                    }

                    shopOrderRepository.save(shopOrder);

                    log.info("Order after payment:" + shopOrder);
                });
    }
}
