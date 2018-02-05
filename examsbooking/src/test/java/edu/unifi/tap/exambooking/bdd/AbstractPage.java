package edu.unifi.tap.exambooking.bdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractPage {

	protected WebDriver driver;

	public static int port = 0;
	
	@FindBy(css = "label.error, .alert-error")
	private WebElement errors;

	public AbstractPage(WebDriver driver) {
		setDriver(driver);
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getErrors() {
		return errors.getText();
	}

	static void get(WebDriver driver) {
		driver.get(String.format("http://localhost:8080"));
	}
	
	static void get(WebDriver driver, String relativeUrl) {
		driver.get("http://localhost"
		+ (port > 0 ? ":" + port : "")
		+ "/" + relativeUrl);
		}
}
