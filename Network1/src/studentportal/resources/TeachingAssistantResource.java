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

import studentportal.datamodel.TeachingAssistant;
import studentportal.exception.RecordNotFoundException;
import studentportal.datamodel.TeachingAssistant;
import studentportal.services.TeachingAssistantService;
import studentportal.services.TeachingAssistantService;

@Path("/TA")
public class TeachingAssistantResource {

	TeachingAssistantService teachingAssistantService = new TeachingAssistantService();

	// Add student into database
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TeachingAssistant addTeachingAssistant(TeachingAssistant teachingAssistant) {
		return teachingAssistantService.addTeachingAssistant(teachingAssistant);
	}

	// Get List of all students
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<TeachingAssistant> getTeachingAssistant() throws RecordNotFoundException {
		if (teachingAssistantService.getAllTAs().size() == 0) {
			throw new RecordNotFoundException("TA not found");
		}
		return teachingAssistantService.getAllTAs();
	}
	// Get list of TA for a course

	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TeachingAssistant getTeachingAssistantByCourse(@PathParam("courseId") String courseId)
			throws RecordNotFoundException {
		if (teachingAssistantService.getTeachingAssistantsByCourse(courseId) == null) {
			throw new RecordNotFoundException("TA nof found");
		}
		return teachingAssistantService.getTeachingAssistantsByCourse(courseId);
	}

	// update TA in database
	@PUT
	@Path("/{TAID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TeachingAssistant updateTeachingAssistant(@PathParam("TAID") String TAID,
			TeachingAssistant teachingAssistant) throws RecordNotFoundException {
		TeachingAssistant teachingAssistant2 = teachingAssistantService.updateTeachingAssistantInformation(TAID,
				teachingAssistant);
		if (teachingAssistant2 == null) {
			throw new RecordNotFoundException("TA to be updated not found");
		}
		return teachingAssistant2;
	}

	// Delete TA record by id
	@DELETE
	@Path("/{TAID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TeachingAssistant deleteTeachingAssistant(@PathParam("TAID") String TAId) throws RecordNotFoundException {
		TeachingAssistant teachingAssistant = teachingAssistantService.deleteTeachingAssistant(TAId);
		if (teachingAssistant == null) {
			throw new RecordNotFoundException("TA to be deleted not found");
		}
		return teachingAssistant;
	}
}
