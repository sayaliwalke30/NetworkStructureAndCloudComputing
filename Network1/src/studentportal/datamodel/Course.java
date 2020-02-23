package studentportal.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Course {
	private String courseId;
	private String courseName;
	private String professorId;
	private String department;
	private String boardId;
	private List<String> roster = new ArrayList<String>();// has registered student Id list
	private List<String> announcements; // Field for storing announcements
	private String notificationTopic; // For storing notifications

	public Course() {
	}

	public Course(String courseId, String courseName, String professorId, String department) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.professorId = professorId;
		this.department = department;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public List<String> getRoster() {
		return roster;
	}

	public void setRoster(List<String> roster) {
		this.roster = roster;
	}

	public List<String> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<String> announcements) {
		this.announcements = announcements;
	}

	public String getNotificationTopic() {
		return notificationTopic;
	}

	public void setNotificationTopic(String notificationTopic) {
		this.notificationTopic = notificationTopic;
	}

}
