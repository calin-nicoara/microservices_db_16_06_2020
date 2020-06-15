package ro.esolacad.microservices.stockandprice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock-and-price")
@RequiredArgsConstructor
public class ProductStockAndPriceResource {

    private final ProductStockAndPriceService productStockAndPriceService;

    @GetMapping
    public List<ProductStockAndPrice> findAllProductStockAndPrices() {
        return productStockAndPriceService.findAllProductStockAndPrices();
    }

    @GetMapping(value = "/{productCode}")
    public ResponseEntity<ProductStockAndPrice> findByCode(@PathVariable("productCode") final String productCode) {
        Optional<ProductStockAndPrice> byCode = productStockAndPriceService.findByCode(productCode);

        if(byCode.isPresent()) {
            return ResponseEntity.ok(byCode.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void modifyStock(@RequestBody StockChangeModel stockChangeModel) {
        productStockAndPriceService.changeStock(stockChangeModel);
    }
}
