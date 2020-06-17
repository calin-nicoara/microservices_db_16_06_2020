package ro.esolacad.microservices.product;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ro.esolacad.microservices.messaging.TestMessagingGateway;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductResource {

    private final ProductService productService;
    private final TestMessagingGateway testMessagingGateway;
    private final TokenStore tokenStore;

    @GetMapping("/{productCode}")
//    @PreAuthorize("hasAuthority('ROLE_USER') || #code == 'PR_1'")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_GUEST')")
//    @PreAuthorize("#code == 'PR_1'")
    public ResponseEntity<ProductWithStockAndPriceModel> findByProductCode(
            @PathVariable("productCode") String code,
            @RequestHeader("Authorization") String token
    ) {

        log.info(token);
        Optional<ProductWithStockAndPriceModel> byProductCode = productService.findByProductCode(code);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(details.getTokenValue());
        log.info(oAuth2AccessToken.getAdditionalInformation().toString());

//        testMessagingGateway.sendMessage("TEST_MESSAGE");

        if(byProductCode.isPresent()) {
            return ResponseEntity.ok(byProductCode.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Secured({"ROLE_GUEST", "ROLE_USER"})
    public ResponseEntity<List<ProductWithStockAndPriceModel>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
