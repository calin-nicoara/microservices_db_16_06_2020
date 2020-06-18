package ro.esolacad.microservices.component;

import org.bouncycastle.util.test.TestRandomEntropySourceProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import ro.esolacad.microservices.product.ProductWithStockAndPriceModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StoreComponentTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetProduct() {
        ResponseEntity<ProductWithStockAndPriceModel> forEntity = testRestTemplate
                .getForEntity("/product/PR_1", ProductWithStockAndPriceModel.class);

        System.out.println(forEntity.getBody());
    }
}
