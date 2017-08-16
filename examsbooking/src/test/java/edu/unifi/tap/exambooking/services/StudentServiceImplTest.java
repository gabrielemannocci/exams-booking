package edu.unifi.tap.exambooking.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.repository.StudentRepository;



@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

	@MockBean
	private StudentRepository studentRepository;
	
	private Student expected;
	
    @Before
    public void setup() {
    	 expected =  new Student(1L,"firstName","lastName","aValidEmailTest@email.com","0000000");
    }
    
    @Test
	public void testRegisterUser() throws Exception{
    }
    
    @Test
	public void testFindStudentByIdNumber() throws Exception{
    	when(studentRepository.findStudentByIdNumber(expected.getIdNumber())).thenReturn(expected);
    	assertNotNull(expected);
    	
    }
    
    @Test
	public void testCheckForStudentRegistration() throws Exception{
    	
    }
    
//	@Override
//	public String registerStudent(Student student, Exam exam) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Student findStudentByIdNumber(String idNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Boolean checkForStudentRegistration(Long studentId, Long examId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
