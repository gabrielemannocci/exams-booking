package edu.unifi.tap.examsbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages="edu.unifi.tap.exambooking")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ExamsbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsbookingApplication.class, args);
	}
}
