package ticketCart.exeption;

import ticketCart.entity.Ticket;

public class NotEnoughFreeSeatsException extends Exception {
    private Ticket ticket;
    private static final String DEFAULT_MESSAGE = "NOT ENOUGH FREE SEATS. TRY ANOTHER PROJECTION OR MOVIE";

    public NotEnoughFreeSeatsException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughFreeSeatsException(Ticket ticket) {
        super("There is " + ticket.getSeat() + " seats left"
                + " for film" + ticket.getName() + " and projection start at " + ticket.getProjectionStart());

        this.ticket = ticket;
    }
}
