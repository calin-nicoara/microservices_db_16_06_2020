package ro.esolacad.microservices.externalclients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import feign.RetryableException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InventoryFeignFallbackFactory implements FallbackFactory<InventoryFeignClient> {
    @Override
    public InventoryFeignClient create(final Throwable throwable) {
        log.error("Fallback factory is used!", throwable);
        return new InventoryFeignClient() {
            @Override
            public ResponseEntity<StockAndPriceModel> findStockAndPriceByCode(final String productCode) {
                return ResponseEntity.ok(StockAndPriceModel.builder()
                        .price(BigDecimal.ZERO)
                        .stock(0)
                        .build());
            }
        };
    }
}
