package edu.unifi.tap.exambooking.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessPage extends AbstractPage{

	@FindBy(id = "message")
	private WebElement message;
	
	public SuccessPage(WebDriver driver) {
		super(driver);
	}

	public String getMessage() {
		return message.getText();
	}
}
