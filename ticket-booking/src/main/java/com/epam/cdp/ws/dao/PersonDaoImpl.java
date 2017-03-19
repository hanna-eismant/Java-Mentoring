package com.epam.cdp.ws.dao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import com.epam.cdp.ws.model.Person;

@Component
public class PersonDaoImpl implements PersonDao {

    private Set<Person> storage = new HashSet<>();

    @Override
    public Person find(final String firstName, final String secondName, final LocalDate birthday) {
        return storage.stream().filter(person ->
                Objects.equals(person.getFirstName(), firstName)
                        && Objects.equals(person.getSecondName(), secondName)
                        && Objects.equals(person.getBirthday(), birthday
                )).findFirst().orElse(null);
    }

    @Override
    public Person create(final String firstName, final String secondName, final LocalDate birthday) {
        Person person = new Person(firstName, secondName, birthday);
        storage.add(person);
        return person;
    }
}
