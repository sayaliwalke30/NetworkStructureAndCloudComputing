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
	private String title;
	private String announcementText;
	private String boardId;
	
	public Announcements() {}
	
	
	
	public Announcements(String annoucementId, String boardId, String title, String announcementText2) {
		// TODO Auto-generated constructor stub
		this.announcementId=annoucementId;
		this.boardId=boardId;
		this.announcementText=announcementText2;
		this.title=title;
	}



	@DynamoDBHashKey(attributeName="Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@DynamoDBAttribute(attributeName="title")
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@DynamoDBIndexRangeKey(attributeName="announcementId", globalSecondaryIndexName="annoucementId-index")
	//@DynamoDBIndexRangeKey
	public String getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}
	@DynamoDBAttribute(attributeName="announcementText")
	public String getAnnnouncementText() {
		return announcementText;
	}

	public void setAnnnouncementText(String annnouncementText) {
		this.announcementText = annnouncementText;
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
		return "AnnoucementId:"+getAnnouncementId()+", Message:"+getAnnnouncementText();
	}
	
}
