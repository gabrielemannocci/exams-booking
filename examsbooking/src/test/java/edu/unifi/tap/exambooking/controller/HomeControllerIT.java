package edu.unifi.tap.exambooking.controller;
//package edu.unifi.tap.examsbooking.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.ModelAndViewAssert;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import edu.unifi.tap.exambooking.model.Exam;
//import edu.unifi.tap.exambooking.service.interfaces.ExamService;
//import edu.unifi.tap.examsbooking.ExamsbookingApplication;
//
//
//
//	@RunWith(SpringRunner.class)
//	@WebAppConfiguration
//	@ContextConfiguration(classes = { ExamsbookingApplication.class })
//	public class HomeControllerIT {
//
//	    @Autowired
//	    private WebApplicationContext wac;
//
//		@MockBean
//		private ExamService examServiceMock;
//		@MockBean 
//		private ClassTest classTestMock;
//		
//	    private MockMvc mockMvc;
//	    
//		/**
//		 * Exams list 
//		 */
//		private List<Exam> exams;
//		
//	    @Before
//	    public void setup() {
//	        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//	    }
//	    
//	    /**
//	     * Check if ServletContext is being attached in WebApplicationContextis
//	     *  and HomeController is being loaded properly
//	     */
//	    @Test
//	    public void givenWac_whenServletContext_thenItProvidesHomeController() {
//	        ServletContext servletContext = wac.getServletContext();
//	         
//	        Assert.assertNotNull(servletContext);
//	        Assert.assertTrue(servletContext instanceof MockServletContext);
//	        Assert.assertNotNull(wac.getBean("homeController"));
//	    }
//	    
//		@Test
//		public void testReturnHomeView() throws Exception{
//			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
//			assertThat(classTestMock).isNotNull();
//			Mockito.when(classTestMock.findAll()).thenReturn(exams);
//			ModelAndViewAssert.assertViewName(mockMvc.perform(requestBuilder).andReturn().getModelAndView(), "index");
//		}
//}
