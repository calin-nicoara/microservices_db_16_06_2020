package ro.esolacad.microservices.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderSagaRepository extends JpaRepository<OrderSaga, Long> {

    Optional<OrderSaga> findByShopOrderIdAndStep(Long orderId, OrderSaga.Step step);
}
