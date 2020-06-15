package ro.esolacad.microservices.stockandprice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockChangeModel {

    private String productCode;
    private Integer quantityToChange;
}
