package bookstore.repositories.shoppingcartrepository;

import bookstore.models.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query(
            "FROM ShoppingCart shoppingCart "
            + "LEFT JOIN FETCH shoppingCart.cartItems "
            + "WHERE shoppingCart.user.id = :userId"
    )
    Optional<ShoppingCart> findByUserIdWithCartItems(@Param("userId") Long userId);

    Optional<ShoppingCart> findByUserId(Long userId);
}
