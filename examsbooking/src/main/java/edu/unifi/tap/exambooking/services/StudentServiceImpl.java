package edu.unifi.tap.exambooking.services;

import org.springframework.stereotype.Service;

import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.exception.StudentAlreadyRegisteredForExamException;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Override
	public Student findByIdNumber(String idNumber, Long long1) throws StudentAlreadyRegisteredForExamException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(Student student) throws InvalidStudentException {
		// TODO Auto-generated method stub
		
	}



}
