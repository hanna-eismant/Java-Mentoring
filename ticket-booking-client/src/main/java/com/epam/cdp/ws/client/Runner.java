package com.epam.cdp.ws.client;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.cdp.ws.client.generate.BookTicketResponse;
import com.epam.cdp.ws.client.generate.GetTicketResponse;
import com.epam.cdp.ws.client.generate.PayTicketResponse;
import com.epam.cdp.ws.client.generate.ReturnTicketResponse;
import com.epam.cdp.ws.client.generate.SoapPerson;
import com.epam.cdp.ws.client.generate.SoapTicket;

public class Runner {

    private static Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WebServiceClient webServiceClient = context.getBean(WebServiceClient.class);

        String departureCity = "Hrodna";
        String arrivalCity = "Minsk";
        LocalDateTime departureDate = LocalDateTime.parse("2017-03-20T09:00:00");
        LocalDateTime arrivalDate = LocalDateTime.parse("2017-03-20T16:00:00");

        String firstName = "Hanna";
        String secondName = "Eismant";
        LocalDate birthday = LocalDate.parse("1987-12-15");

        final BookTicketResponse bookTicketResponse = webServiceClient.bookTicket(departureCity, arrivalCity, departureDate,
                arrivalDate, firstName, secondName, birthday);

        final Long ticketNumber = bookTicketResponse.getTicketNumber().longValue();

        LOGGER.info("Book ticket with number: " + ticketNumber);

        GetTicketResponse getTicketResponse = webServiceClient.getTicket(ticketNumber);
        printTicketInfo(getTicketResponse);

        PayTicketResponse payTicketResponse = webServiceClient.payTicket(ticketNumber);
        LOGGER.info("Pay success: " + payTicketResponse.isSuccess());
        getTicketResponse = webServiceClient.getTicket(ticketNumber);
        printTicketInfo(getTicketResponse);

        ReturnTicketResponse returnTicketResponse = webServiceClient.returnTicket(ticketNumber);
        LOGGER.info("Return success: " + returnTicketResponse.isSuccess());
        getTicketResponse = webServiceClient.getTicket(ticketNumber);
        printTicketInfo(getTicketResponse);
    }

    private static void printTicketInfo(final GetTicketResponse getTicketResponse) {
        LOGGER.info("Ticket Info:");
        final SoapTicket soapTicket = getTicketResponse.getTicket();
        if (Objects.isNull(soapTicket)) {
            LOGGER.info("Ticket Info is empty");
            return;
        }

        LOGGER.info("Number: " + soapTicket.getNumber());
        LOGGER.info("Departure City: " + soapTicket.getDepartureCity());
        LOGGER.info("Arrival City: " + soapTicket.getArrivalCity());
        LOGGER.info("Departure Date: " + soapTicket.getDepartureDate());
        LOGGER.info("Arrival Date: " + soapTicket.getArrivalDate());
        LOGGER.info("Price: " + soapTicket.getPrice());
        LOGGER.info("State: " + soapTicket.getState());

        final SoapPerson passenger = soapTicket.getPassenger();

        LOGGER.info("Passenger first name: " + passenger.getFirstName());
        LOGGER.info("Passenger second name: " + passenger.getSecondName());
        LOGGER.info("Passenger birthday: " + passenger.getBirthday());
    }
}
