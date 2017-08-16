package edu.unifi.tap.exambooking.services;


import java.util.List;

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
	private StudentRepository studentRepository;
	
	@Autowired
	private ExamRepository examRepository;
	
	@Override
	public String registerStudent(Student student, Exam exam) {
		 Student studTemp = studentRepository.findStudentByIdNumber(student.getIdNumber());
		 Exam examTemp = examRepository.findOne(exam.getExamId());
		 List<Exam> exams = studTemp.getRegisteredExams();
		 if(!(exams.contains(examTemp)))
				 exams.add(examTemp);
		 studTemp.setRegisteredExams(exams);
		 studentRepository.save(studTemp);
		return "OK";
	}

	@Override
	public Student findStudentByIdNumber(String idNumber) {
		return studentRepository.findStudentByIdNumber(idNumber);
	}

	@Override
	public Boolean checkForStudentRegistration(Long studentId, Long examId) {
		
		return null;
	}



}
