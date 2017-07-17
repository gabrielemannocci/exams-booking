package edu.unifi.tap.exambooking.services.interfaces;

import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.exception.StudentAlreadyRegisteredForExamException;
import edu.unifi.tap.exambooking.model.Student;

public interface StudentService {

	public Student findByIdNumber(String idNumber, Long long1) throws StudentAlreadyRegisteredForExamException;
	public void saveUser(Student student) throws InvalidStudentException;
	
}
