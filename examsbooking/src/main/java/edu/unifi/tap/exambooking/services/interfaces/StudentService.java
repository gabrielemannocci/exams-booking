package edu.unifi.tap.exambooking.services.interfaces;

import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;

public interface StudentService {

	public String registerStudent(Student student, Exam exam) throws InvalidStudentException;
	public Boolean checkForStudentRegistration(Long studentId, Long examId);
	public Student findStudentByIdNumber(String idNumber);
}
