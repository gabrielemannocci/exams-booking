package edu.unifi.tap.exambooking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ExamsbookingApplication.class)
public class ExamsbookingApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMain()
	    {
		     ExamsbookingApplication.main(new String[]{
	                "--spring.main.web-environment=false"
	        });
	    }
}
