package edu.unifi.tap.exambooking.ui;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;
import edu.unifi.tap.exambooking.ExamsbookingApplication;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ExamsbookingApplication.class })
public class SeleniumTest {

	@Autowired
	WebApplicationContext context;
	
	WebDriver webDriver;
	
	@FindBy(css = "input[type=submit]")
	private WebElement submit;
	
	private WebElement nome;
	
	@Before
	public void setup() {

		webDriver = MockMvcHtmlUnitDriverBuilder
				.webAppContextSetup(context)
				.build();
	}
	
	@Test
 	public void getHomePage() throws ExamsNotFoundException, InterruptedException, MalformedURLException{
		webDriver.get(String.format("http://localhost:8080/"));
		System.out.println("------------------------");
		System.out.println(webDriver.getTitle());
		System.out.println("------------------------");
		System.out.println(webDriver.getPageSource());
		System.out.println();
		
	}
	
	@After 
	public  void cleanUp(){
	    if (webDriver != null) {
	    	webDriver.close();
	    	webDriver.quit();
	    }
	}
}
