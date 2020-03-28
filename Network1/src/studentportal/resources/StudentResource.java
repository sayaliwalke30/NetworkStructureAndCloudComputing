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

import studentportal.datamodel.Student;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.StudentService;

@Path("/student")
public class StudentResource {

	StudentService studentService = new StudentService();

	// Add student into database
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStudent(Student student) {
		 studentService.addStudent(student);
	}

	// Get List of all students
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Student> getStudent() throws RecordNotFoundException {
		if (studentService.getAllStudents().size() == 0) {
			throw new RecordNotFoundException("Student not found");
		}
		return studentService.getAllStudents();
	}

	// Get list of all students by program
	@GET
	@Path("/studentbyprogram/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student getStudentsByProgram(@PathParam("programId") String programId) throws RecordNotFoundException {
		if (studentService.getStudentsByProgram(programId) == null) {
			throw new RecordNotFoundException("Student not found");
		}
		return studentService.getStudentsByProgram(programId);
	}

	// Get student info using id
	@GET
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentID") String studentID) throws RecordNotFoundException {
		System.out.println("Student you are looking for : " + studentID);

		Student student = studentService.getStudent(studentID);
		if (student == null) {
			throw new RecordNotFoundException("Student not found");
		}
		return student;
	}

	// update student in database
	@PUT
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentID") String studentID, Student student)
			throws RecordNotFoundException {
		Student student2 = studentService.updateStudentInformation(studentID, student);
		if (student2 == null) {
			throw new RecordNotFoundException("Student to be updated not found");
		}
		return student2;
	}

	// Delete student record by id
	@DELETE
	@Path("/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentID") String studentID) throws RecordNotFoundException {
		Student student = studentService.deleteStudent(studentID);
		if (student == null) {
			throw new RecordNotFoundException("Student to be deleted not found");
		}
		return student;
	}
}
