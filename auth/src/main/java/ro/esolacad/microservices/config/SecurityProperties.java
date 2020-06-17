package ro.esolacad.microservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("security")
public class SecurityProperties {

    @Getter
    @Setter
    private JwtProperties jtw;

    @Getter
    @Setter
    public static class JwtProperties {
        private Resource keyStore;
        private String keyStorePassword;
        private String keyPairAlias;
    }
}
