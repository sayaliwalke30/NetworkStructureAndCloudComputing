package studentportal.datamodel;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Announcements")
@XmlRootElement
public class Announcements {
	private String Id;
	private String announcementId;
	private String announcementText;
	private String boardId;
	
	public Announcements() {}
	
	
	
	public Announcements(String annoucementId, String boardId,String announcementText) {
		// TODO Auto-generated constructor stub
		this.announcementId=annoucementId;
		this.boardId=boardId;
		this.announcementText=announcementText;
	}



	@DynamoDBHashKey(attributeName="Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}


	@DynamoDBIndexRangeKey(attributeName="announcementId", globalSecondaryIndexName="boardId-annoucementId-index")
	public String getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}
	
	@DynamoDBAttribute(attributeName="announcementText")
	public String getAnnouncementText() {
		return announcementText;
	}

	public void setAnnouncementText(String announcementText) {
		this.announcementText = announcementText;
	}

	@DynamoDBIndexHashKey(attributeName="boardId",globalSecondaryIndexName="boardId-annoucementId-index")
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	@DynamoDBIgnore
	@Override
	public String toString() {
		return "AnnoucementId:"+getAnnouncementId()+", Message:"+getAnnouncementText();
	}
	
}
