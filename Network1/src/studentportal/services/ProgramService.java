package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentportal.InMemoryDatabase;
import studentportal.datamodel.Program;

public class ProgramService {
	static HashMap<Long, Program> program_map = InMemoryDatabase.getProgramDB();

	// get the list of all programs
	public List<Program> getAllPrograms() {
		ArrayList<Program> list = new ArrayList<>();
		for (Program pro : program_map.values()) {
			list.add(pro);
		}
		return list;
	}

	public Program addProgram(Program program) {
        Long key = Long.parseLong(program.getProgramId());
		program_map.put(key, program);
		return program;
	}
	// Getting one program

	public Program getProgram(String programID) {

		// Simple HashKey Load
		Long key = Long.parseLong(programID);
		Program program = program_map.get(key);
		System.out.println("Item retrieved Programs:");

		return program;
	}

	// Updating Program Info
	public Program updateProgramInformation(String programId, Program program) {
		Long key = Long.parseLong(programId);
		Program oldProgram = program_map.get(key);
		if (oldProgram != null) {
		oldProgram = program;
		program_map.put(key, oldProgram);
		}
		return oldProgram;
	}

	// Deleting a Program
	public Program deleteProgram(String programId) {
		Long key = Long.parseLong(programId);
		Program deletedProgramDetails = program_map.remove(key);
		return deletedProgramDetails;
	}

	// Get courses in a Program
	public List<String> getCoursesByProgram(String progrmId) {
		// Getting the list
		ArrayList<String> list = new ArrayList<>();
		for (Program program : program_map.values()) {
			if (program.getProgramId().equals(progrmId)) {
				list.addAll((program.getCoursesOfProgram()));
			}
		}
		return list;
	}
}
