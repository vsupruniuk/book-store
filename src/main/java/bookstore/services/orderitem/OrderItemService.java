package bookstore.services.orderitem;

import bookstore.dtos.orderitems.OrderItemDto;
import bookstore.exceptions.EntityNotFoundException;
import bookstore.mappers.orderitem.IOrderItemMapper;
import bookstore.repositories.orderitem.IOrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemService implements IOrderItemService {
    private final IOrderItemRepository orderItemRepository;
    private final IOrderItemMapper orderItemMapper;

    @Override
    public Page<OrderItemDto> getAllByOrderId(Long orderId, Pageable pageable) {
        return orderItemRepository
                .findAllByOrderId(orderId, pageable)
                .map(orderItemMapper::toDto);
    }

    @Override
    public OrderItemDto getById(Long id) {
        return orderItemRepository
                .findById(id)
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Order item with id %d does not exist".formatted(id)
                ));
    }
}
