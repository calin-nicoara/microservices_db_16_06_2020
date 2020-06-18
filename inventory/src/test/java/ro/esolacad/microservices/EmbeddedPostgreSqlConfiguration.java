package ro.esolacad.microservices;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.time.Duration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Configuration
public class EmbeddedPostgreSqlConfiguration {

    private PostgreSQLContainer postgresqlContainer;
    private DataSource dataSource;

    @Bean(destroyMethod = "stop", name = "postgresProcess")
    public PostgreSQLContainer postgresProcess() throws IOException {
        postgresqlContainer =
                (PostgreSQLContainer) new PostgreSQLContainer("postgres:10.4")
                        .withDatabaseName("ms")
                        .withUsername("postgres")
                        .withPassword("postgres")
                        .withStartupTimeout(Duration.ofSeconds(600));
        postgresqlContainer.start();
        return postgresqlContainer;
    }

    @Bean
    @DependsOn("postgresProcess")
    public DataSource datasource() {
        dataSource = DataSourceBuilder
                .create()
                .username("postgres")
                .password("postgres")
                .url(postgresqlContainer.getJdbcUrl())
                .driverClassName("org.postgresql.Driver")
                .build();

        return dataSource;
    }

    @PreDestroy
    public void preDestroy() {
        postgresqlContainer.stop();
    }
}
