package ro.esolacad.microservices.cart;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartResource {

    private final CartService cartService;

    @PostMapping("/addItem")
    public void addCartItem(@RequestBody AddCartItemModel addCartItemModel) {
        cartService.addCartItem(addCartItemModel);
    }

    @GetMapping
    public List<CartItem> findAllCartItems() {
        return cartService.findAllCartItems();
    }
}
