package edu.unifi.tap.exambooking.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import webapp.example.demo.exception.ExamsNotFoundException;
//import webapp.example.demo.model.Exam;
//import webapp.example.demo.services.interfaces.StudentService;

import edu.unifi.tap.exambooking.controller.HomeController;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.exception.StudentAlreadyRegisteredForExamException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.model.StudentBuilder;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;

/**
 * Unit test for Controller.
 * @author gabriele.mannocci
 *
 */
//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

	/**
	 * Main entry point for server-side Spring MVC test support. 
	 */
	private MockMvc mockMvc;

	/**
	 * Mock of student service bean
	 */
	@MockBean (name = "examService")
	private ExamService examServiceMock;
	/**
	 * Mock of student service bean
	 */
	@MockBean (name = "studentService")
	private StudentService studentServiceMock;

	private Student actual;
	
	//Se definito com spy chiama il metodo reale
	@Before
	public void setupController(){
		mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(examServiceMock,studentServiceMock)).build();
		actual = new Student(null,"firstName","lastName","aValidEmailTest@email.com","0000000",1L);
	}

	/**
	 * Exams list 
	 */
	private List<Exam> exams= new ArrayList<Exam>();

	/**
	 * Check for status 200
	 * @throws Exception
	 */
	@Test
	public void testStatus200() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		mockMvc.perform(requestBuilder)
		.andExpect(status().is2xxSuccessful());
	}

	/**
	 * Check for right indexview 
	 * @throws Exception
	 */
	@Test
	public void testReturnHomeView() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		ModelAndViewAssert.assertViewName(mockMvc.perform(requestBuilder).andReturn().getModelAndView(), "index");
	}



	//ORDINE INVOCAZIONI
	//ARGUMENT CAPTUR
	/**
	 * Check for right indexview 
	 * @throws Exception
	 */
	@Test
	public void testFindAllExams() throws Exception{
		assertThat(this.examServiceMock).isNotNull();
		Mockito.when(this.examServiceMock.findAll()).thenReturn(exams);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		ModelAndViewAssert.assertCompareListModelAttribute(mockMvc.perform(requestBuilder).
				andReturn().getModelAndView(),"exams",exams);

		verify(this.examServiceMock, times(1)).findAll();
		verifyNoMoreInteractions(this.examServiceMock);
	}

	@Test
	public void testShouldThrowExceptionWhenNoExamsFound() throws Exception {
		ExamsNotFoundException ex = new ExamsNotFoundException("No exams found!");
		Mockito.when(this.examServiceMock.findAll()).thenThrow(ex);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		mockMvc.perform(requestBuilder)
		.andExpect(view().name("error"))
		.andExpect(model().attribute("errormessage",ex.getMessage()));
	}

	@Test
	public void testBaseCaseUserRegistration() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
				.param("email", "aValidEmailTest@email.com")
				.param("idNumber", "0000000")
				.param("firstName", "firstName")
				.param("lastName", "lastName")
				.param("examId", "1");
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(view().name("results"))
		.andExpect(model().attributeHasNoErrors());
	}
	
	@Test
	public void whenStudentAlreadyRegisteredForExamShouldThrowException() throws StudentAlreadyRegisteredForExamException {
		
		assertThat(this.studentServiceMock).isNotNull();
		Mockito.when(this.studentServiceMock.findByIdNumber(actual.getIdNumber(), actual.getExamId())).thenThrow(new StudentAlreadyRegisteredForExamException());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
				.param("email", "aValidEmailTest@email.com")
				.param("idNumber", "0000000")
				.param("firstName", "firstName")
				.param("lastName", "lastName")
				.param("examId", "1");
		
//		mockMvc.perform(requestBuilder)
//		.andExpect(status().isOk())
//		.andExpect(view().name("error"))
//		.andExpect(model().attributeHasNoErrors());
	}
	
//	@Test
//	public void testBaseCaseUserRegistration() throws Exception {
//		
//		Student student = new Student();
//		student.setStudentId(0L);
//		student.setFirstName("firstName");
//		student.setLastName("lastName");
//		student.setEmail("aValidEmailTest@email.com");
//		student.setExamId(1L);
//		student.setIdNumber("0000000");
//		
//		System.out.println(student.toString());
//		System.out.println(student.getFirstName());
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
//				.param("email", "aValidEmailTest@email.com")
//				.param("idNumber", "0000000")
//				.param("firstName", "firstName")
//				.param("lastName", "lastName")
//				.param("examId", "1");
//		mockMvc.perform(requestBuilder)
//		.andExpect(status().isOk())
//		.andExpect(forwardedUrl("results"))
//		.andExpect(model().attributeHasNoErrors())
//		.andExpect(model().attributeExists("studentId", student.getFirstName()));
//	}

	//test findById ==> view error
	//test for each parameters  ==> view error
	
//	.andExpect(status().isOk())
//    .andExpect(view().name(&quot;todo/add&quot;))
//    .andExpect(forwardedUrl(&quot;/WEB-INF/jsp/todo/add.jsp&quot;))
//    .andExpect(model().attributeHasFieldErrors(&quot;todo&quot;, &quot;title&quot;))
    
    
	//	Arrays.asList(first, second)
	//    /**
	//     * Check for right indexview 
	//     * @throws Exception
	//     */
	//	@Test
	//	public void testStatus200() throws Exception{
	//		assertThat(this.studentServiceMock).isNotNull();
	//        
	//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
	//		mockMvc.perform(requestBuilder)
	//		.andExpect(view().name("index"));
	//	}






	//	@Test
	//	public void testHTTPGetRequest() throws Exception{
	//		Mockito.when(studentServiceMock.findAllExams()).thenReturn(exams);
	//
	//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
	//		mockMvc.perform(requestBuilder)
	//				.andDo(print())
	//				.andExpect(status().isOk())
	//				.andReturn();
	//		
	//		verify(studentServiceMock, times(1)).findAllExams();
	//		verifyNoMoreInteractions(studentServiceMock);
	//	}
	//	
	//	@Test
	//	public void testBindingResultHasError() throws Exception{
	//	}
	//
	//	@Test
	//	public void testCreateNewStudent() throws Exception{
	//	}
	//	
	//	@Test
	//	public void testCreateNewStudentInvalidForm() throws Exception{
	//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/")
	//				.param("email", "invalidemailtest.com")
	//                .param("idNumber", "0000000")
	//                .param("firstName", "firstName")
	//                .param("lastName", "lastName");
	//        this.mockMvc.perform(requestBuilder)
	//                .andExpect(status().isOk())
	//                .andExpect(view().name("results"))
	//                .andExpect(model().attributeHasFieldErrors("student","email"));
	//	}
	//	
	//	@Test 
	//	public void testStudentAlreadyExits() throws Exception{
	//        
	//	}


}
