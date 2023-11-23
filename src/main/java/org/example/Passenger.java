package org.example;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Passenger_ID")
    private int passengerId;

    @Column(name = "Surname", nullable = false, length = 20)
    private String surname;

    @NaturalId
    @Column(name = "Name", nullable = false, length = 20)
    private String name;

    @Column(name = "Middle_name", length = 20)
    private String middleName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "Passport_data", nullable = false, unique = true, length = 20)
    private String passportData;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List<Ticket> tickets;



    public Passenger(String surname, String name, String middleName, Date dateOfBirth, String passportData) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.passportData = passportData;
    }

    public Passenger() {
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassportData() {
        return passportData;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
