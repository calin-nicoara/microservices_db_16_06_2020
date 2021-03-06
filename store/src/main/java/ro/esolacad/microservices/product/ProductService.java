package ro.esolacad.microservices.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import ro.esolacad.microservices.externalclients.InventoryClient;
import ro.esolacad.microservices.externalclients.StockAndPriceModel;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public Optional<ProductWithStockAndPriceModel> findByProductCode(final String code) {
        return productRepository.findByCode(code)
                .map(product -> getProductWithStockAndPriceModel(product));
    }

    public List<ProductWithStockAndPriceModel> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product ->  getProductWithStockAndPriceModel(product))
                .collect(Collectors.toList());
    }

    private ProductWithStockAndPriceModel getProductWithStockAndPriceModel(final Product product) {
        StockAndPriceModel stockAndPriceByCode = inventoryClient.findStockAndPriceByCodeUsingFeign(product.getCode());

        return ProductWithStockAndPriceModel.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .price(stockAndPriceByCode.getPrice())
                .stock(stockAndPriceByCode.getStock())
                .build();
    }
}
