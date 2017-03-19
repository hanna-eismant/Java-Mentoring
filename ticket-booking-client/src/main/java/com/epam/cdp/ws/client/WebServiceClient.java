package com.epam.cdp.ws.client;

import java.math.BigInteger;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.epam.cdp.ws.client.generate.BookTicketRequest;
import com.epam.cdp.ws.client.generate.BookTicketResponse;
import com.epam.cdp.ws.client.generate.GetTicketRequest;
import com.epam.cdp.ws.client.generate.GetTicketResponse;
import com.epam.cdp.ws.client.generate.ObjectFactory;
import com.epam.cdp.ws.client.generate.PayTicketRequest;
import com.epam.cdp.ws.client.generate.PayTicketResponse;
import com.epam.cdp.ws.client.generate.ReturnTicketRequest;
import com.epam.cdp.ws.client.generate.ReturnTicketResponse;
import com.epam.cdp.ws.client.generate.SoapPerson;

public class WebServiceClient extends WebServiceGatewaySupport {
    private static final String SOAP_URI = "http://localhost:8080/api/soap";
    private ObjectFactory objectFactory = new ObjectFactory();

    public BookTicketResponse bookTicket(String departureCity, String arrivalCity,
                                         LocalDateTime departureDate, LocalDateTime arrivalDate,
                                         String firstName, String secondName,
                                         LocalDate birthday) {

        final BookTicketRequest request = objectFactory.createBookTicketRequest();
        request.setDepartureCity(departureCity);
        request.setArrivalCity(arrivalCity);
        request.setDepartureDate(departureDate.toString());
        request.setArrivalDate(arrivalDate.toString());

        final SoapPerson soapPerson = objectFactory.createSoapPerson();
        soapPerson.setFirstName(firstName);
        soapPerson.setSecondName(secondName);
        soapPerson.setBirthday(birthday.toString());

        request.setPassenger(soapPerson);

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        BookTicketResponse response = (BookTicketResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);
        return response;
    }

    public GetTicketResponse getTicket(final Long ticketNumber) {
        final GetTicketRequest request = objectFactory.createGetTicketRequest();
        request.setTicketNumber(BigInteger.valueOf(ticketNumber));
        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetTicketResponse response = (GetTicketResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);
        return response;
    }

    public PayTicketResponse payTicket(final Long ticketNumber) {
        final PayTicketRequest request = objectFactory.createPayTicketRequest();
        request.setTicketNumber(BigInteger.valueOf(ticketNumber));
        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        PayTicketResponse response = (PayTicketResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);
        return response;
    }

    public ReturnTicketResponse returnTicket(final Long ticketNumber) {
        final ReturnTicketRequest request = objectFactory.createReturnTicketRequest();
        request.setTicketNumber(BigInteger.valueOf(ticketNumber));
        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        ReturnTicketResponse response = (ReturnTicketResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);
        return response;
    }
}
