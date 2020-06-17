package ro.esolacad.microservices.externalclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;

@FeignClient(name = "inventory-service",
//        fallback = InventoryFeignFallback.class,
        fallbackFactory = InventoryFeignFallbackFactory.class
)
public interface InventoryFeignClient {

    @RequestMapping(value = "/stock-and-price/{productCode}", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StockAndPriceModel> findStockAndPriceByCode(
            @PathVariable("productCode") String productCode);
}
