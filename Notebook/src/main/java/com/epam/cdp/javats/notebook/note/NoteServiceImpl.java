package com.epam.cdp.javats.notebook.note;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class NoteServiceImpl implements NoteService {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    private NoteDao noteDao;

    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Note create(String tag, String text) {
        Date date = new Date();
        final DateTime creationDate = new DateTime(date);
        Document document = noteDao.create(creationDate.toString(formatter), tag, text);
        return buildNoteObject(document);
    }

    @Override
    public List<Note> findAll() {
        return noteDao.findAll().stream().map(this::buildNoteObject).collect(Collectors.toList());
    }

    @Override
    public List<Note> findByTag(String tag) {
        return noteDao.findByTag(tag).stream().map(this::buildNoteObject).collect(Collectors.toList());
    }

    @Override
    public List<Note> findByText(String text) {
        return noteDao.findByText(text).stream().map(this::buildNoteObject).collect(Collectors.toList());
    }

    @Override
    public void removeAll() {
        noteDao.removeAll();
    }

    @Override
    public void index() {
        noteDao.index();
    }

    @Override
    public void remove(final Note note) {
        noteDao.remove(note.id);
    }

    private Note buildNoteObject(Document document) {
        if (document == null) {
            return null;
        }

        Note note = new Note();
        note.id = document.getObjectId(NoteDao.ID_FIELD);
        note.creationDate = document.getString(NoteDao.CREATION_DATE_FIELD);
        note.tag = document.getString(NoteDao.TAG_FIELD);
        note.text = document.getString(NoteDao.TEXT_FIELD);

        return note;
    }
}
