package ro.esolacad.microservices.externalclients;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;


import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeingClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
                    authentication.getDetails();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + details.getTokenValue());
        }
    }
}
