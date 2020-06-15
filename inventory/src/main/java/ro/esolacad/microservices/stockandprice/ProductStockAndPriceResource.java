package ro.esolacad.microservices.stockandprice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
