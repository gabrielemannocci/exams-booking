package edu.unifi.tap.exambooking.services;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.repository.ExamRepository;
import edu.unifi.tap.exambooking.repository.StudentRepository;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;


@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

	@TestConfiguration
    static class EStudentServiceImplTestContextConfiguration {
  
        @Bean
        public StudentService studentService() {
            return new StudentServiceImpl();
        }
    }
	
	@Autowired
	private TestEntityManager entityManager;
	
	@MockBean
	private StudentRepository studentRepository;
	
	@MockBean
	private ExamRepository examRepository;
	
	@Autowired
	private StudentService studentService;
	
	private Student actualStudent;
	private Exam actualExam;
	
    @Before
    public void setup() {
    	 actualStudent =  new Student(1L,"firstName","lastName","aValidEmailTest@email.com","0000000");
    	 actualExam = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
    }
    
    @Test
	public void testRegisterUser() throws Exception{
    	when(examRepository.findOne(actualExam.getExamId())).thenReturn(actualExam);
    	
	    entityManager.persist(actualStudent);
	    entityManager.flush();

    }
    
    @Test
	public void testFindStudentByIdNumber() throws Exception{
    	
    }
    
    @Test
	public void testCheckForStudentRegistration() throws Exception{
    	
    }
    
}
