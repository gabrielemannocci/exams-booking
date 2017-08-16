package edu.unifi.tap.exambooking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "exam")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_id")
	private Long examId;
	
	@Column(name = "exam_code", nullable = false, length = 3)
	private String examCode;
	
	@Column(name = "exam_name", nullable = false)
	private String examName;

	@Column(name = "exam_date", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date examDate;
	
	@Column(name = "exam_classrooom")//, nullable = false)
	private String classRoom;
	
	@ManyToOne 
	private Student student;
	
	public Exam(){
		super();
	}



	public Exam(Long examId, String examCode, String examName, Date examDate, String classRoom) {
		super();
		this.examId = examId;
		this.examCode = examCode;
		this.examName = examName;
		this.examDate = examDate;
		this.classRoom = classRoom;
	}



	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
