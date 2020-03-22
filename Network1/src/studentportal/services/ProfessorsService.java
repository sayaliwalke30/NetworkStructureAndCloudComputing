package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import studentportal.DynamoDbConnector;
import studentportal.InMemoryDatabase;
import studentportal.datamodel.Professor;

public class ProfessorsService {

	//static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
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
	/*public List<Professor> getAllProfessors() {
		// Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list;
	}*/

	

	// Getting One Professor
	/*public Professor getProfessor(String profId) {
		Long key = Long.parseLong(profId);
		// Simple HashKey Load
		Professor prof2 = prof_Map.get(key);
		System.out.println("Item retrieved:");
		return prof2;
	}
	// Updating Professor Info
		public Professor updateProfessorInformation(String profId, Professor prof) {
			Long key = Long.parseLong(profId);
			Professor oldProfObject = prof_Map.get(key);
			if (oldProfObject != null) {
				oldProfObject = prof;
				prof_Map.put(key, oldProfObject);
			}
			return oldProfObject;
		}

	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		Long key = Long.parseLong(profId);
		return prof_Map.remove(key);

	}

	

	// Get professors in a department
	public List<Professor> getProfessorsByDepartment(String department) {
		// Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list;
	}*/

}
