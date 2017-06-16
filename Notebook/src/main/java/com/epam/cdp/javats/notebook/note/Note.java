package com.epam.cdp.javats.notebook.note;

import org.bson.types.ObjectId;

public class Note {

    public ObjectId id;
    public String creationDate;
    public String tag;
    public String text;

    public Note() {
    }

    public Note(String creationDate, String tag, String text) {
        this.creationDate = creationDate;
        this.tag = tag;
        this.text = text;
    }

    @Override
    public String toString() {
        return "date: '" + creationDate +
                ", tag: '" + tag + '\'' +
                ", text: '" + text + '\'' ;
    }
}
