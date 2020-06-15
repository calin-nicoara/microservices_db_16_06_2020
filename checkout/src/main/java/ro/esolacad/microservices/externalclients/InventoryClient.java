package ro.esolacad.microservices.externalclients;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryClient {

    private RestTemplate restTemplate = new RestTemplate();

    public void addModifyItemStock(StockChangeModel stockChangeModel) {
        String urlModifyStock = "http://localhost:8080/stock-and-price";

        restTemplate.postForEntity(urlModifyStock, stockChangeModel, Void.class);
    }
}
