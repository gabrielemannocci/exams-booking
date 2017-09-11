package edu.unifi.tap.exambooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages="edu.unifi.tap.exambooking")
public class ExamsbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsbookingApplication.class, args);
	}
}
