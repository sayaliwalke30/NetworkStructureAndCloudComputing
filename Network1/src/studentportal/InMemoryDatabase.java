package studentportal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import studentportal.datamodel.Course;
import studentportal.datamodel.Lecture;
import studentportal.datamodel.Professor;
import studentportal.datamodel.Program;
import studentportal.datamodel.Student;
import studentportal.datamodel.TeachingAssistant;

public class InMemoryDatabase {

	private static HashMap<Long, Professor> professorDB = new HashMap<>();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}

	private static HashMap<Long, Student> studentDB = new HashMap<>();

	public static HashMap<Long, Student> getStudentDB() {
		return studentDB;
	}

	private static HashMap<Long, Program> programDB = new HashMap<>();

	public static HashMap<Long, Program> getProgramDB() {
		return programDB;
	}

	private static HashMap<Long, Course> courseDB = new HashMap<>();

	public static HashMap<Long, Course> getCourseDB() {
		return courseDB;
	}
	
	private static HashMap<Long, TeachingAssistant> TADB = new HashMap<>();

	public static HashMap<Long, TeachingAssistant> getTADB() {
		return TADB;
	}
	private static HashMap<Long, Lecture> lectureDB = new HashMap<>();

	public static HashMap<Long, Lecture> getLectureDB() {
		return lectureDB;
	}
}
