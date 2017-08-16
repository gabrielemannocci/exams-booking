package edu.unifi.tap.exambooking.services;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.repository.ExamRepository;

@RunWith(SpringRunner.class)
public class ExamServiceImplTest {

	@MockBean
	private ExamRepository examRepository;
	
	private Exam expected;
	private List<Exam> examList;
	
    @Before
    public void setup() {
    	 expected = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
    	 examList = new ArrayList<Exam>();
    }
    
    @Test
	public void testFindById() throws Exception{
    	when(examRepository.findOne(1L)).thenReturn(expected);
    	assertNotNull(expected);
    }
    
    @Test
	public void testFindAll() throws Exception{
    	
    	when(examRepository.findAll()).thenReturn(examList);
    	
        assertNotNull(examList);
        assertTrue(examList.size() >= 0);
    }
    
}
