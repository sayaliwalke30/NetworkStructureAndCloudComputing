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
import studentportal.datamodel.Course;
import studentportal.datamodel.Professor;

public class CourseService {
	// static HashMap<Long, Course> course_Map = InMemoryDatabase.getCourseDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper course_Map;

	BoardService boardService = new BoardService();

	public CourseService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		course_Map = new DynamoDBMapper(dynamoDb.getClient());
		System.out.println("DynamoDb client initialized");
	}

	public Course addCourse(Course course) {
		Long key = Long.parseLong(course.getCourseId());
		course_Map.save(course);
		return course;
	}

	// get the list of all courses
	public List<Course> getAllCourses() {
		return course_Map.scan(Course.class, new DynamoDBScanExpression());
	}
	// Getting one course

	public Course getCourse(String courseId) {

		List<Course> list = course_Map.scan(Course.class, new DynamoDBScanExpression());
		for (Course f : list) {
			if (f.getCourseId().equals(courseId)) {
				System.out.println("Course found");
				return f;
			}
		}
		return null;
	}

	// get courses by a department
	public List<Course> getCourseByDepartment(String department) {
		List<Course> list = course_Map.scan(Course.class, new DynamoDBScanExpression());
		List<Course> result = new ArrayList<>();
		for (Course f : list) {
			if (f.getDepartment().equals(department)) {
				System.out.println("Found professor");
				result.add(f);
			}
		}
		return result;
	}

	// get courses by a professorID
	public Course getCourseByProfessor(String professorID) {
		List<Course> list = course_Map.scan(Course.class, new DynamoDBScanExpression());
		for (Course f : list) {
			if (f.getProfessorId().equals(professorID)) {
				System.out.println("Found professor");
				return f;
			}
		}
		return null;
	}

	// Updating course Info
	public Course updateCourseInformation(String courseId, Course course) {
		Map<String, AttributeValue> map = new HashMap<>();
		map.put(":courseId", new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("courseId=:courseId")
				.withExpressionAttributeValues(map);
		List<Course> target = course_Map.scan(Course.class, scanExpression);
		if (target.size() != 0) {
			String Id = target.get(0).getCourseId();
			course.setCourseId(courseId);
			course_Map.save(course);
			return course_Map.load(Course.class, Id);
		}
		return null;
	}

	// deleting course Info
	public Course deleteCourse(String courseId) {
		Map<String, AttributeValue> map = new HashMap<>();
		map.put(":courseId", new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("courseId=:courseId")
				.withExpressionAttributeValues(map);
		List<Course> target = course_Map.scan(Course.class, scanExpression);
		if (target.size() != 0) {
			course_Map.delete(target.get(0));
			return target.get(0);
		}
		return null;

	}
}
