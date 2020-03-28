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

import studentportal.datamodel.Course;
import studentportal.datamodel.Professor;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.CourseService;

@Path("/course")
public class CourseResource {
	CourseService courseservice = new CourseService();

	// POST
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course c) {
		return courseservice.addCourse(c);
	}

	// Get all the courses
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourse() throws RecordNotFoundException {
		List<Course> list = courseservice.getAllCourses();
		if (list.size() == 0) {
			throw new RecordNotFoundException("Course not found");
		}
		return list;
	}

	// get Course by id
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) throws RecordNotFoundException {
		Course course = courseservice.getCourse(courseId);
		if (course == null) {
			throw new RecordNotFoundException("Course not found");
		}
		return course;
	}

	@GET
	@Path("/department/{department}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseByDepartment(@PathParam("department") String department)
			throws RecordNotFoundException {
		System.out.println("In get by department: Looking for: " + department);
		if (courseservice.getCourseByDepartment(department) == null) {
			throw new RecordNotFoundException("No courses found in the department");
		}
		return courseservice.getCourseByDepartment(department);

	}

	// get Course by professor
	@GET
	@Path("/byprofessorid/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course getCoursesByProfessor(@PathParam("professorId") String professorId) throws RecordNotFoundException {
		Course course = courseservice.getCourseByProfessor(professorId);
		if (course == null) {
			throw new RecordNotFoundException("Course not found");
		}
		return course;
	}

	// update
	@PUT
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("courseId") String courseId, Course c) throws RecordNotFoundException {
		Course course = courseservice.updateCourseInformation(courseId, c);
		if (course == null) {
			throw new RecordNotFoundException("Course to be updated not found");
		}
		return course;
	}

	// Delete
	@DELETE
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("courseId") String courseId) throws RecordNotFoundException {
		Course course = courseservice.deleteCourse(courseId);
		if (course == null) {
			throw new RecordNotFoundException("Course to be deleted not found");
		}
		return course;
	}
}
