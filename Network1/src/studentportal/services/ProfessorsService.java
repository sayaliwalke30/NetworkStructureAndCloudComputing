package studentportal.services;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import studentportal.InMemoryDatabase;
import studentportal.datamodel.Professor;

public class ProfessorsService {

	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();

	public ProfessorsService() {
	}

	// Getting a list of all professor
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {
		// Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		return list;
	}

	// Adding a professor
	public Professor addProfessor(Professor professor) {
		Long key = Long.parseLong(professor.getProfessorId());
		System.out.print("Key is " + key);
		return prof_Map.put(key, professor);
	}

	// Getting One Professor
	public Professor getProfessor(String profId) {
		Long key = Long.parseLong(profId);
		// Simple HashKey Load
		Professor prof2 = prof_Map.get(key);
		System.out.println("Item retrieved:");
		return prof2;
	}

	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		Long key = Long.parseLong(profId);
		return prof_Map.remove(key);

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
	}

}
