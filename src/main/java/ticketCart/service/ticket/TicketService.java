package ticketCart.service.ticket;

import org.springframework.stereotype.Service;

import ticketCart.entity.Ticket;

import java.util.List;

@Service
public interface TicketService {

    public Ticket findById(Integer id);

    public List<Ticket> findAll();

}
