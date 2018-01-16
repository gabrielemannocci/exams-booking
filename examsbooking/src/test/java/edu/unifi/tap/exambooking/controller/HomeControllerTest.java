package edu.unifi.tap.exambooking.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;

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

	/**
	 * Beans used in test
	 */
	private Student actualStudent;
	private Student expectedStudent;
	private Exam expectedExam;

	private static final String EXAMSBOOKING_RESULT_VIEW = "results";
	private static final String EXAMSBOOKING_ERROR_VIEW = "error";

	private static final String EXAM_PARAM_INDEX = "1"; 

	private static final String STUDENT_REGISTRATION_SUCCESS_MSG = "Student #x# correctly registered for exam #y# !";
	private static final String STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG = "Student #x# already registered for exam #y# !";

	private static final String NO_EXAMS_FOUND_ERROR_MSG = "No exams found!";
	private static final String INVALID_STUDENT_ERROR_MSG = "Something wrong happened storing Student data: missing field value";


	@Before
	public void setupController(){
		mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(examServiceMock,studentServiceMock)).build();
		actualStudent = new Student(null,"firstName","lastName","aValidEmailTest@email.com","0000000");
		expectedStudent = new Student(1L,"firstName","lastName","aValidEmailTest@email.com","0000000");
		expectedExam = new Exam(1L,"DWH", "Datawarehousing",new Date(), "Aula 103");
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



	/**
	 * Check for right indexview 
	 * @throws Exception
	 */
	@Test
	public void testFindAllExams() throws Exception{
		Mockito.when(this.examServiceMock.findAll()).thenReturn(exams);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		ModelAndViewAssert.assertCompareListModelAttribute(mockMvc.perform(requestBuilder).
				andReturn().getModelAndView(),"exams",exams);

		verify(this.examServiceMock, times(1)).findAll();
		verifyNoMoreInteractions(this.examServiceMock);
	}

	@Test
	public void testShouldThrowExceptionWhenNoExamsFound() throws Exception {
		ExamsNotFoundException ex = new ExamsNotFoundException(
				NO_EXAMS_FOUND_ERROR_MSG);
		Mockito.when(this.examServiceMock.findAll()).thenThrow(ex);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		mockMvc.perform(requestBuilder)
		.andExpect(view().name(EXAMSBOOKING_ERROR_VIEW))
		.andExpect(model().attribute("errormessage",ex.getMessage()));
	}


	/**
	 * Refactoring of previous version
	 * @throws Exception
	 */
	@Test
	public void testCaseStudentRegistration() throws Exception {
		Mockito.when(this.examServiceMock.findById(expectedExam.getExamId())).thenReturn(expectedExam);
		Mockito.when(this.studentServiceMock.registerStudent(actualStudent,expectedExam)).thenReturn(expectedStudent);
		Mockito.when(this.studentServiceMock.findStudentByIdNumberAndExam(expectedStudent.getIdNumber(), expectedExam.getExamId())).
		thenReturn(null);


		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
				.param("email", actualStudent.getEmail())
				.param("idNumber", actualStudent.getIdNumber())
				.param("firstName", actualStudent.getFirstName())
				.param("lastName", actualStudent.getLastName())
				.param("examParam",  EXAM_PARAM_INDEX);

		mockMvc.perform(requestBuilder)
		.andExpect(view().name(EXAMSBOOKING_RESULT_VIEW))
		.andExpect(model().attribute("message", STUDENT_REGISTRATION_SUCCESS_MSG
				.replace("#x#", expectedStudent.getLastName())
				.replace("#y#", expectedExam.getExamName())));

		verify(this.examServiceMock, times(1)).findById(expectedExam.getExamId());
		verify(this.studentServiceMock, times(1)).registerStudent(actualStudent,expectedExam);
		verify(this.studentServiceMock, times(1)).findStudentByIdNumberAndExam(expectedStudent.getIdNumber(),expectedExam.getExamId());
	}

	@Test
	public void testWhenExamNotFoundThrowException() throws Exception {
		ExamsNotFoundException ex = new ExamsNotFoundException(NO_EXAMS_FOUND_ERROR_MSG);
		Mockito.when(this.examServiceMock.findById(expectedExam.getExamId())).thenThrow(ex);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
				.param("email", expectedStudent.getEmail())
				.param("idNumber", expectedStudent.getIdNumber())
				.param("firstName", expectedStudent.getFirstName())
				.param("lastName", expectedStudent.getLastName())
				.param("examParam", expectedExam.getExamId().toString());
		mockMvc.perform(requestBuilder)
		.andExpect(view().name("error"))
		.andExpect(model().attribute("errormessage",ex.getMessage()));
	}

	@Test
	public void whenStudentAlreadyRegisteredForExamShouldRedirectToErrorView() throws Exception {
		Mockito.when(this.examServiceMock.findById(expectedExam.getExamId())).thenReturn(expectedExam);
		Mockito.when(this.studentServiceMock.findStudentByIdNumberAndExam(expectedStudent.getIdNumber(), expectedExam.getExamId())).thenReturn(expectedStudent);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
				.param("email", expectedStudent.getEmail())
				.param("idNumber", expectedStudent.getIdNumber())
				.param("firstName", expectedStudent.getFirstName())
				.param("lastName", expectedStudent.getLastName())
				.param("examParam", EXAM_PARAM_INDEX);

		mockMvc.perform(requestBuilder)
		.andExpect(view().name("error"))
		.andExpect(model().attribute("errormessage",
				STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG.replace("#x#", expectedStudent.getLastName())
				.replace("#y#", expectedExam.getExamName())));

		InOrder inOrder = Mockito.inOrder(examServiceMock,studentServiceMock);
		inOrder.verify(examServiceMock).findById(expectedExam.getExamId());
		inOrder.verify(studentServiceMock).findStudentByIdNumberAndExam(expectedStudent.getIdNumber(), expectedExam.getExamId());

	}


	@Test
	public void whenMissingStudentFieldShouldThrowInvalidStudentException() throws Exception {
		Mockito.when(this.examServiceMock.findById(expectedExam.getExamId())).thenReturn(expectedExam);
		Mockito.when(this.studentServiceMock.findStudentByIdNumberAndExam(expectedStudent.getIdNumber(), expectedExam.getExamId())).thenReturn(null);

		InvalidStudentException ex = new InvalidStudentException(INVALID_STUDENT_ERROR_MSG);
		actualStudent.setFirstName("");
		Mockito.when(this.studentServiceMock.registerStudent(actualStudent, expectedExam)).thenThrow(ex);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
				.param("email", actualStudent.getEmail())
				.param("idNumber", actualStudent.getIdNumber())
				.param("firstName",actualStudent.getFirstName())
				.param("lastName", actualStudent.getLastName())
				.param("examParam", expectedExam.getExamId().toString());
		mockMvc.perform(requestBuilder)
		.andExpect(view().name("error"))
		.andExpect(model().attribute("errormessage",INVALID_STUDENT_ERROR_MSG));
	}
}
