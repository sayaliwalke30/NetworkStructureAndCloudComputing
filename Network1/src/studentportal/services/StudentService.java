package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentportal.InMemoryDatabase;
import studentportal.datamodel.Student;

public class StudentService {
	static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();
	// Add Student information
		public Student addStudent(Student student) {
			Long key = Long.parseLong(student.getStudentID());
			student_Map.put(key, student);
			return student;
		}

	// get the list of all students
	public List<Student> getAllStudents() {
		ArrayList<Student> list = new ArrayList<>();
		for (Student stu : student_Map.values()) {
			list.add(stu);
		}
		return list;
	}

	// Get students in a Program
	public List<Student> getStudentsByProgram(String ProgramId) {
		// Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student student : student_Map.values()) {
			if (student.getprogramID().equals(ProgramId)) {
				list.add(student);
			}
		}
		return list;
	}
	
	// Getting one student
	public Student getStudent(String studentID) {

		// Simple HashKey Load
		Long key = Long.parseLong(studentID);
		Student student = student_Map.get(key);
		System.out.println("Student Item retrieved:");

		return student;
	}

	// Updating Student Info
	public Student updateStudentInformation(String studentID, Student student) {
		Long key = Long.parseLong(studentID);
		Student oldStudentObject = student_Map.get(key);
		if (oldStudentObject != null) {
		oldStudentObject = student;
		student_Map.put(key, oldStudentObject);
		}
		return oldStudentObject;
	}

	// Deleting a student
	public Student deleteStudent(String studentID) {
		Long key = Long.parseLong(studentID);
		return student_Map.remove(key);
	}


}
