package ro.esolacad.microservices.messaging.consumer.saga;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.messaging.PaymentMessageGateway;
import ro.esolacad.microservices.messaging.consumer.PaymentClientInformation;
import ro.esolacad.microservices.messaging.consumer.PaymentStatus;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderSageConsumer {

    private final PaymentMessageGateway paymentMessageGateway;

    @StreamListener("approvePaymentForOrder")
    public void approvePayment(OrderPaymentApprovalModel orderPaymentApprovalModel) {
        PaymentStatus paymentStatus = orderPaymentApprovalModel.getTotalOrderValue()
                .compareTo(BigDecimal.valueOf(50)) < 0
                ? PaymentStatus.APPROVED
                : PaymentStatus.DENIED;

        PaymentClientInformation paymentClientInformation = PaymentClientInformation.builder()
                .clientCode(orderPaymentApprovalModel.getClientCode())
                .orderId(orderPaymentApprovalModel.getOrderId())
                .status(paymentStatus)
                .build();

        paymentMessageGateway.sendPayment(paymentClientInformation);
        log.info("Payment info sent:" + paymentClientInformation);
    }
}
