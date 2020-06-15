package ro.esolacad.microservices.stockandprice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStockAndPriceRepository extends JpaRepository<ProductStockAndPrice, Long> {

    Optional<ProductStockAndPrice> findByProductCode(String code);
}
