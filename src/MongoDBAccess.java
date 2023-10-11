
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

}
