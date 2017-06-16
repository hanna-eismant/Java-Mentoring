package com.epam.cdp.javats.notebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.cdp.javats.notebook.note.Note;
import com.epam.cdp.javats.notebook.note.NoteService;

public class Runner {

    private static NoteService noteService;
    private static List<Note> notes = new ArrayList<>();
    private static Scanner scanner;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        noteService = context.getBean(NoteService.class);

        createSampleNotes();
        indexNotes();

        Integer selectedOption = null;
        scanner = new Scanner(System.in);
        while (!Objects.equals(selectedOption, 0)) {
            System.out.println("\nOptions: \n0 - exit \n1 - show all notes \n2 - find notes by tag \n3 - find notes by tag and text \n4 - remove note");
            selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 0:
                    clearData();
                    break;
                case 1:
                    showAll();
                    break;
                case 2:
                    System.out.println("Search criteria:");
                    searchTag();
                    break;
                case 3:
                    System.out.println("Search criteria:");
                    fullTextSearch();
                    break;
                case 4:
                    System.out.println("Number note to remove:");
                    remove();
                    break;
                default:
                    break;
            }
        }
    }

    private static void remove() {
        final int index = scanner.nextInt();
        final Note note = notes.get(index);
        noteService.remove(note);
        System.out.print("Follow note is removed: ");
        System.out.println(note);
        notes = new ArrayList<>();
    }

    private static void indexNotes() {
        noteService.index();
    }

    private static void fullTextSearch() {
        final String text = scanner.next();
        notes = noteService.findByText(text);
        print(notes);
    }

    private static void clearData() {
        noteService.removeAll();
    }

    private static void searchTag() {
        final String tag = scanner.next();
        notes = noteService.findByTag(tag);
        print(notes);
    }

    private static void showAll() {
        notes = noteService.findAll();
        print(notes);
    }

    private static void print(final List<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println(i + ": " + note);
        }
    }

    private static void createSampleNotes() {
        noteService.create("test", "Hello World!");
        noteService.create("samples", "test");
        noteService.create("test", "Hello, Tatsiana!");
        noteService.create("samples", "It't my World!");
        noteService.create("work", "Do homework");
        noteService.create("hello", "Make perfect release");
        noteService.create("work", "Lorem ipsum...");
    }

}
