package ro.esolacad.microservices.externalclients;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAndPriceModel {
    private String productCode;
    private BigDecimal price;
    private Integer stock;
}
