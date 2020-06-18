package ro.esolacad.microservices.stockandprice;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductStockAndPriceModel {
    private Long id;
    private String productCode;
    private BigDecimal price;
    private Integer stock;
}
