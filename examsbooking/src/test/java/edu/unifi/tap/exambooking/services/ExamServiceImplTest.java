package edu.unifi.tap.exambooking.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.repository.ExamRepository;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;

@RunWith(SpringRunner.class)
public class ExamServiceImplTest {

	@TestConfiguration
    static class ExamServiceImplTestContextConfiguration {
  
        @Bean
        public ExamService examService() {
            return new ExamServiceImpl();
        }
    }
	
	@MockBean
	private ExamRepository examRepository;
	
	@Autowired
	private ExamService examService;
	
	
	private Exam expected;
	private List<Exam> examList;
	
    @Before
    public void setup() {
    	 expected = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
    	 examList = new ArrayList<Exam>();
    }
    
    
    @Test
	public void testFindById() throws Exception{	
    	when(examRepository.findOne(expected.getExamId())).thenReturn(expected);

        Exam found = examService.findById(expected.getExamId());
        assertThat(found.getExamCode()).isEqualTo(expected.getExamCode());
    }
    
    
    @Test(expected = ExamsNotFoundException.class)
	public void testFindAll() throws Exception{
    	
    	when(examRepository.findAll()).thenReturn(examList);
    	
    	examService.findAll();
//        assertNotNull(examsFound);
//        assertTrue(examsFound.size() >0);
    }
    
    @Test
	public void testFindAllEmptyList() throws Exception{
    	examList.add(expected);
    	when(examRepository.findAll()).thenReturn(examList);
    	
    	List<Exam> examsFound = examService.findAll();
        assertNotNull(examsFound);
        assertTrue(examsFound.size() >0);

//        assertThatThrownBy(new ExamsNotFoundException());
    }
}
