package edu.unifi.tap.exambooking.model;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ExamTest {

	
	private Long examId;
	private String examCode;
	private String examName;
	private Date examDate;
	private String examClassRoom;

	private Exam expectedEntity;

	@Before
	public void init() {

		examId = 0L;
		examCode = "XYZ";
		examName = "TEST";
		examDate = new Date();
		examClassRoom = "Aula 101";
		
		expectedEntity = new Exam(examId,examCode,examName,examDate,examClassRoom );

	}
	

	@Test
	public void equalsObject() {
		Exam actualEntity = new Exam(0L, "XYZ", "TEST", new Date(),"Aula 101");
		Assert.assertEquals(this.expectedEntity,actualEntity);
	}
	
	@Test
    public void equalsNull() {
		Exam actualEntity = null;
		Assert.assertNotEquals(this.expectedEntity, actualEntity);
    }
	

	
	@Test
	public void equalsExamCodeNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(null);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(examDate);
		this.expectedEntity.setExamCode(null);
		Assert.assertFalse(this.expectedEntity.equals(actualEntity));
	}
	
	@Test
	public void equalsExamNameNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(null);
		this.expectedEntity.setExamDate(null);
		Assert.assertFalse(this.expectedEntity.equals(actualEntity));
	}
	
	@Test
	public void equalsExamDateNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(null);
		actualEntity.setExamDate(examDate);
		this.expectedEntity.setExamName(null);
		Assert.assertFalse(this.expectedEntity.equals(actualEntity));
	}
	

	@Test
	public void equalsHashCodeExamCodeNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(null);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(examDate);
		this.expectedEntity.setExamCode(null);
		Assert.assertEquals(this.expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void equalsHashCodeExamNameNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(null);
		this.expectedEntity.setExamDate(null);
		Assert.assertEquals(this.expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void equalsHashCodeExamDateNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(null);
		actualEntity.setExamDate(examDate);
		this.expectedEntity.setExamName(null);
		Assert.assertEquals(this.expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void equalsHashCodeExamClassRoomNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(examDate);
		actualEntity.setExamClassRoom(null);
		this.expectedEntity.setExamClassRoom(null);
		Assert.assertEquals(this.expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void equalsHashCode() {
		Exam actualEntity = new Exam(examId,examCode,examName,examDate,examClassRoom );
		Assert.assertEquals(this.expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void notEqualsHashCode() {
		examId = 99L;
		Exam actualEntity = new Exam(examId,examCode,examName,examDate,examClassRoom );
		Assert.assertNotEquals(expectedEntity.hashCode(), actualEntity.hashCode());
	}
	
	@Test
	public void object_has_property_values_as () {
		Exam actualEntity = new Exam(examId,examCode,examName,examDate,"" );
		assertThat(expectedEntity, samePropertyValuesAs(actualEntity));
	}
	
	
	@Test
	public void object_set_property_exam_id () {
		Exam actualEntity = new Exam();
		actualEntity.setExamId(0L);
		Assert.assertEquals(expectedEntity.getExamId(),actualEntity.getExamId());
	}
	
	@Test
	public void object_set_property_exam_code () {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode("XYZ");
		Assert.assertEquals(expectedEntity.getExamCode(),actualEntity.getExamCode());
	}
	
	@Test
	public void object_set_property_exam_name () {
		Exam actualEntity = new Exam();
		actualEntity.setExamName("TEST");
		Assert.assertEquals(expectedEntity.getExamName(),actualEntity.getExamName());
	}
	
	@Test
	public void object_set_property_exam_date () {
		Exam actualEntity = new Exam();
		actualEntity.setExamDate(new Date());
		Assert.assertEquals(expectedEntity.getExamDate(),actualEntity.getExamDate());
	}
	
	@Test
	public void object_set_property_exam_classRoom () {
		Exam actualEntity = new Exam();
		actualEntity.setExamClassRoom("Aula 101");
		Assert.assertEquals(expectedEntity.getExamClassRoom(),actualEntity.getExamClassRoom());
	}
}
