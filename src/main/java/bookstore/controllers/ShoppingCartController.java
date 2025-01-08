package bookstore.controllers;

import bookstore.dtos.cartitem.CartItemDto;
import bookstore.dtos.cartitem.CreateCartItemDto;
import bookstore.dtos.cartitem.UpdateCartItemDto;
import bookstore.dtos.shoppingcart.ShoppingCartDto;
import bookstore.models.User;
import bookstore.services.cartitemservice.ICartItemService;
import bookstore.services.shoppingcartservice.IShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final IShoppingCartService shoppingCartService;
    private final ICartItemService cartItemService;

    @Operation(description = "Get user shopping cart")
    @GetMapping
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        return shoppingCartService.getByUserIdWithCartItems(
                ((User)authentication.getPrincipal()).getId()
        );
    }

    @Operation(description = "Add a book to shopping cart")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemDto addBookToCart(
            @RequestBody @Valid CreateCartItemDto createUpdateCartItemDto,
            Authentication authentication
    ) {
        return cartItemService.addCartItem(
                ((User)authentication.getPrincipal()).getId(),
                createUpdateCartItemDto
        );
    }

    @Operation(description = "Update cart item")
    @PutMapping("/items/{cartItemId}")
    public CartItemDto updateCartItem(
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateCartItemDto updateCartItemDto
    ) {
        return cartItemService.updateCartItem(cartItemId, updateCartItemDto);
    }

    @Operation(description = "Delete cart item")
    @DeleteMapping("/items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(
            @PathVariable Long cartItemId
    ) {
        cartItemService.deleteCartItem(cartItemId);
    }
}
