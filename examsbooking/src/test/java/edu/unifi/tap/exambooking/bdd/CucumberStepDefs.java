package edu.unifi.tap.exambooking.bdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.unifi.tap.exambooking.ExamsbookingApplication;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExamsbookingApplication.class)
@SpringBootTest
public class CucumberStepDefs {

	@Autowired
	private WebApplicationContext context;


	private RegisterStudentPage registerStudentPage;

	private SuccessPage successPage;

	private ErrorPage errorPage;

	@Autowired
	private ExamService examService;

	private String expectedMessage;
	private String expectedErrorMessage;

	private static final String STUDENT_REGISTRATION_SUCCESS_MSG = "Student #x# correctly registered for exam #y# !";
	private static final String INVALID_STUDENT_ERROR_MSG = "Something wrong happened storing Student data: missing field value";
	private WebDriver driver;


	@BeforeClass
	public static void beforeTest(){
		System.out.println();
		System.out.println("+++++ START FUNCTIONAL TESTS +++++");
		System.out.println();
	}

	@Given("Registration page")
	public void homePage() throws Throwable {
		driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(context).build();
		registerStudentPage = RegisterStudentPage.to(driver);
	}

	@When("^I'll select exam with id (\\d+) from dropdown list$")
	public void selectExam(int examId) throws Throwable {
		Select dropdown = new Select(driver.findElement(By.id("examId")));
		dropdown.selectByIndex(examId);
	}

	@And("^I'll insert student information (.+), (.+), (.+), (.+)$")
	public void registerStudent(String firstName, String lastName, String idNumber, String email) throws Throwable {
		successPage = registerStudentPage.createMessage(SuccessPage.class, firstName, lastName, idNumber, email);
	}


	@And("^I'll insert student information incomplete such as (.+), (.+), (.+)$")
	public void registerStudentMissingInfo(String firstName, String lastName, String email) throws Throwable {
		errorPage = registerStudentPage.createMessage(ErrorPage.class, firstName, lastName, "", email);
	}

	@Then("^User (.+) is correctly registered for exam$")
	public void getSuccessPage(String lastName) throws Throwable {

		Exam found = examService.findById(1L);
		expectedMessage = STUDENT_REGISTRATION_SUCCESS_MSG
				.replace("#x#", lastName)
				.replace("#y#", found.getExamName());

		assertThat(successPage.getMessage()).isEqualTo(expectedMessage);
		if(driver != null) {
			driver.close();
		}
	}

	
	@Then("^Invalid student exception is thrown")
	public void getErrorPage() throws Throwable {

		expectedErrorMessage = INVALID_STUDENT_ERROR_MSG;
		assertThat(errorPage.getErrorMessage()).isEqualTo(expectedErrorMessage);
		if(driver != null) {
			driver.close();
		}
	}
	
	@AfterClass
	public static void afterTest(){
		System.out.println();
		System.out.println("----- END FUNCTIONAL TESTS -----");
		System.out.println();

		
	}
}
