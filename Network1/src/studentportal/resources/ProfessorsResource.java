package studentportal.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import studentportal.datamodel.Professor;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.ProfessorsService;

// .. /webapi/myresource
@Path("/professor")
public class ProfessorsResource {

	ProfessorsService profService;

	public ProfessorsResource() {
		profService = new ProfessorsService();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProfessor(Professor prof) {
		System.out.println("Posted object " + prof.toString());
		profService.addProfessor(prof);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessors() throws RecordNotFoundException {
		System.out.println("In get Professors");
		if (profService.getAllProfessors().size() == 0) {
			throw new RecordNotFoundException("Professor not found");
		}
		return profService.getAllProfessors();
	}

	// ... webapi/professor/1
	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String profId) throws RecordNotFoundException {
		System.out.println("Professor Resource: Looking for: " + profId);
		if (profService.getProfessor(profId) == null) {
			throw new RecordNotFoundException("Professor not found");
		}
		return profService.getProfessor(profId);
	}

	@GET
	@Path("/department/{department}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDepartment(@PathParam("department") String department)
			throws RecordNotFoundException {
		System.out.println("In get by department: Looking for: " + department);
		if (profService.getProfessorsByDepartment(department) == null) {
			throw new RecordNotFoundException("No professor found in the department");
		}
		return profService.getProfessorsByDepartment(department);

	}

	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") String profId, Professor prof)
			throws RecordNotFoundException {
		Professor professor = profService.updateProfessorInformation(profId, prof);
		if (professor == null) {
			throw new RecordNotFoundException("Professor to be updated not found");
		}
		return professor;
	}

	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") String profId) throws Exception {
		Professor prof = profService.deleteProfessor(profId);

		if (prof == null) {
			throw new RecordNotFoundException("Prefessor to be deleted not found");
		}
		
		return prof;
	}

}
