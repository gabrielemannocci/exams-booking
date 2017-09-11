package edu.unifi.tap.exambooking.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import edu.unifi.tap.exambooking.ExamsbookingApplication;
import edu.unifi.tap.exambooking.model.Exam;


/**
 * An end to end test that validates the {@link CreateMessagePage}. A few things to notice:
 *
 * <ul>
 * <li>You will see that all the tests are the same as {@link WebDriverCreateMessageITests}. This shows how little difference
 * there is in how you would write the tests.</li>
 * <li>The only difference is how we initialize our {@link WebDriver}</li>
 * <li>We do not need to run the web application on a server for this test since we are using
 * {@link org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver}</li>
 * </ul>
 *
 * @author Rob Winch
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebMvcConfig.class, MockDataConfig.class})
//@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamsbookingApplication.class)
public class SetupSeleniumTest {
	
	@Autowired
	private WebApplicationContext context;

	private String expectedMessage;

	private String expectedErrorMessage;
	
	private static final String STUDENT_REGISTRATION_SUCCESS_MSG = "Student #x# correctly registered for exam #y# !";

	
	private WebDriver driver;

	private Exam expectedExam;
	@Before
	public void setup() {
//		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(context).build();
		expectedExam = new Exam(0L,"CSZ", "Codici e sicurezza",new Date(), "Aula 101");
	}

	@After
	public void destroy() {
		if(driver != null) {
			driver.close();
		}
	}

//	@Test
//	public void missingFieldWithJavascriptValidationDisplaysError() {
//		CreateMessagePage messagePage = CreateMessagePage.to(driver);
//		messagePage = messagePage.createMessage(CreateMessagePage.class, "", "");
//		assertThat(messagePage.getErrors()).isEqualTo("This field is required.");
//	}
//
//	@Test
//	public void missingFieldServerSideValidationDisplaysError() {
//		CreateMessagePage messagePage = CreateMessagePage.to(driver);
//		messagePage = messagePage.createMessage(CreateMessagePage.class, "Summary", "");
//		assertThat(messagePage.getErrors()).isEqualTo("Message is required.");
//	}

//	@Test
//	public void missingStudentFieldDisplaysError() {
//		
//		expectedErrorMessage = ExamsbookingApplicationParams.INVALID_STUDENT_ERROR_MSG;
//		
//		RegisterStudentPage page = RegisterStudentPage.to(driver);
//		ErrorPage errorPage = page.createMessage(ErrorPage.class, 0L, "", "lastNameField", "idNumberField", "emailField");
//		System.out.println(errorPage.getErrorMessage());
//		assertThat(errorPage.getErrorMessage()).isEqualTo(expectedErrorMessage);
//
//	}
	
	@Test
	public void successfullyRegisterStudent() throws ParseException {

		expectedMessage = STUDENT_REGISTRATION_SUCCESS_MSG
				.replace("#x#", "lastNameField")
				.replace("#y#", expectedExam.getExamName());
				
		RegisterStudentPage page = RegisterStudentPage.to(driver);

		SuccessPage successPage = page.createMessage(SuccessPage.class, 0L, "firstNameField", "lastNameField", "idNumberField", "emailField");
		assertThat(successPage.getMessage()).isEqualTo(expectedMessage);
	}
}

