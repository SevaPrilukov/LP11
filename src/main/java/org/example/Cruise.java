package org.example;

import javax.persistence.*;
import java.time.Duration;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Cruise")
public class Cruise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cruise_ID")
    private int cruiseId;

    @Column(name = "Date_and_time_of_departure", nullable = false)
    private Date dateAndTimeOfDeparture;

    @Column(name = "Place_of_departure", nullable = false, length = 50)
    private String placeOfDeparture;

    @Column(name = "Destination", nullable = false, length = 50)
    private String destination;

    @Column(name = "Duration", nullable = false)
    private Duration duration;

    @OneToMany(mappedBy = "cruise")
    private List<Ticket> tickets;

    public Cruise (Date dateAndTimeOfDeparture, String placeOfDeparture, String destination, Duration duration){
        this.dateAndTimeOfDeparture = dateAndTimeOfDeparture;
        this.placeOfDeparture = placeOfDeparture;
        this.destination = destination;
        this.duration = duration;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(destination).append(":\n");

        for (Ticket ticket : tickets) {
            stringBuilder.append("  Номер билета: ").append(ticket.getTicketId())
                    .append(", Passenger: ").append(ticket.getPassenger().getName())
                    .append(" ").append(ticket.getPassenger().getSurname())
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    public Cruise() {
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public Date getDateAndTimeOfDeparture() {
        return dateAndTimeOfDeparture;
    }

    public String getPlaceOfDeparture() {
        return placeOfDeparture;
    }

    public String getDestination() {
        return destination;
    }

    public Duration getDuration() {
        return duration;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    public void setDateAndTimeOfDeparture(Date dateAndTimeOfDeparture) {
        this.dateAndTimeOfDeparture = dateAndTimeOfDeparture;
    }

    public void setPlaceOfDeparture(String placeOfDeparture) {
        this.placeOfDeparture = placeOfDeparture;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
