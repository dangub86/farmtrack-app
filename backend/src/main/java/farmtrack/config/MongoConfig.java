package farmtrack.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

public class MongoConfig extends AbstractMongoConfiguration {

    private MongoClientURI getMongoUri() {
        return new MongoClientURI(
                "mongodb+srv://dangub86:Madhudugh4@farmtrackdb-7hcrq.mongodb.net/test?retryWrites=true&w=majority");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(getMongoUri());
    }

    @Override
    protected String getDatabaseName() {
        return "farmtrack_db";
    }

    public MongoDatabase getDatabase() {
        return mongoClient().getDatabase(getDatabaseName());
    }


}
