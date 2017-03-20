package mongodb.test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {

	/* http://mongodb.github.io/mongo-java-driver/2.13/getting-started/quick-tour/:

	The MongoClient class is designed to be thread safe and shared among threads. 
	Typically you create only 1 instance for a given database cluster and use it 
	across your application.
	
    All resource usage limits (max connections, etc) apply per MongoClient instance
    To dispose of an instance, make sure you call MongoClient.close() to clean up resources

    Authentication (Optional):
		MongoCredential credential = MongoCredential.createCredential(userName, database, password);
		MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
	*/
	
	private final static MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

	private final static String DATABASE_NAME = "mongo_test";
	
	
	public void save(Object data) {
		MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
		db.
	}
}
