package edu.unifi.tap.exambooking.model;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private String dateInString = "12-09-2017";

	private Exam expectedEntity;

	@Before
	public void init() throws ParseException {

		examId = 0L;
		examCode = "XYZ";
		examName = "TEST";
		examClassRoom = "Aula 101";
		examDate = formatter.parse(dateInString);
		expectedEntity = new Exam(examId,examCode,examName,examDate,examClassRoom );

	}


	@Test
	public void equalsObject() throws ParseException {
		Date date = formatter.parse(dateInString);
		Exam actualEntity = new Exam(0L, "XYZ", "TEST", date,"Aula 101");
		Assert.assertEquals(this.expectedEntity,actualEntity);
	}

	@Test
	public void equalsNull() {
		Exam actualEntity = null;
		Assert.assertNotEquals(this.expectedEntity, actualEntity);
	}

	@Test
	public void notEqualsById() {
		Long id = 99L;
		Exam actualEntity = new Exam(id, examCode, "TEST", new Date(),"Aula 101");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	}

	@Test
	public void notEqualsByDesc() {
		String examCode = "ZYX";
		Exam actualEntity = new Exam(0L, examCode, "TEST", new Date(),"Aula 101");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	}

	@Test
	public void notEqualsByName() {
		String examName = "different name";
		Exam actualEntity = new Exam(0L, "XYZ", examName, new Date(),"Aula 101");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	}  

	@Test
	public void notEqualsByDate() {
		Date differentDate = new Date(10000);
		Exam actualEntity = new Exam(0L, "XYZ", examName, differentDate,"Aula 101");
		Assert.assertNotEquals(expectedEntity, actualEntity);
	}  

	@Test
	public void notEqualsByClassRoom() {
		String examClassRoom = "Aula 100";
		Exam actualEntity = new Exam(0L, "XYZ", "TEST", new Date(), examClassRoom);
		Assert.assertNotEquals(expectedEntity, actualEntity);
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
	public void equalsExamDateNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(null);
		this.expectedEntity.setExamDate(null);
		Assert.assertFalse(this.expectedEntity.equals(actualEntity));
	}

	@Test
	public void equalsExamNameNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(null);
		actualEntity.setExamDate(examDate);
		this.expectedEntity.setExamName(null);
		Assert.assertFalse(this.expectedEntity.equals(actualEntity));
	}
	@Test
	public void equalsExamClassRoomNull() {
		Exam actualEntity = new Exam();
		actualEntity.setExamCode(examCode);
		actualEntity.setExamName(examName);
		actualEntity.setExamDate(examDate);
		this.expectedEntity.setExamClassRoom(null);
		Assert.assertFalse(this.expectedEntity.equals(actualEntity));
	}

	@Test
	public void notEqualsByClass() {
		Student actualEntity = new Student();
		Assert.assertFalse(expectedEntity.equals(actualEntity));
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
		Exam actualEntity = new Exam(examId,examCode,examName,examDate,examClassRoom);
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
	public void object_set_property_exam_date () throws ParseException {
		Exam actualEntity = new Exam();
		actualEntity.setExamDate(formatter.parse(dateInString));
		Assert.assertEquals(expectedEntity.getExamDate(),actualEntity.getExamDate());
	}


	@Test
	public void not_equal_exam_name () throws ParseException {
		Exam actualEntity = new Exam(0L, "XYZ", "Datawarehousing", formatter.parse(dateInString), examClassRoom);
		Assert.assertFalse(expectedEntity.equals(actualEntity));
	}

	@Test
	public void not_equal_exam_classRoom () throws ParseException {
		Exam actualEntity = new Exam(0L, "XYZ", "TEST", formatter.parse(dateInString), "Aula 103");
		Assert.assertFalse(expectedEntity.equals(actualEntity));
	}
}
