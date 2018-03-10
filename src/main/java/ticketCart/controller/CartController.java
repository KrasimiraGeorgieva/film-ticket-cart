package ticketCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ticketCart.exeption.NotEnoughFreeSeatsException;
import ticketCart.service.cartService.CartService;
import ticketCart.service.ticket.TicketService;


@Controller
public class CartController {

    private final CartService cartService;

    private final TicketService ticketService;

    @Autowired
    public CartController(CartService cartService, TicketService ticketService) {
        this.ticketService = ticketService;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ModelAndView cart(){
        ModelAndView mav = new ModelAndView("cart");

        mav.addObject("tickets", cartService.getTicketsInCart());
        mav.addObject("total",cartService.getTotal().toString());
		
        return mav;
    }

    @GetMapping("/cart/addTicket/{id}")
    public ModelAndView addTicket(@PathVariable("id") Integer id){
        cartService.add(ticketService.findById(id));

        return cart();
    }

    @GetMapping("/cart/removeTicket/{id}")
    public ModelAndView removeTicket(@PathVariable("id") Integer id){
        cartService.remove(ticketService.findById(id));

        return cart();
    }

    @GetMapping("/cart/checkout")
    public ModelAndView checkout(){
        try{
            cartService.checkout();
        }catch (NotEnoughFreeSeatsException e){
            return cart().addObject("outOfStockMessage", e.getMessage());
        }
        return cart();
    }
}