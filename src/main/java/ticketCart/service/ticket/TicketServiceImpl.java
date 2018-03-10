package ticketCart.service.ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketCart.entity.Ticket;
import ticketCart.repository.TicketRepository;

import java.util.List;

@Service

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket findById(Integer id) {
        return ticketRepository.findOne(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
