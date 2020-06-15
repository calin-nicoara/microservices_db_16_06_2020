package ro.esolacad.microservices.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductWithStockAndPriceModel> findByProductCode(@PathVariable("productCode") String code) {
        Optional<ProductWithStockAndPriceModel> byProductCode = productService.findByProductCode(code);

        if(byProductCode.isPresent()) {
            return ResponseEntity.ok(byProductCode.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
