package ro.esolacad.microservices.cart;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import ro.esolacad.microservices.externalclients.InventoryClient;
import ro.esolacad.microservices.externalclients.StockChangeModel;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final InventoryClient inventoryClient;

    public void addCartItem(final AddCartItemModel addCartItemModel) {
        CartItem cartItem = cartItemRepository.findByProductCode(addCartItemModel.getProductCode())
                .map(cartItemFound -> {
                    cartItemFound.setQuantity(cartItemFound.getQuantity() + addCartItemModel.getQuantityToAdd());
                    return cartItemFound;
                })
                .orElseGet(() ->
                        CartItem.builder()
                                .productCode(addCartItemModel.getProductCode())
                                .quantity(addCartItemModel.getQuantityToAdd())
                                .build());

        cartItemRepository.save(cartItem);
        inventoryClient.addModifyItemStock(
                StockChangeModel.builder()
                        .productCode(addCartItemModel.getProductCode())
                        .quantityToChange(-addCartItemModel.getQuantityToAdd())
                        .build()
        );

    }

    public List<CartItem> findAllCartItems() {
        return cartItemRepository.findAll();
    }
}
