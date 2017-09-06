package edu.unifi.tap.exambooking.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class RegisterStudentPage extends AbstractPage{
	
	@FindBy(id = "examId")
	private WebElement examCombo;
	@FindBy(id = "firstName")
	private WebElement firstNameField;
	@FindBy(id = "lastName")
	private WebElement lastNameField;
	@FindBy(id = "idNumber")
	private WebElement idNumberField;
	@FindBy(id = "email")
	private WebElement emailField;
	
//	@FindBy(css = "input[type=submit]")
	@FindBy(id = "submit")
	private WebElement submit;
	
    public RegisterStudentPage(WebDriver driver) {
        super(driver);
    }

    public <T> T createMessage(Class<T> resultPage, Long idExam, String firstNameField, String lastNameField, String idNumberField, String emailField) {
    	this.firstNameField.sendKeys(firstNameField);
        this.lastNameField.sendKeys(lastNameField);
    	this.idNumberField.sendKeys(idNumberField);
        this.emailField.sendKeys(emailField);
        this.submit.click();
        return PageFactory.initElements(driver, resultPage);
    }

    public static RegisterStudentPage to(WebDriver driver) {
        get(driver, "messages/form");
        return PageFactory.initElements(driver, RegisterStudentPage.class);
    }
    
}


