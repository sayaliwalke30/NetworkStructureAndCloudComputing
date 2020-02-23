package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentportal.InMemoryDatabase;
import studentportal.datamodel.TeachingAssistant;

public class TeachingAssistantService {
	
	static HashMap<Long, TeachingAssistant> TA_Map = InMemoryDatabase.getTADB();
	
	// Assign a teaching assistant for a course
	public TeachingAssistant addTeachingAssistant(TeachingAssistant TeachingAssistant) {
		Long key = Long.parseLong(TeachingAssistant.getTaId());
		TA_Map.put(key, TeachingAssistant);
		return TeachingAssistant;
	}

	// get the list of all TeachingAssistants
	public List<TeachingAssistant> getAllTAs() {
		ArrayList<TeachingAssistant> list = new ArrayList<>();
		for (TeachingAssistant stu : TA_Map.values()) {
			list.add(stu);
		}
		return list;
	}
	// Get TeachingAssistants for a course
		public TeachingAssistant getTeachingAssistantsByCourse(String courseId) {
			// Getting the list
			System.out.println("In getTeachingAssistantsByCourse");
			for (TeachingAssistant TeachingAssistant : TA_Map.values()) {
				if (TeachingAssistant.getCourseId().equals(courseId)) {				
					return TeachingAssistant;
				}
			}
			return null;
		}




	// Updating TeachingAssistant Info
	public TeachingAssistant updateTeachingAssistantInformation(String TeachingAssistantID, TeachingAssistant TeachingAssistant) {
		Long key = Long.parseLong(TeachingAssistantID);
		TeachingAssistant oldTeachingAssistantObject = TA_Map.get(key);
		if (oldTeachingAssistantObject != null) {
		oldTeachingAssistantObject = TeachingAssistant;
		TA_Map.put(key, oldTeachingAssistantObject);
		}
		return TeachingAssistant;
	}

	// Deleting a TeachingAssistant
	public TeachingAssistant deleteTeachingAssistant(String TeachingAssistantID) {
		Long key = Long.parseLong(TeachingAssistantID);
		return TA_Map.remove(key);
	}

	


}
