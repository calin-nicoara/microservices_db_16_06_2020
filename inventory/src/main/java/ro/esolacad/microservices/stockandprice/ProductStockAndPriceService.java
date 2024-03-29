package ro.esolacad.microservices.stockandprice;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductStockAndPriceService {

    private final ProductStockAndPriceRepository productStockAndPriceRepository;

//    @PostConstruct
//    public void init() {
//        productStockAndPriceRepository.save(
//                ProductStockAndPrice.builder()
//                        .price(BigDecimal.valueOf(2.5))
//                        .stock(10)
//                        .productCode("PR_1")
//                        .build()
//        );
//    }

    public List<ProductStockAndPrice> findAllProductStockAndPrices() {
        return productStockAndPriceRepository.findAll();
    }

    public Optional<ProductStockAndPrice> findByCode(final String productCode) {
        return productStockAndPriceRepository.findByProductCode(productCode);
    }

    public Optional<ProductStockAndPriceModel> findModelByCode(final String productCode) {
        return productStockAndPriceRepository.findByProductCode(productCode)
                .map(productStockAndPrice ->
                    ProductStockAndPriceModel.builder()
                            .id(productStockAndPrice.getId())
                            .price(productStockAndPrice.getPrice())
                            .productCode(productStockAndPrice.getProductCode())
                            .stock(productStockAndPrice.getStock())
                            .build()
                );
    }

    public void changeStock(StockChangeModel stockChangeModel) {
        Optional<ProductStockAndPrice> byProductCode = productStockAndPriceRepository.findByProductCode(stockChangeModel.getProductCode());

        byProductCode.ifPresent(productStockAndPrice -> {
            productStockAndPrice.setStock(productStockAndPrice.getStock() + stockChangeModel.getQuantityToChange());
            productStockAndPriceRepository.save(productStockAndPrice);
        });
    }
}
