package bookstore.controllers;

import bookstore.dtos.order.OrderDto;
import bookstore.dtos.order.PlaceOrderDto;
import bookstore.dtos.order.UpdateOrderDto;
import bookstore.dtos.orderitems.OrderItemDto;
import bookstore.models.User;
import bookstore.services.orderitemservice.IOrderItemService;
import bookstore.services.orderservice.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders endpoints")
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;

    @Operation(description = "Get all orders")
    @GetMapping
    public Page<OrderDto> getUserOrders(Authentication authentication, Pageable pageable) {
        return orderService.getUserOrders(
                ((User) authentication.getPrincipal()).getId(),
                pageable
        );
    }

    @Operation(description = "Place an order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto placeOrder(
            @RequestBody @Valid PlaceOrderDto placeOrderDto,
            Authentication authentication
    ) {
        return orderService.placeOrder(
                ((User)authentication.getPrincipal()),
                placeOrderDto
        );
    }

    @Operation(description = "Update order status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public OrderDto updateOrderStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOrderDto updateOrderDto
    ) {
        return orderService.updateOrderStatus(id, updateOrderDto);
    }

    @Operation(description = "Get all order items")
    @GetMapping("{orderId}/items")
    public Page<OrderItemDto> getOrderItems(@PathVariable Long orderId, Pageable pageable) {
        return orderItemService.getAllByOrderId(orderId, pageable);
    }

    @Operation(description = "Get order item")
    @GetMapping("{orderId}/items/{id}")
    public OrderItemDto getOrderItem(@PathVariable Long id) {
        return orderItemService.getById(id);
    }
}
