package ticketCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketCart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
