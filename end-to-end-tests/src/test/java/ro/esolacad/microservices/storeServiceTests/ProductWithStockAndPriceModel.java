package ro.esolacad.microservices.storeServiceTests;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductWithStockAndPriceModel {
    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String categoryName;
    private Long categoryId;
}
