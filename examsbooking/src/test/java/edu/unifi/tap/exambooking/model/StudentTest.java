package edu.unifi.tap.exambooking.model;

import java.util.Date;
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
		exam = new Exam(0L, "XYZ", "TEST", new Date(),"Aula 101");
	}
	
	@Test
	public void equalsObject() {
		Student actualEntity = new Student(0L, "firstName", "lastName", "firstName.lastName@email.it", "1000000");
		Assert.assertEquals(this.expectedEntity,actualEntity);
	}
	
	@Test
    public void equalsNull() {
		Student actualEntity = null;
		Assert.assertNotEquals(this.expectedEntity, actualEntity);
    }
	
	@Test
	public void equalsHashCode() {
		Student actualEntity = new Student(0L, "firstName", "lastName", "firstName.lastName@email.it", "1000000");
		Assert.assertEquals(this.expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void notEqualsHashCode() {
		Long studentId = 99L;
		Student actualEntity = new Student(studentId, "firstName", "lastName", "firstName.lastName@email.it", "1000000");
		Assert.assertNotEquals(expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void object_set_property_student_id () {
		Student actualEntity = new Student();
		actualEntity.setStudentId(0L);
		Assert.assertEquals(expectedEntity.getStudentId(),actualEntity.getStudentId());
	}
	
	@Test
	public void object_set_property_student_firstname () {
		Student actualEntity = new Student();
		actualEntity.setFirstName( "firstName");
		Assert.assertEquals(expectedEntity.getFirstName(),actualEntity.getFirstName());
	}
	
	@Test
	public void object_set_property_student_lastname () {
		Student actualEntity = new Student();
		actualEntity.setLastName("lastName");
		Assert.assertEquals(expectedEntity.getLastName(),actualEntity.getLastName());
	}
	
	@Test
	public void object_set_property_student_email () {
		Student actualEntity = new Student();
		actualEntity.setEmail("firstName.lastName@email.it");
		Assert.assertEquals(expectedEntity.getEmail(),actualEntity.getEmail());
	}
	
	@Test
	public void object_set_property_student_idnumber () {
		Student actualEntity = new Student();
		actualEntity.setIdNumber("1000000");
		Assert.assertEquals(expectedEntity.getIdNumber(),actualEntity.getIdNumber());
	}
	
	@Test
	public void object_set_property_exam () {
		Student actualEntity = new Student();
		actualEntity.setExam(exam);;
		expectedEntity.setExam(exam);
		Assert.assertEquals(expectedEntity.getExam(),actualEntity.getExam());
	}
	
	   @Test
	    public void notEqualsById() {
		Long  studentId = 99L;
		Student actualEntity = new Student(studentId, "firstName", "lastName", "firstName.lastName@email.it", "1000000");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	    }
	   
	   @Test
	    public void notEqualsByFirstName() {
		String firstName = "different name";
		Student actualEntity = new Student(0L, firstName, "lastName", "firstName.lastName@email.it", "1000000");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	    }  
	   
	   @Test
	    public void notEqualsByLastName() {
		String lastName = "different name";
		Student actualEntity = new Student(studentId, "firstName", lastName, "firstName.lastName@email.it", "1000000");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	    }  
	   
	   @Test
	    public void notEqualsByEmail() {
		   String email = "test@email.it";
		   Student actualEntity = new Student(studentId, "firstName", "lastName", email, "1000000");
		   Assert.assertNotEquals(expectedEntity, actualEntity);
	    }  
	   @Test
	    public void notEqualsByIdNumber() {
		   String idNumber = "0000000";
		   Student actualEntity = new Student(studentId, "firstName", "lastName", email, idNumber);
		   Assert.assertNotEquals(expectedEntity, actualEntity);
	    } 
	   @Test
	    public void notEqualsByExam() {
		   expectedEntity.setExam(exam);
		   Student actualEntity = new Student(0L, "firstName", "lastName", "firstName.lastName@email.it", "1000000");
		   Exam actualExam = new  Exam(99L, "ABC", "TEST", new Date(),"Aula 103");
		   actualEntity.setExam(actualExam);
		   Assert.assertNotEquals(expectedEntity, actualEntity);
	    }
}
