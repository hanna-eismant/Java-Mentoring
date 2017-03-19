package com.epam.cdp.ws.service;

import java.util.Objects;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cdp.ws.dao.PersonDao;
import com.epam.cdp.ws.dao.TicketDao;
import com.epam.cdp.ws.model.Person;
import com.epam.cdp.ws.model.Ticket;
import com.epam.cdp.ws.model.TicketState;

@Component
public class BookingServiceImpl implements BookingService {

    private static final Double DEFAULT_PRICE = 23.99;

    private PersonDao personDao;
    private TicketDao ticketDao;

    @Autowired
    public BookingServiceImpl(final PersonDao personDao, final TicketDao ticketDao) {
        this.personDao = personDao;
        this.ticketDao = ticketDao;
    }

    /**
     * Create new person if it doesn't exist yet.
     */
    @Override
    public Person getPassenger(final String firstName, final String secondName, final LocalDate birthday) {
        Person person = personDao.find(firstName, secondName, birthday);
        if (Objects.isNull(person)) {
            person = personDao.create(firstName, secondName, birthday);
        }
        return person;
    }

    @Override
    public Long bookTicket(final String departureCity, final String arrivalCity, final LocalDateTime departureDate, final LocalDateTime arrivalDate, final Person passenger) {
        Ticket ticket = ticketDao.generate(departureCity, arrivalCity, departureDate, arrivalDate);
        ticket.setPrice(DEFAULT_PRICE);
        ticket.setState(TicketState.BOOKED);
        ticket.setPassenger(passenger);
        return ticket.getNumber();
    }

    @Override
    public boolean payTicket(final Long ticketNumber) {
        Ticket ticket = ticketDao.find(ticketNumber);
        if (Objects.isNull(ticket) || !Objects.equals(ticket.getState(), TicketState.BOOKED)) {
            return false;
        }
        ticket.setState(TicketState.PAID);
        return true;
    }

    @Override
    public boolean returnTicket(final Long ticketNumber) {
        Ticket ticket = ticketDao.find(ticketNumber);
        if (Objects.isNull(ticket)) {
            return false;
        }
        ticketDao.remove(ticket);
        return true;
    }

    @Override
    public Ticket findTicket(final Long ticketNumber) {
        return ticketDao.find(ticketNumber);
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(final PersonDao personDao) {
        this.personDao = personDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(final TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }
}
