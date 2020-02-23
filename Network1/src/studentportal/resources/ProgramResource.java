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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import studentportal.datamodel.Program;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.ProgramService;

@Path("/program")
public class ProgramResource {
	ProgramService programService = new ProgramService();

	// get all programs
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms() throws RecordNotFoundException {
		if (programService.getAllPrograms().size() == 0) {
			throw new RecordNotFoundException("Programs not found");
		}
		return programService.getAllPrograms();
	}

	// get program by id
	@GET
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") String programId) throws RecordNotFoundException {
		if (programService.getProgram(programId) == null ) {
			throw new RecordNotFoundException("Program not found");
		}
		return programService.getProgram(programId);
	}

	// Delete
	@DELETE
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId") String programId) throws RecordNotFoundException {
		Program program = programService.deleteProgram(programId);
		if (program == null) {
			throw new RecordNotFoundException("Program to be deleted not found");
		}
		return program;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program p) {
		return programService.addProgram(p);
	}

	// update
	@PUT
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") String programId, Program p) throws RecordNotFoundException {
		Program program = programService.updateProgramInformation(programId, p);
		if (program == null) {
			throw new RecordNotFoundException("Program to be updated not found");
		}
		return program;
	}

}
