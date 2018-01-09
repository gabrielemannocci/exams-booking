package edu.unifi.tap.exambooking.bdd;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberTest {

	static final Logger LOGGER = Logger.getLogger(CucumberTest.class);
	
	@BeforeClass
	public static void beforeTest(){
		LOGGER.info("");
		LOGGER.info("+++++ START FUNCTIONAL TESTS +++++");
		LOGGER.info("");
	}
	
	@AfterClass
	public static void afterTest(){
		LOGGER.info("");
		LOGGER.info("----- END FUNCTIONAL TESTS -----");
		LOGGER.info("");
	}
}
