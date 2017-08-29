package edu.unifi.tap.exambooking.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.repository.ExamRepository;
import edu.unifi.tap.exambooking.repository.StudentRepository;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	final static Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentRepository  studentRepository;


	@Override
	public Student findStudentByIdNumberAndExam(String idNumber, Long examId) {
		LOGGER.debug("findStudentByIdNumberAndExam called");
		return studentRepository.findStudentByIdNumberAndExam(idNumber,examId);
	}


	@Override
	public Student registerStudent(Student student,Exam exam) { 
		 student.setExam(exam);
		 return studentRepository.save(student);
	}



}
