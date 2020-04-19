package studentportal.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName = "Course")
@XmlRootElement
public class Course {
	private String Id;
	private String courseId;
	private String courseName;
	private String professorId;
	private String taId;
	private String department;
	private String boardId;
	private List<String> roster=new ArrayList<String>();//has registered student Id list
	private String notificationTopic;//New field for storing sns topic
	
	
	public Course() {}
	
	public Course(String courseId,String courseName,String department){
		this.courseId=courseId;
		this.courseName=courseName;
		this.department=department;
	}
	

	public Course(String courseId, String courseName, String professorId, String taId, String boardId, String department
			) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.professorId = professorId;
		this.taId = taId;
		this.department = department;
		this.boardId = boardId;
	}

	@DynamoDBHashKey(attributeName="id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	@DynamoDBIndexHashKey(attributeName="courseId",globalSecondaryIndexName="courseId-index")
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@DynamoDBAttribute(attributeName="courseName")
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@DynamoDBAttribute(attributeName="professorId")
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	
	@DynamoDBAttribute(attributeName="taId")
	public String getTaId() {
		return taId;
	}
	public void setTaId(String taId) {
		this.taId = taId;
	}
	
	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@DynamoDBAttribute(attributeName="boardId")
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	@DynamoDBAttribute(attributeName="roster")
	public List<String> getRoster() {
		return roster;
	}
	public void setRoster(List<String> roster) {
		this.roster = roster;
	}

	public String getNotificationTopic() {
		return notificationTopic;
	}

	public void setNotificationTopic(String notificationTopic) {
		this.notificationTopic = notificationTopic;
	}
}
