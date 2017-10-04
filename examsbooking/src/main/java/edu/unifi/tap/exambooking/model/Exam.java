package edu.unifi.tap.exambooking.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date examDate;
	
	@Column(name = "exam_classrooom")
	private String examClassRoom;

	
	public Exam(){
		super();
	}



	public Exam(Long examId, String examCode, String examName, Date examDate, String classRoom) {
		super();
		this.examId = examId;
		this.examCode = examCode;
		this.examName = examName;
		this.examDate = examDate;
		this.examClassRoom = classRoom;
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


	public String getExamClassRoom() {
		return examClassRoom;
	}



	public void setExamClassRoom(String examClassRoom) {
		this.examClassRoom = examClassRoom;
	}




	 @Override
	    public int hashCode() {
		return Objects.hash(examId, examCode, examName, examDate, examClassRoom);
	}



	 @Override
	    public boolean equals(Object obj) {
		if (obj == null) {
		    return false;
		}
		if (this.getClass() != obj.getClass())
		    return false;
		final Exam exam = (Exam) obj;
		if (!Objects.equals(this.examId, exam.examId) || !Objects.equals(this.examCode, exam.examCode) || !Objects.equals(this.examDate, exam.examDate)
				|| !Objects.equals(this.examName, exam.examName) || !Objects.equals(this.examClassRoom, exam.examClassRoom))
		    return false;
		return true;
	    }




}
