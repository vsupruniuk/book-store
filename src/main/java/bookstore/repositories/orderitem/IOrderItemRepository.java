package bookstore.repositories.orderitem;

import bookstore.models.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("FROM OrderItem item LEFT JOIN FETCH item.order WHERE item.order.id = :orderId")
    Page<OrderItem> findAllByOrderId(Long orderId, Pageable pageable);
}
