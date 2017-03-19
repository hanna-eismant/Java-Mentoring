package com.epam.cdp.ws.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.epam.cdp.ws.model.Person;
import com.epam.cdp.ws.model.Ticket;

public interface BookingService {

    Person getPassenger(String firstName, String secondName, LocalDate birthday);

    Long bookTicket(String departureCity, String arrivalCity, LocalDateTime departureDate, LocalDateTime arrivalDate, Person passenger);

    boolean payTicket(Long ticketNumber);

    boolean returnTicket(Long ticketNumber);

    Ticket findTicket(Long ticketNumber);
}
