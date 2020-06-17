package ro.esolacad.microservices.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaga {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ShopOrder shopOrder;

    private Step step;

    public enum Step {
        APPROVE_PAYMENT, RESERVE_STOCK, SEND_DELIVERY, COMPLETE_ORDER, CANCELLED
    }
}
