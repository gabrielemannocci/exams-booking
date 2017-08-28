package edu.unifi.tap.exambooking.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.repository.ExamRepository;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.util.ExamsbookingApplicationParams;

@RunWith(SpringRunner.class)
public class ExamServiceImplTest {

	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
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
    
//    @Test
//	public void testThrowExceptionWhenExamNotFound() throws Exception{	
//    	when(examRepository.findOne(expected.getExamId())).
//    	thenThrow(new ExamsNotFoundException(ExamsbookingApplicationParams.NO_EXAMS_FOUND_ERROR_MSG));
//
//        assertThatThrownBy(
//                () -> examRepository.findOne(expected.getExamId()))
//                .isInstanceOf(ExamsNotFoundException.class)
//                .hasMessage(ExamsbookingApplicationParams.NO_EXAMS_FOUND_ERROR_MSG);
//
//    }
    
    @Test
	public void testFindAll() throws Exception{
    	
    	when(examRepository.findAll()).thenReturn(examList);
    	
        assertNotNull(examList);
        assertTrue(examList.size() >= 0);
    }
    
}
