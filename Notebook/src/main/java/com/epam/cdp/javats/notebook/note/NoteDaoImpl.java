package com.epam.cdp.javats.notebook.note;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.epam.cdp.javats.notebook.MongoConfiguration;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;

public class NoteDaoImpl implements NoteDao {

    private MongoConfiguration mongoConfiguration;
    private MongoCollection<Document> collection;

    public NoteDaoImpl(MongoConfiguration mongoConfiguration) {
        this.mongoConfiguration = mongoConfiguration;
    }

    public void init() {
        collection = mongoConfiguration.getCollection(COLLECTION_NAME);
    }

    @Override
    public Document create(String creationDate, String tag, String text) {
        // @formatter:off
        Document note = new Document()
                .append(CREATION_DATE_FIELD, creationDate.toString())
                .append(TAG_FIELD, tag)
                .append(TEXT_FIELD, text);
        // @formatter:on
        collection.insertOne(note);
        return note;
    }

    @Override
    public List<Document> findAll() {
        List<Document> documentList = new ArrayList<>();
        Consumer<Document> consumer = document -> documentList.add(document);
        collection.find().forEach(consumer);
        return documentList;
    }

    @Override
    public List<Document> findByTag(String tag) {
        List<Document> documentList = new ArrayList<>();
        Consumer<Document> consumer = document -> documentList.add(document);
        collection.find(eq(TAG_FIELD, tag)).forEach(consumer);
        return documentList;
    }

    @Override
    public List<Document> findByText(String text) {
        List<Document> documentList = new ArrayList<>();
        Consumer<Document> consumer = document -> documentList.add(document);
        collection.find(text(text)).forEach(consumer);
        return documentList;
    }

    @Override
    public void removeAll() {
        collection.deleteMany(exists(ID_FIELD));
    }

    @Override
    public void remove(final ObjectId id) {
        collection.deleteOne(eq(ID_FIELD, id));
    }

    @Override
    public void index() {
        collection.dropIndexes();
        IndexOptions indexOptions = new IndexOptions().unique(false);
        // @formatter:off
        Document index = new Document()
                .append(TEXT_FIELD, "text")
                .append(TAG_FIELD, "text");
        // @formatter:on
        collection.createIndex(index, indexOptions);
    }
}
