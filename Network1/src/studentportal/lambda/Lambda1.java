package studentportal.lambda;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import studentportal.DynamoDbConnector;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import studentportal.datamodel.Board;
import studentportal.datamodel.Course;
import studentportal.services.BoardService;
import studentportal.services.CourseService;

public class Lambda1 implements RequestHandler<Course, String> {
	/*private static AmazonDynamoDB db=AmazonDynamoDBClientBuilder
			.standard()
			.withRegion(Regions.US_WEST_2)
			.withCredentials(new EnvironmentVariableCredentialsProvider())
			.build();
	private static AmazonSNS sns=AmazonSNSClientBuilder
			.standard()
			.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
			.withRegion(Regions.US_WEST_2)
			.build();*/

	@Override
	public String handleRequest(Course event, Context context) {
		System.out.println("Start to trigger.");
		
		
				//find boardId, then use boardId to get courseId 
//				String boardId=item.get("boardId").getS();
//				System.out.println("BoardId:"+boardId);
//				
//				Board board=new BoardService().getOneBoard(boardId);
				String courseId = event.getCourseId();
				System.out.println("CourseId:"+courseId);
				
				Course course=new CourseService().getCourse(courseId);
				if (course.getBoardId() != null && course.getRoster().size() > 0 && 
						course.getNotificationTopic() != null)
				{
					System.out.println(" The boardId,roaster,notification are not empty");
					return "old record";
				}

				
				/*//find topicArn using courseId
				String topicArn="";
				Map<String, AttributeValue> item2=new HashMap<>();
				item2.put(":val", new AttributeValue().withS(courseId));
				ScanRequest scanRequest=new ScanRequest()
						.withTableName("Course")
						.withFilterExpression("courseId=:val")
						.withExpressionAttributeValues(item2);
				//ScanResult scanResult=db.scan(scanRequest);
				List<Map<String,AttributeValue>> result=scanResult.getItems();
				for(Map<String,AttributeValue>i:result) {
					if(i!=null) {
						topicArn=i.get("notificationTopic").getS();
					}
				}
				
				context.getLogger().log(courseId);
				context.getLogger().log(topicArn);
				
				String message="A new annoucement has been published for course "+courseId;
				context.getLogger().log(message);
				
				//publish to sns topic
				PublishRequest publishRequest=new PublishRequest()
						.withMessage(message)
						.withSubject("Course "+courseId+" Announcement")
						.withTargetArn(topicArn);
				
				//PublishResult publishResult=sns.publish(publishRequest);*/
				
					
		return "Successfully sent records: ";
}
}
