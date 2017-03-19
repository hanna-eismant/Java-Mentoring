package com.epam.cdp.ws.api;

import java.math.BigInteger;
import java.util.Objects;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.cdp.ws.api.generate.BookTicketRequest;
import com.epam.cdp.ws.api.generate.BookTicketResponse;
import com.epam.cdp.ws.api.generate.GetTicketRequest;
import com.epam.cdp.ws.api.generate.GetTicketResponse;
import com.epam.cdp.ws.api.generate.ObjectFactory;
import com.epam.cdp.ws.api.generate.PayTicketRequest;
import com.epam.cdp.ws.api.generate.PayTicketResponse;
import com.epam.cdp.ws.api.generate.ReturnTicketRequest;
import com.epam.cdp.ws.api.generate.ReturnTicketResponse;
import com.epam.cdp.ws.api.generate.SoapPerson;
import com.epam.cdp.ws.api.generate.SoapTicket;
import com.epam.cdp.ws.model.Person;
import com.epam.cdp.ws.model.Ticket;
import com.epam.cdp.ws.model.TicketState;
import com.epam.cdp.ws.service.BookingService;

@Endpoint
public class TicketEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com/cdp/ws/api/soap";

    private BookingService bookingService;
    private ObjectFactory soapObjectFactory = new ObjectFactory();

    @Autowired
    public TicketEndpoint(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "bookTicketRequest")
    @ResponsePayload
    public BookTicketResponse bookTicket(@RequestPayload final BookTicketRequest bookTicketRequest) {
        final SoapPerson soapPassenger = bookTicketRequest.getPassenger();
        final LocalDate birthday = LocalDate.parse(soapPassenger.getBirthday());
        Person passenger = bookingService.getPassenger(soapPassenger.getFirstName(), soapPassenger.getSecondName(), birthday);

        final LocalDateTime departureDate = LocalDateTime.parse(bookTicketRequest.getDepartureDate());
        final LocalDateTime arrivalDate = LocalDateTime.parse(bookTicketRequest.getArrivalDate());
        final Long ticketNumber = bookingService.bookTicket(bookTicketRequest.getDepartureCity(), bookTicketRequest.getArrivalCity(),
                departureDate, arrivalDate, passenger);

        final BookTicketResponse bookTicketResponse = soapObjectFactory.createBookTicketResponse();
        bookTicketResponse.setTicketNumber(BigInteger.valueOf(ticketNumber));

        return bookTicketResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTicketRequest")
    @ResponsePayload
    public GetTicketResponse getTicket(@RequestPayload final GetTicketRequest getTicketRequest) {
        Ticket ticket = bookingService.findTicket(getTicketRequest.getTicketNumber().longValue());
        SoapTicket soapTicket = null;
        final GetTicketResponse getTicketResponse = soapObjectFactory.createGetTicketResponse();
        if (Objects.nonNull(ticket)) {
            soapTicket = convertTicket(ticket);
            getTicketResponse.setTicket(soapTicket);
        }
        return getTicketResponse;
    }

    private SoapTicket convertTicket(final Ticket ticket) {
        final SoapTicket soapTicket = soapObjectFactory.createSoapTicket();
        soapTicket.setDepartureCity(ticket.getDepartureCity());
        soapTicket.setArrivalCity(ticket.getArrivalCity());
        soapTicket.setDepartureDate(ticket.getDepartureDate().toString());
        soapTicket.setArrivalDate(ticket.getArrivalDate().toString());
        soapTicket.setNumber(BigInteger.valueOf(ticket.getNumber()));
        soapTicket.setPrice(ticket.getPrice());

        if (Objects.equals(ticket.getState(), TicketState.BOOKED)) {
            soapTicket.setState(com.epam.cdp.ws.api.generate.TicketState.BOOKED);
        } else if (Objects.equals(ticket.getState(), TicketState.PAID)) {
            soapTicket.setState(com.epam.cdp.ws.api.generate.TicketState.PAID);
        }

        final Person passenger = ticket.getPassenger();
        SoapPerson soapPerson = null;
        if (Objects.nonNull(passenger)) {
            soapPerson = soapObjectFactory.createSoapPerson();
            soapPerson.setFirstName(passenger.getFirstName());
            soapPerson.setSecondName(passenger.getSecondName());
            soapPerson.setBirthday(passenger.getBirthday().toString());
        }

        soapTicket.setPassenger(soapPerson);
        return soapTicket;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "payTicketRequest")
    @ResponsePayload
    public PayTicketResponse payTicket(@RequestPayload final PayTicketRequest payTicketRequest) {
        boolean status = bookingService.payTicket(payTicketRequest.getTicketNumber().longValue());
        final PayTicketResponse payTicketResponse = soapObjectFactory.createPayTicketResponse();
        payTicketResponse.setSuccess(status);
        return payTicketResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "returnTicketRequest")
    @ResponsePayload
    public ReturnTicketResponse returnTicket(@RequestPayload final ReturnTicketRequest returnTicketRequest) {
        boolean status = bookingService.returnTicket(returnTicketRequest.getTicketNumber().longValue());
        final ReturnTicketResponse returnTicketResponse = soapObjectFactory.createReturnTicketResponse();
        returnTicketResponse.setSuccess(status);
        return returnTicketResponse;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(final BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
