package studentportal.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Program {
	private String programName;
	private String programId;
	private List<String> coursesOfProgram;

	public Program() {
	}

	public Program(String programId, String programName) {
		this.programId = programId;
		this.programName = programName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public List<String> getCoursesOfProgram() {
		return coursesOfProgram;
	}

	public void setCoursesOfProgram(List<String> coursesOfProgram) {
		this.coursesOfProgram = coursesOfProgram;
	}

}
