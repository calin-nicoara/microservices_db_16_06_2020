package ro.esolacad.microservices.order;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String clientCode;

    private State state;

    public enum State {
        PENDING, PAYMENT_APPROVED, PRODUCTS_IN_STOCK, CANCELED
    }
}
