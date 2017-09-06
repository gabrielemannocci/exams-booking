package edu.unifi.tap.exambooking.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage extends AbstractPage{

	@FindBy(id = "errormessage")
	private WebElement errorMessage;
	
	public ErrorPage(WebDriver driver) {
		super(driver);
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

}
