package edu.unifi.tap.exambooking.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.model.Exam;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)//Se presente usa DB reale invece di H2 in memoria
public class ExamRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private ExamRepository examRepository;
	
	private Exam exam1;
	private Exam exam2;
	private Exam exam3;
	private List<Exam> exams;
	
	@Before
	public void setUp() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            
		exam1 = new Exam();
		exam1.setExamId(0L);
		exam1.setExamCode("CSZ");
		exam1.setExamName("Codici e sicurezza");
 		exam1.setExamDate(formatter.parse("2017-07-09 09:00:00"));
		
		exam2 = new Exam();
		exam2.setExamId(1L);
		exam2.setExamCode("DWH");
		exam2.setExamName("Datawarehousing");
 		exam2.setExamDate(formatter.parse("2017-07-10 09:00:00"));
		
		exam3 = new Exam();
		exam3.setExamId(2L);
		exam3.setExamCode("TAP");
		exam3.setExamName("Tecniche avanzate di programmazione");
 		exam3.setExamDate(formatter.parse("2017-07-11 09:00:00"));

 		exams = new ArrayList<Exam>();
 		exams.add(exam1);
 		exams.add(exam2);
 		exams.add(exam3);
 		
 		examRepository.deleteAll();
	}
	
	@Test
	public void whenFindById_thenReturnExam() {
//		Exam actualExam = new Exam(null,"ABC","Test Exam",new Date(),null);
//	    // given
//	    entityManager.persist(actualExam);
//	    entityManager.flush();
//	 
	    // when
	    Exam found = examRepository.findOne(exam1.getExamId());
	 
	    // then
	    assertThat(found.getExamCode())
	      .isEqualTo(exam1.getExamCode());
	}
	
	@Test
	public void findAllExams(){

//
//	    entityManager.persist(exam1);
//	    entityManager.persist(exam2);
//	    entityManager.persist(exam3);
//	    entityManager.flush();
	    
		List<Exam> results = examRepository.findAll();
 		assertThat(results).hasSize(3).contains(exam1, exam2, exam3);
		
	}
	
}
