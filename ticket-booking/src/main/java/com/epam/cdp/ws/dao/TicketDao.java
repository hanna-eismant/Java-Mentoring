package com.epam.cdp.ws.dao;

import org.joda.time.LocalDateTime;

import com.epam.cdp.ws.model.Ticket;

public interface TicketDao {
    Ticket generate(String departureCity, String arrivalCity, LocalDateTime departureDate, LocalDateTime arrivalDate);

    Ticket find(Long ticketNumber);

    void remove(Ticket ticket);
}
