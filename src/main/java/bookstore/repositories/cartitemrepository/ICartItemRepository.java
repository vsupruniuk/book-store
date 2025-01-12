package bookstore.repositories.cartitemrepository;

import bookstore.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsById(@NonNull Long id);

    @Modifying
    @Query("UPDATE CartItem item SET item.isDeleted = true WHERE item.id IN :ids")
    void softDeleteAllById(@Param("ids") Iterable<? extends Long> ids);
}
