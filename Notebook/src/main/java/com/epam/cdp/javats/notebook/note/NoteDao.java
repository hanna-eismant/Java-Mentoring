package com.epam.cdp.javats.notebook.note;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public interface NoteDao {

    String COLLECTION_NAME = "notes";
    String ID_FIELD = "_id";
    String CREATION_DATE_FIELD = "creationDate";
    String TAG_FIELD = "tag";
    String TEXT_FIELD = "text";

    Document create(String creationDate, String tag, String text);

    List<Document> findAll();

    List<Document> findByTag(String tag);

    List<Document> findByText(String text);

    void removeAll();

    void index();

    void remove(ObjectId id);
}
