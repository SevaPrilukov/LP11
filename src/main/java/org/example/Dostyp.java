package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;

public class Dostyp {
    private final SessionFactory sessionFactory;

    public Dostyp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void addPassenger(Passenger passenger){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(passenger);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void addCruice(Cruise cruise){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(cruise);
            session.getTransaction().commit();
            session.close();
        }
    }
    public void buyTicket(int passengerId, int cruiseId, double ticketPrice) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Passenger passenger = session.get(Passenger.class, passengerId);
            Cruise cruise = session.get(Cruise.class, cruiseId);

            if (passenger != null && cruise != null) {
                Ticket ticket = new Ticket();
                ticket.setTicketPrice(ticketPrice);
                ticket.setPassenger(passenger);
                ticket.setCruise(cruise);
                session.save(ticket);
                passenger.getTickets().add(ticket);
                session.getTransaction().commit();
            }

            session.close();
        }
    }

    public void delete(int userid) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Passenger passenger = session.find(Passenger.class, userid);
            if (passenger != null) {
                session.delete(passenger);
                System.out.println("Пользователь с id " + userid + " удалён");
            }
            else System.out.println("Пользователя с id " + userid + " не существует или он удалён ранее");
            session.getTransaction().commit();
            session.close();
        }
    }

    //update
    public void update(int cruiseId, String newName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Cruise cruise = session.find(Cruise.class, cruiseId);

            if (cruise != null) {
                cruise.setDestination(newName);
                session.update(cruise);
                System.out.println("Название круиза с ID " + cruiseId + " изменено на " + newName);
            } else {
                System.out.println("Круиз с ID " + cruiseId + " не найден");
            }

            session.getTransaction().commit();
        }
    }

    public void name(String passengerName) {
        Set<Ticket> tickets = new HashSet<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Passenger passenger = session.bySimpleNaturalId(Passenger.class).load(passengerName);

            if (passenger != null) {
                tickets = new HashSet<>(passenger.getTickets());
            }

            if (!tickets.isEmpty()) {
                System.out.println("Билеты у пассажира " + passengerName + ":");
                for (Ticket ticket : tickets) {
                    int ticketId = ticket.getTicketId();
                    String cruiseDestination = ticket.getCruise().getDestination();

                    System.out.println("ID билета: " + ticketId + ", Название круиза: " + cruiseDestination);
                }
            } else if (passenger != null) {
                System.out.println("У пассажира " + passengerName + " нет билетов");
            } else {
                System.out.println("Пассажир с именем " + passengerName + " не найден");
            }

            session.getTransaction().commit();
        }
    }


    public void getPassengersByCruiseName(String cruiseName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Ticket> tickets = session.createQuery("FROM Ticket", Ticket.class).list();
            if (!tickets.isEmpty()) {
                Map<String, Set<String>> cruisePassengerMap = new HashMap<>();
                for (Ticket ticket : tickets) {
                    String currentCruiseName = ticket.getCruise().getDestination();
                    String passengerName = ticket.getPassenger().getName();
                    if (currentCruiseName.equals(cruiseName)) {
                        cruisePassengerMap.computeIfAbsent(currentCruiseName, k -> new HashSet<>()).add(passengerName);
                    }
                }

                if (cruisePassengerMap.containsKey(cruiseName)) {
                    System.out.println("Пассажиры, участвующие в круизе ");
                    System.out.println(cruiseName + ": " + cruisePassengerMap.get(cruiseName));
                } else {
                    System.out.println("Круиз с названием " + cruiseName + " не найден или у пассажиров нет билетов на этот круиз");
                }
            } else {
                System.out.println("Нет доступных билетов");
            }

            session.getTransaction().commit();
        }
    }


    public void getPassengersByCruiseId(int cruiseId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Получаем круиз по ID
            Cruise cruise = session.get(Cruise.class, cruiseId);

            if (cruise != null) {
                Map<Integer, Set<String>> cruisePassengerMap = new HashMap<>();
                Set<String> passengerNames = new HashSet<>();

                // Перебираем билеты круиза и получаем имена пассажиров
                for (Ticket ticket : cruise.getTickets()) {
                    passengerNames.add(ticket.getPassenger().getName());
                }

                cruisePassengerMap.put(cruiseId, passengerNames);

                if (!passengerNames.isEmpty()) {
                    System.out.println("Пассажиры, участвующие в круизе с ID " + cruiseId);
                    System.out.println(passengerNames);
                } else {
                    System.out.println("У круиза с ID " + cruiseId + " нет пассажиров");
                }
            } else {
                System.out.println("Круиз с ID " + cruiseId + " не найден");
            }

            session.getTransaction().commit();
        }
    }

    public void printPassengersForCruise(int cruiseId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Cruise cruise = session.get(Cruise.class, cruiseId);

            if (cruise != null) {
                System.out.println(cruise);

            } else {
                System.out.println("Рейс с ID " + cruiseId + " не найден");
            }

            session.getTransaction().commit();
        }
    }



}
