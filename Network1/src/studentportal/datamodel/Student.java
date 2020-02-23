package studentportal.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private String firstName;
	private String lastName;
	private String studentID;
	private String programID;
	private List<String> coursesEnrolled;

	public Student() {

	}

	public Student(String firstName, String lastName, String studentID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	@Override
	public String toString() {
		return "StudentId=" + getStudentID() + ", firstName=" + getFirstName() + ", Program = " + getprogramID();

	}

	public String getprogramID() {
		return programID;
	}

	public void setprogramID(String programID) {
		this.programID = programID;
	}

	public List<String> getCoursesEnrolled() {
		return coursesEnrolled;
	}

	public void setCoursesEnrolled(List<String> coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}
}
