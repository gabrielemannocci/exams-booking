package edu.unifi.tap.exambooking.bdd;

import static org.assertj.core.api.Assertions.assertThat;
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
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.unifi.tap.exambooking.ExamsbookingApplication;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.ui.ErrorPage;
import edu.unifi.tap.exambooking.ui.RegisterStudentPage;
import edu.unifi.tap.exambooking.ui.SuccessPage;

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

	private WebDriver driver;



	@Given("Registration page")
	public void homePage() throws Throwable {
		driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(context).build();
		registerStudentPage = RegisterStudentPage.to(driver);
	}

	@When("I'll select an exam from dropdown list")
	public void selectExam() throws Throwable {
		Select dropdown = new Select(driver.findElement(By.id("examId")));
		dropdown.selectByIndex(1);
	}

	@And("I'll insert student information")
	public void registerStudent() throws Throwable {
		successPage = registerStudentPage.createMessage(SuccessPage.class, "firstNameField", "lastNameField", "idNumberField", "emailField");
	}

	@Then("User is registered for exam")
	public void getSuccessPage() throws Throwable {
		
		Exam found = examService.findById(1L);
		expectedMessage = STUDENT_REGISTRATION_SUCCESS_MSG
				.replace("#x#", "lastNameField")
				.replace("#y#", found.getExamName());
		
		assertThat(successPage.getMessage()).isEqualTo(expectedMessage);
		if(driver != null) {
			driver.close();
		}
	}


}
