package com.epam.cdp.ws.model;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

public class Person {

    private String firstName;
    private String secondName;
    private LocalDate birthday;

    public Person() {
    }

    public Person(final String firstName, final String secondName, final LocalDate birthday) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(getFirstName(), person.getFirstName()) &&
                Objects.equal(getSecondName(), person.getSecondName()) &&
                Objects.equal(getBirthday(), person.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFirstName(), getSecondName(), getBirthday());
    }
}
