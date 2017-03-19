package com.epam.cdp.ws.dao;

import org.joda.time.LocalDate;

import com.epam.cdp.ws.model.Person;

public interface PersonDao {

    Person find(String firstName, String secondName, LocalDate birthday);

    Person create(String firstName, String secondName, LocalDate birthday);
}
