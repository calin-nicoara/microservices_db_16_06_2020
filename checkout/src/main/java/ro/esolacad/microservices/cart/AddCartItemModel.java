package ro.esolacad.microservices.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemModel {

    private String productCode;
    private Integer quantityToAdd;
}
