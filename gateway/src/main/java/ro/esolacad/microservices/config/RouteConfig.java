package ro.esolacad.microservices.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(final RouteLocatorBuilder builder) {
        return builder
                .routes()
                    .route(pred ->
                            pred.path("/inventory/**").or()
                                    .path("/inventory_v2/**")
                            .filters(f -> f.stripPrefix(1)).uri("http://localhost:8080")
                    )
                .build();
    }
}
