package studentportal.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TeachingAssistant {
	private String studentID;
	private String taId;
	private String courseId;

	public TeachingAssistant() {

	}

	public TeachingAssistant(String studentID, String taId, String courseId) {
		this.studentID = studentID;
		this.courseId = courseId;
		this.taId = taId;
	}

	public String getTaId() {
		return taId;
	}

	public void setTaId(String taId) {
		this.taId = taId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
}
