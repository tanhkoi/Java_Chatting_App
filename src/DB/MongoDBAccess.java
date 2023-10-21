package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class MongoDBAccess {

    private String connectionString;
    private String databaseName;
    private String collectionName;

    public MongoDBAccess() {
        try {
            connectionString = "mongodb://localhost:27017";
            databaseName = "JavaChattingApp";
            collectionName = "Users";
        } catch (Exception e) {
        }
    }

    public int Login(String inputUsername, String inputPassword) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document userDocument = collection.find(new Document("username", inputUsername)).first();

            if (userDocument != null) {

                String storedPassword = userDocument.getString("password");
                if (storedPassword.equals(inputPassword)) {
                    return 1; // dang nhap thanh cong
                } else {
                    return -1; // sai mat khau
                }
            } else {
                return -2; // sai username
            }
        }
    }

    public int Register(String inputUsername, String email, String inputPassword) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document existingUser = collection.find(new Document("username", inputUsername)).first();
            if (existingUser != null) {
                return -1; // username da ton tai
            }

            Document newUser = new Document()
                    .append("username", inputUsername)
                    .append("email", email)
                    .append("password", inputPassword);

            collection.insertOne(newUser);

            return 1; // dang ky thanh cong
        }
    }

    public List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("JavaChattingApp");
            MongoCollection<Document> usersCollection = database.getCollection("Users");

            Document query = new Document("isOnline", true);
            try (MongoCursor<Document> cursor = usersCollection.find(query).iterator()) {
                while (cursor.hasNext()) {
                    Document user = cursor.next();
                    onlineUsers.add(user.getString("username"));
                }
            }
        }
        return onlineUsers;
    }

    public void turnOffOnline(String username) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("JavaChattingApp");
            MongoCollection<Document> collection = database.getCollection("Users");

            Document userDocument = collection.find(new Document("username", username)).first();

            if (userDocument != null) {
                userDocument.put("isOnline", false);

                collection.replaceOne(new Document("username", username), userDocument);
            }
        }
    }
    
    
    public void turnOnOnline(String username) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("JavaChattingApp");
            MongoCollection<Document> collection = database.getCollection("Users");

            Document userDocument = collection.find(new Document("username", username)).first();

            if (userDocument != null) {
                userDocument.put("isOnline", true);

                collection.replaceOne(new Document("username", username), userDocument);
            }
        }
    }

}
