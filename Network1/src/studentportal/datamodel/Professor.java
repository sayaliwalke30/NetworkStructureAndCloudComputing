package studentportal.datamodel;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Professor {
	private String firstName;
	private String lastName;
	private String department;
	private String professorId;
	private String joiningDate;

	public Professor() {

	}

	public Professor(String professorId, String firstName, String lastName, String department, String joiningDate) {
		this.professorId = professorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.joiningDate = joiningDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "ProfId=" + getProfessorId() + ", firstName=" + getFirstName() + ", department=" + getDepartment()
				+ ", joiningDate=" + getJoiningDate() + "last Name" + getLastName();
	}
}
