package studentportal.datamodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Lecture {
	private String courseID;
	private String associatedMaterial;
	private String notes;
	private String lectureID;
	
	public String getLectureID() {
		return lectureID;
	}
	public void setLectureID(String lectureID) {
		this.lectureID = lectureID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getAssociatedMaterial() {
		return associatedMaterial;
	}
	public void setAssociatedMaterial(String associatedMaterial) {
		this.associatedMaterial = associatedMaterial;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "LectureId=" + getLectureID() + ", CourseID=" + getCourseID() + ", "
				+ "Associated Material = " + getAssociatedMaterial()+ ", Notes=" + getNotes();
	}
}
