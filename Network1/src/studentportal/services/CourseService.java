package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentportal.InMemoryDatabase;
import studentportal.datamodel.Course;

public class CourseService {
	static HashMap<Long, Course> course_Map = InMemoryDatabase.getCourseDB();

	// Adding a Course
	public void addCourse(String courseId, String courseName, String professorId,String department) {
		// Next Id
//		long nextAvailableId = course_Map.size() + 1;
		// Create a Course Object
		Course course = new Course(courseId, courseName, professorId, department);
		Long key = Long.parseLong(courseId);
		course_Map.put(key, course);
	}

	public Course addCourse(Course course) {
		// Next Id
//		long nextAvailableId = course_Map.size() + 1;
		Long key = Long.parseLong(course.getCourseId());
		course_Map.put(key, course);
		return course;
	}

	// get the list of all courses
	public List<Course> getAllCourses() {
		ArrayList<Course> list = new ArrayList<>();
		for (Course course : course_Map.values()) {
			list.add(course);
		}
		return list;
	}
	// Getting one course

	public Course getCourse(String courseId) {

		// Simple HashKey Load
		Long key = Long.parseLong(courseId);
		Course course = course_Map.get(key);
		System.out.println("Course Item retrieved:");
		return course;
	}

	// get courses by a department
	public Course getCourseByDepartment(String department) {
		// Simple HashKey Load

		for (Course course : course_Map.values()) {
			if (course.getDepartment().equals(department)) {
				return course;
			}
		}
		return null;
	}

	// get courses by a professorID
	public Course getCourseByProfessor(String professorID) {
		// Simple HashKey Load
		for (Course course : course_Map.values()) {
			if (course.getProfessorId().equals(professorID)) {
				return course;
			}
		}
		return null;
	}

	// Updating course Info
	public Course updateCourseInformation(String courseID, Course course) {
		Long key = Long.parseLong(courseID);
		Course oldCourseObject = course_Map.get(key);
		if (oldCourseObject != null) {
		oldCourseObject = course;
		course_Map.put(key, oldCourseObject);
		}
		return oldCourseObject;
	}

	// deleting course Info
	public Course deleteCourse(String courseID) {
		Long key = Long.parseLong(courseID);
		return course_Map.remove(key);
	}
}
