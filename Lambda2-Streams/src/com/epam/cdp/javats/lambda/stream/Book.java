package com.epam.cdp.javats.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {

    private String title;
    private List<Author> authors = new ArrayList<>();
    private Integer numberOfPages;

    public Book(String title, Integer numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(numberOfPages, book.numberOfPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, numberOfPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
