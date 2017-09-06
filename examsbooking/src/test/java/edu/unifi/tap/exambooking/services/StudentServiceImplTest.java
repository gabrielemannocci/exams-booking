package edu.unifi.tap.exambooking.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.repository.StudentRepository;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;


@RunWith(SpringRunner.class)
public class StudentServiceImplTest {

	@TestConfiguration
    static class StudentServiceImplTestContextConfiguration {
  
        @Bean
        public StudentService studentService() {
            return new StudentServiceImpl();
        }
    }

	
	@MockBean
	private StudentRepository studentRepository;

	
	@Autowired
	private StudentService studentService;
	
	private Student actualStudent;
	private Student expectedStudent;
	private Exam actualExam;
	
    @Before
    public void setup() {
    	 expectedStudent =  new Student(1L,"firstName","lastName","aValidEmailTest@email.com","6245113");
    	 actualStudent =  new Student(null,"firstName","lastName","aValidEmailTest@email.com","0000000");
    	 actualExam = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
    }
    
    @Test
	public void testRegisterStudent() throws Exception{
    	assertThat(actualStudent).isNotNull();
    	assertThat(actualExam).isNotNull();

    	actualStudent.setExam(actualExam);
    	
    	when(studentRepository.save(actualStudent)).thenReturn(expectedStudent);
    	
    	Student saved = studentService.registerStudent(actualStudent, actualExam);
    	assertThat(saved.getStudentId()).isEqualTo(expectedStudent.getStudentId());
    }
    
    
    @Test(expected = InvalidStudentException.class)
	public void testRegisterInvalidStudentWithNoFirstNameThenThrowException() throws Exception{
    	assertThat(actualStudent).isNotNull(); 
    	assertThat(actualExam).isNotNull();

    	actualStudent.setFirstName("");
    	actualStudent.setExam(actualExam);
    	
    	when(studentRepository.save(actualStudent)).thenReturn(expectedStudent);
    	
    	studentService.registerStudent(actualStudent, actualExam);
    }
    
    @Test(expected = InvalidStudentException.class)
	public void testRegisterInvalidStudentWithNoLastNameThenThrowException() throws Exception{
    	assertThat(actualStudent).isNotNull(); 
    	assertThat(actualExam).isNotNull();

    	actualStudent.setLastName("");
    	actualStudent.setExam(actualExam);
    	
    	when(studentRepository.save(actualStudent)).thenReturn(expectedStudent);
    	
    	studentService.registerStudent(actualStudent, actualExam);
    }
    
    @Test(expected = InvalidStudentException.class)
	public void testRegisterInvalidStudentWithNoIdNumberThenThrowException() throws Exception{
    	assertThat(actualStudent).isNotNull(); 
    	assertThat(actualExam).isNotNull();

    	actualStudent.setIdNumber("");
    	actualStudent.setExam(actualExam);
    	
    	when(studentRepository.save(actualStudent)).thenReturn(expectedStudent);
    	
    	studentService.registerStudent(actualStudent, actualExam);
    }
    
    
    @Test
	public void testFindStudentByIdNumberAndExam() throws Exception{
    	assertThat(actualStudent.getIdNumber()).isNotNull();
    	assertThat(actualExam.getExamId()).isNotNull();
    	
    	when(studentRepository.findStudentByIdNumberAndExam(actualStudent.getIdNumber(), actualExam.getExamId())).
    	thenReturn(expectedStudent);
    	
    	Student found = studentService.findStudentByIdNumberAndExam(actualStudent.getIdNumber(), actualExam.getExamId());
    	System.out.println("FOUND "+found.getStudentId());
    	assertThat(found.getStudentId()).isEqualTo(expectedStudent.getStudentId());
    }
    
}
