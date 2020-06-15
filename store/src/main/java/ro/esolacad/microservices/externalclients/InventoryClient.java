package ro.esolacad.microservices.externalclients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.ConnectException;

import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.product.ProductWithStockAndPriceModel;

@Service
@Slf4j
public class InventoryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public StockAndPriceModel findStockAndPriceByCode(final String code) {
        String urlToPriceAndStock = "http://localhost:8080/stock-and-price/" + code;

        try {
            ResponseEntity<StockAndPriceModel> responseEntity = restTemplate
                    .getForEntity(urlToPriceAndStock, StockAndPriceModel.class);
            return responseEntity.getBody();

        } catch(HttpClientErrorException errorException) {
            if(errorException.getStatusCode().is4xxClientError()) {
                log.warn("Product inventory was not found", errorException);
            } else if(errorException.getStatusCode().is5xxServerError()) {
                log.error("Inventory service not available", errorException);
            }
        } catch (Exception connectException) {
            log.error("Problem with inventory service");
        }

        return StockAndPriceModel.builder()
                .price(BigDecimal.ZERO)
                .stock(0)
                .build();
    }
}
