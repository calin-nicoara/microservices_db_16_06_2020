package ro.esolacad.microservices.stockandprice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductStockAndPriceServiceTest {

    @Mock
    ProductStockAndPriceRepository productStockAndPriceRepository;

    @InjectMocks
    ProductStockAndPriceService productStockAndPriceService;

    @Test
    public void testFindByCode() {
        ProductStockAndPrice productStockAndPrice = ProductStockAndPrice.builder()
                .productCode("PR_1")
                .stock(1)
                .build();
        Optional<ProductStockAndPrice> optionalProductStockAndPrice = Optional.of(productStockAndPrice);

        Mockito.when(productStockAndPriceRepository.findByProductCode("PR_1"))
                .thenReturn(optionalProductStockAndPrice);


        Optional<ProductStockAndPrice> stockAndPriceOptional = productStockAndPriceService.findByCode("PR_1");

        System.out.println(stockAndPriceOptional);
    }

    @Test
    public void testFindModelByCode() {
        ProductStockAndPrice productStockAndPrice = ProductStockAndPrice.builder()
                .productCode("PR_1")
                .stock(1)
                .build();
        Optional<ProductStockAndPrice> optionalProductStockAndPrice = Optional.of(productStockAndPrice);

        Mockito.when(productStockAndPriceRepository.findByProductCode("PR_1"))
                .thenReturn(optionalProductStockAndPrice);


        Optional<ProductStockAndPriceModel> stockAndPriceOptional = productStockAndPriceService.findModelByCode("PR_1");

        ProductStockAndPriceModel expectedModel = ProductStockAndPriceModel.builder()
                .productCode("PR_1")
                .stock(1)
                .build();

        Assertions.assertEquals(Optional.of(expectedModel), stockAndPriceOptional);
    }

    @Test
    public void testChangeStock() {
        StockChangeModel stockChangeModel = StockChangeModel.builder()
                .productCode("PR_1")
                .quantityToChange(1)
                .build();

        ProductStockAndPrice productStockAndPrice = ProductStockAndPrice.builder()
                .productCode("PR_1")
                .stock(1)
                .build();
        Optional<ProductStockAndPrice> optionalProductStockAndPrice = Optional.of(productStockAndPrice);

        Mockito.when(productStockAndPriceRepository.findByProductCode("PR_1"))
                .thenReturn(optionalProductStockAndPrice);

        productStockAndPriceService.changeStock(stockChangeModel);

        ProductStockAndPrice productStockAndPriceAfterSave = ProductStockAndPrice.builder()
                .productCode("PR_1")
                .stock(2)
                .build();

        Mockito.verify(productStockAndPriceRepository).save(productStockAndPriceAfterSave);

        Mockito.verifyNoMoreInteractions(productStockAndPriceRepository);
    }

    @Test
    public void testChangeStockButProductNotFound() {
        StockChangeModel stockChangeModel = StockChangeModel.builder()
                .productCode("PR_1")
                .quantityToChange(1)
                .build();

        Mockito.when(productStockAndPriceRepository.findByProductCode("PR_1"))
                .thenReturn(Optional.empty());

        productStockAndPriceService.changeStock(stockChangeModel);

        Mockito.verifyNoMoreInteractions(productStockAndPriceRepository);
    }



}
