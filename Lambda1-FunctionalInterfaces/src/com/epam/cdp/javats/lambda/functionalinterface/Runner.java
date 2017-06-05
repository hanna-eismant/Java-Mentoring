package com.epam.cdp.javats.lambda.functionalinterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Runner {

    public static void main(String[] args) {

        Consumer<Person> printInfo = person -> System.out.println(String.format("Person %s, %s", person.getName(), person.getAge()));
        Function<Person, String> getNameFunction = person-> person.getName();
        Predicate<Person> isAdult = person -> 18 <= person.getAge();
        Supplier<Person> createPerson = () -> new Person("Jon", 13);

        Comparator<Person> nameComparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Person> ageComparator = (p1, p2) -> p1.getAge().compareTo(p2.getAge());

        List<Person> persons = new ArrayList<>(3);

        Person hanna = new Person("Hanna", 28);
        persons.add(hanna);

        Person tatsiana = new Person("Tatsiana", 24);
        persons.add(tatsiana);

        Person alexey = new Person("Alexey", 29);
        persons.add(alexey);

        persons.add(createPerson.get());

        Runnable runnable = () -> {
            System.out.println("Sort by name");
            persons.sort(nameComparator);
            persons.forEach(MyInterface::printInfo);

            MyInterface lambdaImpl = person -> {
                System.out.println("Lambda impl");
                Integer age = person.getAge();
                age++;
                person.setAge(age);
            };

            lambdaImpl.apply(hanna);
            MyInterface.printInfo(hanna);
        };

        Runnable runnable1 = () -> {
            System.out.println("Sort by age");
            persons.sort(ageComparator);
            persons.forEach(MyInterface::printInfo);

            MyInterface classImpl = new MyInterface() {
                @Override
                public void apply(Person person) {
                    System.out.println("Inner class impl");
                    person.setAge(18);
                }
            };

            classImpl.apply(tatsiana);
            MyInterface.printInfo(tatsiana);
        };

        runnable.run();
        runnable1.run();
    }
}
