package com.epam.cdp.javats.lambda.functionalinterface;

@FunctionalInterface
public interface MyInterface {

    void apply(Person person);

    default boolean isAdult(Person person) {
        return 18 <= person.getAge();
    }

    default Person createPerson(String name, Integer age) {
        return new Person(name, age);
    }

    static void printInfo(Person person) {
        System.out.println(person);
    }

}