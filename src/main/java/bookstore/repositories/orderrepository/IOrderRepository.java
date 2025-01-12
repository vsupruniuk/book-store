package bookstore.repositories.orderrepository;

import bookstore.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query("FROM Order order LEFT JOIN FETCH order.orderItems WHERE order.user.id = :userId")
    Page<Order> getAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
