package ro.esolacad.microservices.order;

public interface EmailService {
    Boolean sendEmail(OrderModel orderModel);
}
