package ticketCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ticketCart.entity.Ticket;
import ticketCart.service.ticket.TicketService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final TicketService ticketService;

    @Autowired
    public HomeController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        List<Ticket> tickets = ticketService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("tickets", tickets);
        mav.setViewName("home");
		
        return mav;
    }
}