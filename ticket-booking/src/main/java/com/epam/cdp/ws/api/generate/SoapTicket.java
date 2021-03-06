
package com.epam.cdp.ws.api.generate;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for soapTicket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="soapTicket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="departureCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arrivalCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departureDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="arrivalDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="state" type="{http://epam.com/cdp/ws/api/soap}ticketState"/>
 *         &lt;element name="passenger" type="{http://epam.com/cdp/ws/api/soap}soapPerson" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soapTicket", namespace = "http://epam.com/cdp/ws/api/soap", propOrder = {
    "number",
    "departureCity",
    "arrivalCity",
    "departureDate",
    "arrivalDate",
    "price",
    "state",
    "passenger"
})
public class SoapTicket {

    @XmlElement(required = true)
    protected BigInteger number;
    @XmlElement(required = true)
    protected String departureCity;
    @XmlElement(required = true)
    protected String arrivalCity;
    @XmlElement(required = true)
    protected String departureDate;
    @XmlElement(required = true)
    protected String arrivalDate;
    protected double price;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TicketState state;
    protected SoapPerson passenger;

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Gets the value of the departureCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureCity() {
        return departureCity;
    }

    /**
     * Sets the value of the departureCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureCity(String value) {
        this.departureCity = value;
    }

    /**
     * Gets the value of the arrivalCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalCity() {
        return arrivalCity;
    }

    /**
     * Sets the value of the arrivalCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalCity(String value) {
        this.arrivalCity = value;
    }

    /**
     * Gets the value of the departureDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the value of the departureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureDate(String value) {
        this.departureDate = value;
    }

    /**
     * Gets the value of the arrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the value of the arrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalDate(String value) {
        this.arrivalDate = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link TicketState }
     *     
     */
    public TicketState getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link TicketState }
     *     
     */
    public void setState(TicketState value) {
        this.state = value;
    }

    /**
     * Gets the value of the passenger property.
     * 
     * @return
     *     possible object is
     *     {@link SoapPerson }
     *     
     */
    public SoapPerson getPassenger() {
        return passenger;
    }

    /**
     * Sets the value of the passenger property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoapPerson }
     *     
     */
    public void setPassenger(SoapPerson value) {
        this.passenger = value;
    }

}
