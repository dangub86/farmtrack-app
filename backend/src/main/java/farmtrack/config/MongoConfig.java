package farmtrack.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.stereotype.Component;

/*
@Component
public class MongoConfig extends AbstractMongoConfiguration {

    private MongoClientURI getMongoUri() {

       // return new MongoClientURI(
         //       "mongodb://dangub86:Madhudugh4@farmtrackdb-shard-00-00-7hcrq.mongodb.net:27017,farmtrackdb-shard-00-01-7hcrq.mongodb.net:27017,farmtrackdb-shard-00-02-7hcrq.mongodb.net:27017/test?ssl=true&replicaSet=FarmTrackDB-shard-0&authSource=admin&retryWrites=true&w=majority");
       return new MongoClientURI(
               "mongodb+srv://dangub86:Madhudugh4@farmtrackdb-7hcrq.mongodb.net/farmtrack_db?retryWrites=true&w=majority");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(getMongoUri());
    }*/
/**//*


    @Override
    protected String getDatabaseName() {
        return "farmtrack_db";
    }

    public MongoDatabase getDatabase() {
        return mongoClient().getDatabase(getDatabaseName());
    }


}
*/
