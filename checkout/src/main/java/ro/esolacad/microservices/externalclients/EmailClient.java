package ro.esolacad.microservices.externalclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.esolacad.microservices.order.OrderModel;

@FeignClient(name = "account-service")
public interface EmailClient {

    @RequestMapping(value = "/email", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> sendOrderEmail(@RequestBody OrderModel orderModel);
}
