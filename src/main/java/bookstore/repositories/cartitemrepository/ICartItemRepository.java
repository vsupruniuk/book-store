package bookstore.repositories.cartitemrepository;

import bookstore.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsById(@NonNull Long id);
}
