package main;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-03-21.
 */
public class MongoClientManager {
    private static MongoClientManager instance = new MongoClientManager();
    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;

    private MongoClientManager(){
        try {
            mongoClient = new MongoClient("localhost", 27017);
            WriteConcern w = new WriteConcern(1, 2000);
            mongoClient.setWriteConcern(w);
            db = mongoClient.getDB("words");
            coll = db.getCollection("words");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public static MongoClientManager getInstance(){
        return instance;
    }
    public void putData(ArrayList<String> wordList){
        for(String word : wordList){
            BasicDBObject oldObject = new BasicDBObject();
            oldObject.put("name", word);
            BasicDBObject nameObject = new BasicDBObject();
            nameObject.put("name", word);
            BasicDBObject newObject = new BasicDBObject();
            newObject.put("$set", nameObject);
            coll.update(oldObject, newObject, true, false);
        }
    }

    public DBCollection getCollection(){
        return coll;
    }
}
