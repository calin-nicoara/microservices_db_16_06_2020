package ro.esolacad.microservices.component;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import ro.esolacad.microservices.externalclients.InventoryFeignClient;
import ro.esolacad.microservices.externalclients.StockAndPriceModel;

@Primary
@Component
public class InventoryFeignClientStub implements InventoryFeignClient {

    @Override
    public ResponseEntity<StockAndPriceModel> findStockAndPriceByCode(final String productCode) {
        System.out.println("Stub is used");
        return ResponseEntity.ok(StockAndPriceModel.builder()
                .stock(2)
                .price(BigDecimal.ONE)
                .build());
    }
}
