package com.epam.cdp.javats.notebook.note;

import java.util.List;

public interface NoteService {

    Note create(String tag, String text);

    List<Note> findAll();

    List<Note> findByTag(String tag);

    List<Note> findByText(String text);

    void removeAll();

    void index();

    void remove(Note note);
}
