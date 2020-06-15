package ro.esolacad.microservices.product;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTestDataService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        Category category = categoryRepository.save(
                Category.builder()
                        .code("CAT_1")
                        .name("Juice")
                        .build()
        );

        productRepository.save(
                Product.builder()
                        .code("PR_1")
                        .name("Coca-Cola")
                        .description("Coca-Cola Half a liter")
                        .category(category)
                        .build()
        );

        productRepository.save(
                Product.builder()
                        .code("PR_2")
                        .name("Pepsi")
                        .description("Pepsi 2L")
                        .category(category)
                        .build()
        );
    }
}
