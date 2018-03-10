package ticketCart.entity;

import javax.persistence.*;;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable{

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer seat;

    private String projectionStart;

    public Ticket() {
    }

    public Ticket(String name, BigDecimal price, Integer seat, String projectionStart) {
        this.name = name;
        this.price = price;
        this.seat = seat;
        this.projectionStart = projectionStart;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Column(nullable = false)
    public String getProjectionStart() {
        return projectionStart;
    }

    public void setProjectionStart(String projectionStart) {
        this.projectionStart = projectionStart;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Ticket ticket = (Ticket) obj;

        return id.equals(ticket.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
