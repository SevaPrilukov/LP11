package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate_configuration.xml")
                .addAnnotatedClass(Cruise.class)
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Passenger.class)
                .buildSessionFactory();

        Dostyp dostyp = new Dostyp(factory);

        Passenger passenger = new Passenger("Prilukov","Seva","Aleks", Date.valueOf("2003-11-25"), "ABC123");
        dostyp.addPassenger(passenger);
        Passenger passenger1 = new Passenger("ukgdfgov","Sevava","Aldeks", Date.valueOf("2000-11-25"), "gBC123");
        dostyp.addPassenger(passenger1);

        Passenger passenger3 = new Passenger("Prilukovкуп","Sevaффф","Aleks", Date.valueOf("2003-11-25"), "AGC123");
        dostyp.addPassenger(passenger3);
        Passenger passenger4 = new Passenger("ukgdfgovкп","Sevavaааа","Aldeks", Date.valueOf("2000-11-25"), "gKC123");
        dostyp.addPassenger(passenger4);

        Passenger passenger5 = new Passenger("Prilukovц","Sevaрррр","Aleks", Date.valueOf("2003-11-25"), "AUC123");
        dostyp.addPassenger(passenger5);
        Passenger passenger6 = new Passenger("ukgdfgoаыпv","Sevavaооооо","Aldeks", Date.valueOf("2000-11-25"), "TBC123");
        dostyp.addPassenger(passenger6);

        Cruise cruise = new Cruise(Date.valueOf("1990-01-23"),"Place1", "Place11", Duration.ofHours(1));
        dostyp.addCruice(cruise);
        Cruise cruise1 = new Cruise(Date.valueOf("1990-01-23"),"Place2", "Place22", Duration.ofHours(2));
        dostyp.addCruice(cruise1);
        dostyp.buyTicket(1,2,120.00);
        dostyp.buyTicket(1,2,140.00);
        dostyp.buyTicket(2,2,140.00);

        dostyp.buyTicket(4,1,120.00);
        dostyp.buyTicket(5,1,120.00);
        dostyp.buyTicket(1,1,150.00);

//        dostyp.delete(1);

//        dostyp.update(1,"привет");
//
//        dostyp.name("Seva");
//
//        dostyp.getPassengersByCruiseName("Place11");
//        dostyp.getPassengersByCruiseId(2);

        dostyp.printPassengersForCruise(1);
    }
}