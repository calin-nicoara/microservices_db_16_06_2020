package ro.esolacad.microservices.externalclients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InventoryFeignFallback implements InventoryFeignClient{
    @Override
    public ResponseEntity<StockAndPriceModel> findStockAndPriceByCode(final String productCode) {
        log.warn("Fallback used for inventory service");
        return ResponseEntity.ok(StockAndPriceModel.builder()
                .price(BigDecimal.ZERO)
                .stock(0)
                .build());
    }
}
