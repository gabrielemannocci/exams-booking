package edu.unifi.tap.exambooking.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private StudentRepository studentRepository;
	
	private Student expectedStudent;
	private Exam expectedExam;
	
	@Before
	public void setUp(){
		expectedStudent = new Student();
		expectedStudent.setStudentId(null);
		expectedStudent.setFirstName("firstName");
		expectedStudent.setFirstName("lastName");
		expectedStudent.setIdNumber("1000000");
		expectedStudent.setEmail("firstName.lastName@email.it");
		
		expectedExam = new Exam();
		expectedExam.setExamId(null);
		expectedExam.setExamCode("CSZ");
		expectedExam.setExamName("Codici e sicurezza");
		expectedExam.setExamDate(new Date());
		expectedExam.setExamClassRoom("Aual 101");
	}
	
	@Test
	public void testFindStudentByIdNumberAndExam(){
		entityManager.persist(expectedExam);
		
		Student actualStudent = new Student(null,"firstName","lastName","firstName.lastName@email.it","1000000");
		actualStudent.setExam(expectedExam);
		
		entityManager.persist(actualStudent);
	    entityManager.flush();
	 
	    Student found = studentRepository.findStudentByIdNumberAndExam(actualStudent.getIdNumber(), expectedExam.getExamId());
	 
	    assertThat(found.getFirstName()).isEqualTo(actualStudent.getFirstName());
	    assertThat(found.getLastName()).isEqualTo(actualStudent.getLastName());
	}
}

