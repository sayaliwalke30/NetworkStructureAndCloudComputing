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
import studentportal.datamodel.Program;
import studentportal.datamodel.Student;

public class ProgramService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper program_map;
	//static HashMap<Long, Program> program_map = InMemoryDatabase.getProgramDB();
	public ProgramService() {
	dynamoDb = new DynamoDbConnector();
	dynamoDb.init();
	program_map = new DynamoDBMapper(dynamoDb.getClient());
	System.out.println("DynamoDb client initialized");
	}

	
    // Add programs to database
	public void addProgram(Program program) {
		Long key = Long.parseLong(program.getProgramId());
		System.out.print("Key is " + key);
		program_map.save(program);
	}
	// get the list of all programs
		public List<Program> getAllPrograms() {
			return program_map.scan(Program.class, new DynamoDBScanExpression());
		}
	// Getting one program

	public Program getProgram(String programID) {

		// Getting the list
		List<Program> list = program_map.scan(Program.class, new DynamoDBScanExpression());
		for (Program f : list) {
			if (f.getProgramId().equals(programID)) {
				System.out.println("Found programs");
				return f;
			}
		}
		return null;
	}
	// Get courses in a Program
	public List<String> getCoursesByProgram(String progrmId) {
		// Getting the list
		ArrayList<String> listOfCourses = new ArrayList<>();
		List<Program> list = program_map.scan(Program.class, new DynamoDBScanExpression());
		for (Program program : list) {
			if (program.getProgramId().equals(progrmId)) {
				listOfCourses.addAll((program.getCoursesOfProgram()));
			}
		}
		return listOfCourses;
	}

	// Updating Program Info
	public Program updateProgramInformation(String programId, Program program) {
		Map<String, AttributeValue> map = new HashMap<>();
		System.out.println("In update program " + programId);
		map.put(":pogramId", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId=:programId").withExpressionAttributeValues(map);
		List<Program> target = program_map.scan(Program.class, scanExpression);
		if (target.size() != 0) {
			String Id = target.get(0).getId();
			System.out.println("The Id of given object is" + Id);
			program.setId(Id);
			program_map.save(program);
			return program_map.load(Program.class, Id);
		}
		return null;
	}

	// Deleting a Program
	public Program deleteProgram(String programId) {
		Map<String, AttributeValue> map = new HashMap<>();
		System.out.println("In update program " + programId);
		map.put(":pogramId", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId=:programId").withExpressionAttributeValues(map);
		List<Program> target = program_map.scan(Program.class, scanExpression);
		if (target.size() != 0) {
			program_map.delete(target.get(0));
			return target.get(0);
		}
		return null;
		
	}


}
