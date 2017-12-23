package edu.unifi.tap.exambooking.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.unifi.tap.exambooking.model.Exam;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class ExamRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private ExamRepository examRepository;
	
	private Exam expected;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Before
	public void setUp() throws ParseException {

		
	            
		expected = new Exam();
		expected.setExamId(0L);
		expected.setExamCode("CSZ");
		expected.setExamName("Codici e sicurezza");
		expected.setExamDate(formatter.parse("2017-07-09 09:00:00"));
		expected.setExamClassRoom("Aual 101");

	}
	
	@Test
	public void whenFindById_thenReturnExam() throws ParseException {
		Exam actualExam = new Exam(null,"CSZ","Codici e sicurezza",formatter.parse("2017-07-09 09:00:00"),null);

		entityManager.persist(actualExam);
	    entityManager.flush();
	 
	    Exam found = examRepository.findOne(actualExam.getExamId());
	 
	    assertThat(found.getExamCode()).isEqualTo(actualExam.getExamCode());

	}
	
	@Test
	public void findAllExams() throws ParseException{
		
		Exam actualExam1 = new Exam(null,"CSZ","Codici e sicurezza",formatter.parse("2017-07-09 09:00:00"),null);
		Exam actualExam2 = new Exam(null,"DWH","Datawarehousing",formatter.parse("2017-07-10 09:00:00"),null);
		Exam actualExam3 = new Exam(null,"TAP","TAP",formatter.parse("2017-07-11 09:00:00"),null);
		 
	    entityManager.persist(actualExam1);
	    entityManager.persist(actualExam2);
	    entityManager.persist(actualExam3);
	    entityManager.flush();
	    
		List<Exam> results = examRepository.findAll();
 		assertThat(results).contains(actualExam1, actualExam2, actualExam3);
		
	}
	
}
