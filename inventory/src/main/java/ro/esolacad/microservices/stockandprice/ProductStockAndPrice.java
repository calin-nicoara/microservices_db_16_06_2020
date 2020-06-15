package ro.esolacad.microservices.stockandprice;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductStockAndPrice {

    @Id
    @GeneratedValue
    private Long id;

    private String productCode;
    private BigDecimal price;
    private Integer stock;
}
