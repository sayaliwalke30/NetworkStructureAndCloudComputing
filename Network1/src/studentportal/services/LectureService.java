package studentportal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentportal.InMemoryDatabase;
import studentportal.datamodel.Lecture;
import studentportal.datamodel.Student;

public class LectureService {
	static HashMap<Long, Lecture> lecture_Map = InMemoryDatabase.getLectureDB();

	// get the all the information about lectures
	public List<Lecture> getAllLectures() {
		ArrayList<Lecture> list = new ArrayList<>();
		for (Lecture lecture : lecture_Map.values()) {
			list.add(lecture);
		}
		return list;
	}

	// Add Lecture information
	public Lecture addLecture(Lecture lecture) {
		Long key = Long.parseLong(lecture.getLectureID());
		lecture_Map.put(key, lecture);
		return lecture;
	}

	// Get information about one lecture
	public Lecture getLecture(String lectureID) {

		// Simple HashKey Load
		Long key = Long.parseLong(lectureID);
		Lecture lecture = lecture_Map.get(key);
		System.out.println("Lecture Item retrieved:");

		return lecture;
	}

	// Get information about all the lectures associated with course
	public ArrayList<Lecture> getAllLecturesForCourse(String courseID) {

		ArrayList<Lecture> list = new ArrayList<>();
		for (Lecture lecture : lecture_Map.values()) {
			if (lecture.getCourseID().equals(courseID)) {
				list.add(lecture);
			}
		}
		return list;
	}

	// Updating Lecture Info
	public Lecture updateLectureInformation(String lectureID, Lecture lecture) {
		Long key = Long.parseLong(lectureID);
		Lecture oldLectureObject = lecture_Map.get(key);
		if (oldLectureObject != null) {
		oldLectureObject = lecture;
		lecture_Map.put(key, oldLectureObject);
		}
		return oldLectureObject;
	}

	// Deleting a lecture
	public Lecture deleteLecture(String lectureID) {
		Long key = Long.parseLong(lectureID);
		return lecture_Map.remove(key);
	}

}
