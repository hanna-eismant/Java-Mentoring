
package com.epam.cdp.ws.api.generate;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ticketState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ticketState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BOOKED"/>
 *     &lt;enumeration value="PAID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ticketState", namespace = "http://epam.com/cdp/ws/api/soap")
@XmlEnum
public enum TicketState {

    BOOKED,
    PAID;

    public String value() {
        return name();
    }

    public static TicketState fromValue(String v) {
        return valueOf(v);
    }

}
