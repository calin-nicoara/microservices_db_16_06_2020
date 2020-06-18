package ro.esolacad.microservices.stockandprice;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductStockAndPriceResource.class)
class ProductStockAndPriceResourceTest {

    @MockBean
    private ProductStockAndPriceService productStockAndPriceService;

    @MockBean
    private ProductStockAndPriceRepository productStockAndPriceRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "test")
    public void testFindByProductCode() throws Exception {
        ProductStockAndPrice productStockAndPrice = ProductStockAndPrice.builder()
                .productCode("PR_1")
                .stock(1)
                .build();
        Optional<ProductStockAndPrice> optionalProductStockAndPrice = Optional.of(productStockAndPrice);
        Mockito.when(productStockAndPriceService.findByCode("PR_1"))
                .thenReturn(optionalProductStockAndPrice);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/stock-and-price/PR_1"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        ProductStockAndPrice productStockAndPriceFromEndpoint = objectMapper.readValue(contentAsString, ProductStockAndPrice.class);

        Assertions.assertEquals(productStockAndPrice, productStockAndPriceFromEndpoint);
    }

}
