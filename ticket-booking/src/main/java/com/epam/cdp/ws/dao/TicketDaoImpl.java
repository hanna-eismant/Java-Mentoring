package com.epam.cdp.ws.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import com.epam.cdp.ws.model.Ticket;

@Component
public class TicketDaoImpl implements TicketDao {

    private long numberCounter = 1;
    private List<Ticket> storage = new ArrayList<>();

    @Override
    public Ticket generate(final String departureCity, final String arrivalCity, final LocalDateTime departureDate, final LocalDateTime arrivalDate) {
        Ticket ticket = new Ticket(numberCounter++, departureCity, arrivalCity, departureDate, arrivalDate);
        storage.add(ticket);
        return ticket;
    }

    @Override
    public Ticket find(final Long ticketNumber) {
        return storage.stream().filter(ticket ->
                    Objects.equals(ticket.getNumber(), ticketNumber
                )).findFirst().orElse(null);
    }

    @Override
    public void remove(final Ticket ticket) {
        storage.remove(ticket);
    }
}
