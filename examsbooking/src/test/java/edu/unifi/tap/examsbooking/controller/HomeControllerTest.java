package edu.unifi.tap.examsbooking.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import webapp.example.demo.exception.ExamsNotFoundException;
//import webapp.example.demo.model.Exam;
//import webapp.example.demo.services.interfaces.StudentService;

/**
 * Unit test for Controller.
 * @author gabriele.mannocci
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

	/**
	 * Main entry point for server-side Spring MVC test support. 
	 */

	private MockMvc mockMvc;
	// senza Autowired e con setupController null pointer su serviceBean in HomeController


	@Before
	public void setupController(){
		mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}


	//	/**
	//	 * Mock of student service bean
	//	 */
	//	@MockBean
	//	private StudentService studentServiceMock;
	//
	//	/**
	//	 * Exams list 
	//	 */
	//	private List<Exam> exams;

	/**
	 * Check for status 200
	 * @throws Exception
	 */
	@Test
	public void testStatus200() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		mockMvc.perform(requestBuilder)
		.andDo(print())
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


	//    @Test
	//    public void testNoExamsFoundThenThrowException() throws Exception {
	//    	Mockito.when(studentServiceMock.findAllExams()).
	//    	thenThrow(new ExamsNotFoundException("For Testing"));
	//    	
	//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
	//		mockMvc.perform(requestBuilder)
	//		.andExpect(view().name("error"))
	//        .andExpect(model().attributeExists("errormessage"));
	//    }



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
