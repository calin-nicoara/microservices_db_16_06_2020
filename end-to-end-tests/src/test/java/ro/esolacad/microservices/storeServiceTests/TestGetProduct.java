package ro.esolacad.microservices.storeServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TestGetProduct {

    @Test
    public void testGetProduct() {
        String getProductUrl = "http://localhost:8999/store/product/PR_1";

        RestTemplate restTemplate = new RestTemplate();

        ProductWithStockAndPriceModel productModelExpected = ProductWithStockAndPriceModel.builder()
                .id(2L)
                .code("PR_1")
                .name("Coca-Cola")
                .description("Coca-Cola Half a liter")
                .price(BigDecimal.valueOf(2.5).setScale(2))
                .stock(10)
                .categoryId(1L)
                .categoryName("Juice")
                .build();

        ResponseEntity<ProductWithStockAndPriceModel> forEntity = restTemplate.getForEntity(getProductUrl,
                ProductWithStockAndPriceModel.class);

        Assertions.assertEquals(productModelExpected, forEntity.getBody());
    }
}
