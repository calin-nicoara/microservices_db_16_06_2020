package ro.esolacad.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TokenBeansConfig {

    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;

    @Bean
    public JwtAccessTokenConverter  jwtAccessTokenConverter() {
        CustomTokenEnhancer customTokenEnhancer = new CustomTokenEnhancer();

        Resource keyStore = securityProperties.getJtw().getKeyStore();
        char[] passwordAsArray = securityProperties.getJtw().getKeyStorePassword().toCharArray();
        String keyPairAlias = securityProperties.getJtw().getKeyPairAlias();

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, passwordAsArray);

        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);

        customTokenEnhancer.setKeyPair(keyPair);
        return customTokenEnhancer;
    }

    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore,
                                              final ClientDetailsService clientDetailsService) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setAuthenticationManager(authenticationManager);

        return tokenServices;
    }
}
