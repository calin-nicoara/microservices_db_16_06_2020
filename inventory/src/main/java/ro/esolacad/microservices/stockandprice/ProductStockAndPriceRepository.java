package ro.esolacad.microservices.stockandprice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockAndPriceRepository extends JpaRepository<ProductStockAndPrice, Long> {
}
