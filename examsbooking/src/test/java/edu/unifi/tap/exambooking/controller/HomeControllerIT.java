package edu.unifi.tap.exambooking.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import javax.servlet.ServletContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import edu.unifi.tap.exambooking.ExamsbookingApplication;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ExamsbookingApplication.class)
@AutoConfigureMockMvc
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@DatabaseSetup("/examData.xml")
public class HomeControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ExamService examService;

	/**
	 * Beans used in test
	 */
	private static Student actualStudent;
	private static Student expectedStudent;
	private static Exam expectedExam;
	
	private static final String EXAMSBOOKING_HOME_VIEW = "index";
	private static final String EXAMSBOOKING_RESULT_VIEW = "results";
	private static final String EXAMSBOOKING_ERROR_VIEW = "error";
	
	private static final String MODELVIEW_RESULT = "message";
	private static final String MODELVIEW_ERROR = "errormessage";
	
	private static final String STUDENT_REGISTRATION_SUCCESS_MSG = "Student #x# correctly registered for exam #y# !";
	private static final String STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG = "Student #x# already registered for exam #y# !";
			
	private static final String NO_EXAMS_FOUND_ERROR_MSG = "No exams found!";
	private static final String INVALID_STUDENT_ERROR_MSG = "Something wrong happened storing Student data: missing field value";
	
	@BeforeClass
	public static void setupController(){
		actualStudent = new Student(null,"firstName","lastName","aValidEmailTest@email.com","0000000");
		expectedStudent = new Student(1L,"firstName","lastName","aValidEmailTest@email.com","0000000");
		expectedExam = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
		
		System.out.println();
		System.out.println("+++++ START INTEGRATION TESTS +++++");
		System.out.println();

	}
	
	/**
	 * Check if ServletContext is being attached in WebApplicationContextis
	 *  and HomeController is being loaded properly
	 * @throws Exception 
	 */
	@Test
	public void givenWac_whenServletContext_thenItProvidesHomeController() throws Exception {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		ModelAndViewAssert.assertViewName(mockMvc.perform(requestBuilder).andReturn().getModelAndView(), "index");

	}
	
	@Test
	@DatabaseSetup(value = "/examData.xml")
	public void testReturnHomeView() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		ModelAndViewAssert.assertViewName(mockMvc.perform(requestBuilder).
				andReturn().getModelAndView(), "index");
	}
	
	@Test
	@ExpectedDatabase(value = "/afterRegistrationData.xml")
	public void testRegisterStudent() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
		.param("email", actualStudent.getEmail())
		.param("idNumber", actualStudent.getIdNumber())
		.param("firstName", actualStudent.getFirstName())
		.param("lastName", actualStudent.getLastName())
		.param("examParam",  "1"); //expectedExam.getExamId().toString()

		mockMvc.perform(requestBuilder)
		.andExpect(view().name(EXAMSBOOKING_RESULT_VIEW))
		.andExpect(model().attribute("message", STUDENT_REGISTRATION_SUCCESS_MSG
		.replace("#x#", expectedStudent.getLastName())
		.replace("#y#", expectedExam.getExamName())));
		
	}
	
	
	
	@Test
	@ExpectedDatabase(value = "/examData.xml",table="Exam")
	public void testRegisterInvalidStudentThenThrowException() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
		.param("email", actualStudent.getEmail())
		.param("idNumber", "")
		.param("firstName", actualStudent.getFirstName())
		.param("lastName", actualStudent.getLastName())
		.param("examParam",  "1");

		mockMvc.perform(requestBuilder)
		.andExpect(view().name("error"))
		.andExpect(model().attribute("errormessage",INVALID_STUDENT_ERROR_MSG));
		
	}

	@AfterClass
	public static void afterTest(){
		System.out.println();
		System.out.println("----- END INTEGRATION TESTS -----");
		System.out.println();

		
	}
}


