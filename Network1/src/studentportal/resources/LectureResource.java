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

import studentportal.datamodel.Lecture;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.LectureService;

@Path("/lecture")
public class LectureResource {

	LectureService lectureService = new LectureService();
	// Add lecture into database
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture addLecture(Lecture lecture) {
		return lectureService.addLecture(lecture);
	}

	// Get List of all lectures
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Lecture> getLecture() throws RecordNotFoundException {
		if (lectureService.getAllLectures().size() == 0) {
			throw new RecordNotFoundException("Lecture not found");
		}
		return lectureService.getAllLectures();
	}

	// Get list of all lectures by courses
	@GET
	@Path("/bycourseid/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Lecture> getAllLecturesForCourse(@PathParam("courseId") String courseId) throws RecordNotFoundException {
	    if (lectureService.getAllLecturesForCourse(courseId) == null) {
	    	throw new RecordNotFoundException("Lecture not found");
	    }
		return lectureService.getAllLecturesForCourse(courseId);
	}

	// Get lecture info using id
	@GET
	@Path("/{lectureID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture getLecture(@PathParam("lectureID") String lectureID) throws RecordNotFoundException {
		System.out.println("Lecture you are looking for : " + lectureID);
	
		Lecture lecture = lectureService.getLecture(lectureID);
		if (lecture == null) {
			throw new RecordNotFoundException("Lecture not found");
		}
		return lecture;
	}

	// update lecture in database
	@PUT
	@Path("/{lectureID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture updateLecture(@PathParam("lectureID") String lectureID, Lecture lecture) throws RecordNotFoundException {
		Lecture lecture2 = lectureService.updateLectureInformation(lectureID, lecture);
		if (lecture2 == null) {
			throw new RecordNotFoundException("Lecture to be updated not found");
		}
		return lecture2;
	}


	// Delete lecture record by id
	@DELETE
	@Path("/{lectureID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture deleteLecture(@PathParam("lectureID") String lectureID) throws RecordNotFoundException {
		Lecture lecture = lectureService.deleteLecture(lectureID);
		if (lecture == null) {
			throw new RecordNotFoundException("Lecture to be deleted not found");
		}
		return lecture;
	}
	}



