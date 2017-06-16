package com.epam.cdp.javats.notebook;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConfiguration {

    private String databaseName;
    private MongoDatabase database;
    private MongoClient mongoClient;

    public MongoConfiguration(String databaseName) {
        this.databaseName = databaseName;
    }

    public void init() {
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase(databaseName);
    }

    public void destroy() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);

    }
}
