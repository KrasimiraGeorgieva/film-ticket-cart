package ticketCart.service.cartService;

import org.springframework.stereotype.Service;

import ticketCart.entity.Ticket;
import ticketCart.exeption.NotEnoughFreeSeatsException;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface CartService {
    void add(Ticket ticket);

    void remove(Ticket ticket);

    Map<Ticket, Integer> getTicketsInCart();

    void checkout() throws NotEnoughFreeSeatsException;

    BigDecimal getTotal();
}
