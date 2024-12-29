package bookstore.repositories.userrepository;

import bookstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
}
