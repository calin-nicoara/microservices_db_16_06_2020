package ro.esolacad.microservices.stockandprice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductStockAndPriceResourceComponentTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testEndpoint() {
        ResponseEntity<ProductStockAndPrice> entity = testRestTemplate
                .getForEntity("/stock-and-price/PR_1", ProductStockAndPrice.class);

        Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assertions.assertEquals(ProductStockAndPrice.builder()
                .id(1L)
                .price(BigDecimal.valueOf(2.5).setScale(2))
                .stock(10)
                .productCode("PR_1")
                .build(), entity.getBody());
    }
}
