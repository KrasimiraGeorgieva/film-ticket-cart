package ticketCart.service.cartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ticketCart.entity.Ticket;
import ticketCart.exeption.NotEnoughFreeSeatsException;
import ticketCart.repository.TicketRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartServiceImpl implements CartService {

    private final TicketRepository ticketRepository;

    @Autowired
    public CartServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    private Map<Ticket, Integer> tickets = new HashMap<>();

    @Override
    public void add(Ticket ticket) {
        if (tickets.containsKey(ticket)) {
            tickets.replace(ticket, tickets.get(ticket) + 1);
        } else {
            tickets.put(ticket, 1);
        }
    }

    @Override
    public void remove(Ticket ticket) {
        if (tickets.containsKey(ticket)) {
            if (tickets.get(ticket) > 1) {
                tickets.replace(ticket, tickets.get(ticket) - 1);
            }
        } else if (tickets.get(ticket) == 1) {
            tickets.remove(ticket);
        }
    }

    @Override
    public Map<Ticket, Integer> getTicketsInCart() {
        return Collections.unmodifiableMap(tickets);
    }

    @Override
    public void checkout() throws NotEnoughFreeSeatsException {
        Ticket ticket;
        for (Map.Entry<Ticket, Integer> entry : tickets.entrySet()) {
            ticket = ticketRepository.findOne(entry.getKey().getId());
            if(ticket.getSeat() < entry.getValue()) {
                throw new NotEnoughFreeSeatsException();
            } else {
                entry.getKey().setSeat(ticket.getSeat() - entry.getValue());
            }
        }
        ticketRepository.save(tickets.keySet());
        ticketRepository.flush();
        tickets.clear();
    }

    @Override
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Ticket, Integer> entry : tickets.entrySet()) {
            total = total.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }
        return total;
    }
}
