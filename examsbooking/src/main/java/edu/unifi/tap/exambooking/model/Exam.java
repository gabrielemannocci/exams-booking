package edu.unifi.tap.exambooking.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examClassRoom == null) ? 0 : examClassRoom.hashCode());
		result = prime * result + ((examCode == null) ? 0 : examCode.hashCode());
		result = prime * result + ((examDate == null) ? 0 : examDate.hashCode());
		result = prime * result + ((examId == null) ? 0 : examId.hashCode());
		result = prime * result + ((examName == null) ? 0 : examName.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Exam))
			return false;
		Exam other = (Exam) obj;
		if (examClassRoom == null) {
			if (other.examClassRoom != null)
				return false;
		} else if (!examClassRoom.equals(other.examClassRoom))
			return false;
		if (examCode == null) {
			if (other.examCode != null)
				return false;
		} else if (!examCode.equals(other.examCode))
			return false;
		if (examDate == null) {
			if (other.examDate != null)
				return false;
		} else if (!examDate.equals(other.examDate))
			return false;
		if (examId == null) {
			if (other.examId != null)
				return false;
		} else if (!examId.equals(other.examId))
			return false;
		if (examName == null) {
			if (other.examName != null)
				return false;
		} else if (!examName.equals(other.examName))
			return false;
		return true;
	}

}
