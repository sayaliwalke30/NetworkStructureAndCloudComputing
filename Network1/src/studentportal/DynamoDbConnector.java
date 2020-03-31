package studentportal;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {
	static AmazonDynamoDB dynamoDb ;
	 
	 public static void init() {
		 System.out.println(System.getenv("AWS_CREDENTIAL_PROFILES_FILE"));
		if (dynamoDb == null) {
		//ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		//credentialsProvider.getCredentials();
		
		dynamoDb = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
					.withRegion("us-west-2")
					.build();		
		System.out.println("I created the client");
		} 

	}
	
	 public AmazonDynamoDB getClient() {
		 
		 return dynamoDb;
	 }
}
