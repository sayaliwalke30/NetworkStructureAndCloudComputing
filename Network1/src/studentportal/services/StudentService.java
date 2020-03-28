package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import studentportal.DynamoDbConnector;
import studentportal.InMemoryDatabase;
import studentportal.datamodel.Professor;
import studentportal.datamodel.Student;

public class StudentService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper student_Map;

	// static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();
	@SuppressWarnings("static-access")
	public StudentService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		student_Map = new DynamoDBMapper(dynamoDb.getClient());
		System.out.println("DynamoDb client initialized");
	}

	// Add Student information
	public void addStudent(Student student) {
		Long key = Long.parseLong(student.getStudentID());
		System.out.print("Key is " + key);
		student_Map.save(student);
	}

	// get the list of all students
	public List<Student> getAllStudents() {
		return student_Map.scan(Student.class, new DynamoDBScanExpression());
	}

	// Get students in a Program
	public Student getStudentsByProgram(String ProgramId) {
		// Getting the list
		List<Student> list = student_Map.scan(Student.class, new DynamoDBScanExpression());
		for (Student f : list) {
			if (f.getStudentID().equals(ProgramId)) {
				System.out.println("Found professor");
				return f;
			}
		}
		return null;
	}

	// Getting one student
	public Student getStudent(String studentID) {

		List<Student> list = student_Map.scan(Student.class, new DynamoDBScanExpression());
		for (Student f : list) {
			if (f.getStudentID().equals(studentID)) {
				System.out.println("Found professor");
				return f;
			}
		}
		return null;
	}

	// Updating Student Info
	public Student updateStudentInformation(String studentID, Student student) {
		Map<String, AttributeValue> map = new HashMap<>();
		System.out.println("In update student " + studentID);
		map.put(":studentID", new AttributeValue().withS(studentID));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("studentID=:studentID").withExpressionAttributeValues(map);
		List<Student> target = student_Map.scan(Student.class, scanExpression);
		if (target.size() != 0) {
			String Id = target.get(0).getId();
			System.out.println("The Id of given object is" + Id);
			student.setId(Id);
			student_Map.save(student);
			return student_Map.load(Student.class, Id);
		}
		return null;
	}

	// Deleting a student
	public Student deleteStudent(String studentID) {
		Map<String, AttributeValue> map = new HashMap<>();
		System.out.println("In update student " + studentID);
		map.put(":studentID", new AttributeValue().withS(studentID));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("studentID=:studentID").withExpressionAttributeValues(map);
		List<Student> target = student_Map.scan(Student.class, scanExpression);
		if (target.size() != 0) {
			student_Map.delete(target.get(0));
			return target.get(0);
		}
		return null;
	}

}
