package bookstore.repositories.order;

import bookstore.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query("FROM Order order "
            + "JOIN FETCH order.orderItems orderItem "
            + "JOIN FETCH order.user "
            + "JOIN FETCH orderItem.book "
            + "WHERE order.user.id = :userId")
    Page<Order> getAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
