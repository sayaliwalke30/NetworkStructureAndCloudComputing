package studentportal.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName = "Program")
@XmlRootElement
public class Program {
	private String Id;
	private String programName;
	private String programId;
	private List<String> coursesOfProgram;
	public Program(){
		
	}
	public Program(String programId, String programName) {
		this.programId = programId;
		this.programName = programName;
	}

	@DynamoDBHashKey(attributeName="Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}

	@DynamoDBAttribute(attributeName="programName")
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	@DynamoDBIndexHashKey(attributeName="programId",globalSecondaryIndexName="programId-index")
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	@DynamoDBAttribute(attributeName="coursesOffered")
	public List<String> getCoursesOfProgram() {
		return coursesOfProgram;
	}

	public void setCoursesOfProgram(List<String> coursesOfProgram) {
		this.coursesOfProgram = coursesOfProgram;
	}
	@Override
	public String toString() {
		return "ProgramId=" + getProgramId() + ", programName=" + getProgramName() + ", Courses= " + getCoursesOfProgram();

	}

}
