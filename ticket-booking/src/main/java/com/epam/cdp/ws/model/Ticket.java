package com.epam.cdp.ws.model;

import org.joda.time.LocalDateTime;

import com.google.common.base.Objects;

public class Ticket {

    private Long number;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Double price;
    private TicketState state;
    private Person passenger;

    public Ticket(final Long number, final String departureCity, final String arrivalCity, final LocalDateTime departureDate, final LocalDateTime arrivalDate) {
        this.number = number;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(final Long number) {
        this.number = number;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(final String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(final String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(final LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(final TicketState state) {
        this.state = state;
    }

    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(final Person passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equal(getNumber(), ticket.getNumber()) &&
                Objects.equal(getDepartureCity(), ticket.getDepartureCity()) &&
                Objects.equal(getArrivalCity(), ticket.getArrivalCity()) &&
                Objects.equal(getDepartureDate(), ticket.getDepartureDate()) &&
                Objects.equal(getArrivalDate(), ticket.getArrivalDate()) &&
                Objects.equal(getPrice(), ticket.getPrice()) &&
                getState() == ticket.getState() &&
                Objects.equal(getPassenger(), ticket.getPassenger());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumber(), getDepartureCity(), getArrivalCity(), getDepartureDate(), getArrivalDate(), getPrice(), getState(), getPassenger());
    }
}
