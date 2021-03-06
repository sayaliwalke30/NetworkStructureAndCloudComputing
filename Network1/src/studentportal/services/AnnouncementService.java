package studentportal.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import studentportal.DynamoDbConnector;
import studentportal.datamodel.Announcements;
public class AnnouncementService {
	static DynamoDbConnector dbConnector;
	static DynamoDBMapper mapper;
	
	public AnnouncementService() {
		dbConnector=new DynamoDbConnector();
		dbConnector.init();
		mapper=new DynamoDBMapper(dbConnector.getClient());
	}
	//get all
	public List<Announcements> getAllAnnouncements() {
		return mapper.scan(Announcements.class, new DynamoDBScanExpression());
	}
	
	//get one
	public Announcements getOneAnnouncement(String announcementId,String boardId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val1", new AttributeValue().withS(announcementId));
		eav.put(":val2", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("announcementId=:val1 and boardId=:val2").withExpressionAttributeValues(eav);
		List<Announcements> result=mapper.scan(Announcements.class, scanExpression);
		if(result.size()!=0) {
			return result.get(0);
		}
		return null;
	}
	
	
	//get announcement by board
	public List<Announcements> getAnnoucementsByBoard(String boardId){
		Map<String,AttributeValue> eav=new HashMap<String, AttributeValue>();
		eav.put(":val", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("boardId=:val").withExpressionAttributeValues(eav);
		List<Announcements> result=mapper.scan(Announcements.class, scanExpression);
		if(result.size()==0) {
			return null;
		}
		return result;
	}
	
	
	//add annoucements
	public void addAnnouncement(String annoucementId,String boardId, String announcementText ) {
		if(announcementText.length()<=160) {
		Announcements newAnn=new Announcements(annoucementId,boardId,announcementText);
		mapper.save(newAnn);
		}
		else
			System.out.println("----Announcement Text oversize!");
	}
	
	public Announcements addAnnouncement(Announcements a) {
//		if(a.getAnnnouncementText().length()<=160) {
//			System.out.println("The object " + a.toString());
//			mapper.save(a);
//		}
		mapper.save(a);
		return mapper.load(Announcements.class,a.getId());
	}
	
	//delete annoucement
	public Announcements deleteAnnouncement(String announcementId,String boardId) {
		Map<String,AttributeValue> eav=new HashMap<>();
		eav.put(":val1", new AttributeValue().withS(announcementId));
		eav.put(":val2", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
				.withFilterExpression("announcementId=:val1 and boardId=:val2").withExpressionAttributeValues(eav);
		List<Announcements> result=mapper.scan(Announcements.class, scanExpression);
		if(result.size()!=0) {
			Announcements removedone=result.get(0);
			mapper.delete(removedone);
			return removedone;
		}
		return null;
	}
	
	//update
	public Announcements updateAnnouncement(String announcementId,String boardId,Announcements a) {
		deleteAnnouncement(a.getAnnouncementId(), a.getBoardId());
		mapper.save(a);
		return a;
	}
	
}
