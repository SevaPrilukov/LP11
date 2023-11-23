package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ticket_ID")
    private int ticketId;

    @Column(name = "Ticket_price", nullable = false)
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "Cruise_ID", nullable = false)
    private Cruise cruise;

    @ManyToOne
    @JoinColumn(name = "Passenger_ID", nullable = false)
    private Passenger passenger;

    public Ticket(Double ticketPrice, Cruise cruise, Passenger passenger) {
        this.ticketPrice = ticketPrice;
        this.cruise = cruise;
        this.passenger = passenger;
    }

    public Ticket() {
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}
