package com.epam.cdp.javats.lambda.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    public static void main(String[] args) {
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Author author1 = new Author("Martin", (short) 23);
        authors.add(author1);
        Author author2 = new Author("Yulia", (short) 21);
        authors.add(author2);
        Book book1 = new Book("Super Book", 259);
        book1.getAuthors().add(author1);
        book1.getAuthors().add(author2);
        books.add(book1);

        Author author3 = new Author("George", (short) 32);
        authors.add(author3);
        Book book2 = new Book("How to get a Million's", 137);
        book2.getAuthors().add(author3);
        books.add(book2);

        Author author4 = new Author("Hanna", (short) 23);
        authors.add(author4);
        Book book3 = new Book("About Cats", 500);
        book3.getAuthors().add(author4);
        books.add(book3);

        Book book4 = new Book("Fairy Tail", 40);
        book4.getAuthors().add(author1);
        book4.getAuthors().add(author3);
        books.add(book4);

        long more200pages = books
                .stream()
                .filter(b -> 200 > b.getNumberOfPages())
                .count();
        System.out.println(String.format("%nBooks with more than 200 pages: %s", more200pages));

        Optional<Book> minPagesOpt = books
                .stream()
                .min(Comparator.comparing(Book::getNumberOfPages));

        String minPages = minPagesOpt.isPresent() ? minPagesOpt.get().toString() : "no";
        System.out.println(String.format("%nBook with min pages: %s", minPages));

        Book maxPages = books
                .stream()
                .max(Comparator.comparing(Book::getNumberOfPages))
                .get();
        System.out.println(String.format("%nBook with min pages: %s", maxPages));

        System.out.println(String.format("%nBooks with single author:"));
        books.stream()
                .filter(b -> 1 == b.getAuthors().size())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println(String.format("%nSorted list of books:"));
        books.parallelStream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);

        List<String> titles = books
                .stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
        System.out.println(String.format("%nAll titles:"));
        titles.forEach(System.out::println);

        System.out.println(String.format("%nDistinct authors:"));
        books.stream()
                .map(Book::getAuthors)
                .flatMap(l -> l.stream())
                .distinct()
                .forEach(System.out::println);

        System.out.println(String.format("%nGet total sum pages of books with more than 1 author"));
        Integer sum = books.stream()
                .filter(b -> 1 >= b.getAuthors().size())
                .peek(b -> System.out.println(String.format("Sorted book: %s", b)))
                .map(b -> b.getNumberOfPages())
                .reduce(0, Integer::sum);
        System.out.println(String.format("Total sum is %s", sum));

        System.out.println(String.format("%nBiggest book of %s", author3.getName()));
        Optional<Book> biggestOpt = books.stream()
                .filter(b -> b.getAuthors().contains(author3))
                .max(Comparator.comparing(Book::getNumberOfPages));

        Book biggest = biggestOpt.isPresent() ? biggestOpt.get() : null;
        System.out.println(String.format("Result: %s", biggest));

    }
}
