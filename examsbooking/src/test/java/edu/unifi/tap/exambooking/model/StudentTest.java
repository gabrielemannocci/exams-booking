package edu.unifi.tap.exambooking.model;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


public class StudentTest {

	private Long studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String idNumber;
	
	@MockBean
    private Exam exam;
    
	private Student expectedEntity;
	
	@Before
    public void init() {
		studentId = 0L;
		firstName = "firstName";
		lastName = "lastName";
		email = "firstName.lastName@email.it";
		idNumber = "1000000";
		

		expectedEntity = new Student(studentId,firstName,lastName,email,idNumber);
	}
	
	@Test
	public void equalsObject() {
		Student actualEntity = new Student(0L, "firstName", "lastName", "firstName.lastName@email.it", "1000000");
		Assert.assertEquals(this.expectedEntity,actualEntity);
	}
	
	@Test
    public void equalsNull() {
		Exam actualEntity = null;
		Assert.assertNotEquals(this.expectedEntity, actualEntity);
    }
	

}
