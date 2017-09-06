package edu.unifi.tap.exambooking.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.services.ExamConverterImpl;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;


@RunWith(SpringRunner.class)
public class ExamConverterTest {

	@TestConfiguration
    static class ExamConverterImplTestContextConfiguration {
  
        @Bean
        public Converter<String, Exam> examConverter() {
            return new ExamConverterImpl();
        }
    }
	
	private Exam expectedExam;
	
	@Autowired
	private Converter<String, Exam> examConverter;
	
	@MockBean
	private ExamService examServiceMock;
	
	@Before
	public void setup() {
		expectedExam = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
	}
	   
	@Test
	public void testExamConverter() throws ExamsNotFoundException{
		
		Mockito.when(examServiceMock.findById(1L)).thenReturn(expectedExam);
		Exam actual = examConverter.convert("1");
		assertThat(actual.getExamCode()).isEqualTo(expectedExam.getExamCode());
		
	}
	
	@Test
	public void testExamConverterNoExamFound() throws ExamsNotFoundException{
		Mockito.when(examServiceMock.findById(-1L)).thenThrow(new ExamsNotFoundException());
		Exam actual = examConverter.convert("1");
		assertThat(actual).isEqualTo(null);
//		examConverter.convert("-1");
	}
	
}
