package ro.esolacad.microservices.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import ro.esolacad.microservices.messaging.TestMessagingGateway;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;
    private final TestMessagingGateway testMessagingGateway;

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductWithStockAndPriceModel> findByProductCode(@PathVariable("productCode") String code) {
        Optional<ProductWithStockAndPriceModel> byProductCode = productService.findByProductCode(code);

        testMessagingGateway.sendMessage("TEST_MESSAGE");

        if(byProductCode.isPresent()) {
            return ResponseEntity.ok(byProductCode.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductWithStockAndPriceModel>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
