package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import studentportal.DynamoDbConnector;
import studentportal.datamodel.Professor;

public class ProfessorsService {

	// static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper prof_Map;

	@SuppressWarnings("static-access")
	public ProfessorsService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		prof_Map = new DynamoDBMapper(dynamoDb.getClient());
		System.out.println("DynamoDb client initialized");
	}

	// Adding a professor
	public void addProfessor(Professor professor) {
		Long key = Long.parseLong(professor.getProfessorId());
		System.out.print("Key is " + key);
		prof_Map.save(professor);
	}

	// Getting a list of all professor
	public List<Professor> getAllProfessors() {
		return prof_Map.scan(Professor.class, new DynamoDBScanExpression());
	}

	// Getting One Professor
	public Professor getProfessor(String profId) {
		List<Professor> list = prof_Map.scan(Professor.class, new DynamoDBScanExpression());
		for (Professor f : list) {
			if (f.getProfessorId().equals(profId)) {
				System.out.println("Found professor");
				return f;
			}
		}
		return null;
	}

	// Get professors in a department
	public List<Professor> getProfessorsByDepartment(String department) {
		List<Professor> list = prof_Map.scan(Professor.class, new DynamoDBScanExpression());
		List<Professor> result = new ArrayList<>();
		for (Professor f : list) {
			if (f.getDepartment().equals(department)) {
				System.out.println("Found professor");
				result.add(f);
			}
		}
		return result;
	}
	
	  // Updating Professor Info 
	  public Professor updateProfessorInformation(String professorId, Professor prof) 
	  { 
		Map<String, AttributeValue> map = new HashMap<>();
		map.put(":professorId", new AttributeValue().withS(professorId));
		DynamoDBScanExpression scanExpression=
		new DynamoDBScanExpression().withFilterExpression("professorId=:professorId").withExpressionAttributeValues(map);
		List<Professor> target = prof_Map.scan(Professor.class, scanExpression);
		if(target.size()!=0) {
			String Id = target.get(0).getProfessorId();
			prof.setProfessorId(professorId);
			prof_Map.save(prof);
			return prof_Map.load(Professor.class,Id);
		}
		return null; }
	  
	  // Deleting a professor 
	  public Professor deleteProfessor(String professorId) {
		    Map<String, AttributeValue> map=new HashMap<>();
			map.put(":professorId", new AttributeValue().withS(professorId));
			DynamoDBScanExpression scanExpression=new DynamoDBScanExpression()
					.withFilterExpression("professorId=:professorId").withExpressionAttributeValues(map);
			List<Professor> target=prof_Map.scan(Professor.class, scanExpression);
			if(target.size()!=0) {
				prof_Map.delete(target.get(0));
				return target.get(0);
			}
			return null;
	  
	  }
	  
	 

}
