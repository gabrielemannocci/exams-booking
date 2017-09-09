package edu.unifi.tap.exambooking.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "identification_number", nullable = false)
	private String idNumber;

    @ManyToOne(optional=false) 
    @JoinColumn(name="exam_id", nullable=false, updatable=false)
    private Exam exam;
	
	public Student() {
		super();
	}

	public Student(Long studentId, String firstName, String lastName, String email, String idNumber) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idNumber = idNumber;
	}


	public Long getStudentId() {
		return studentId;
	}


	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}


	 @Override
	    public int hashCode() {
		return Objects.hash(studentId,firstName,lastName,email,idNumber,exam);
	}



	 @Override
	    public boolean equals(Object obj) {
		if (obj == null) {
		    return false;
		}
		final Student student = (Student) obj;
		if (!Objects.equals(this.studentId, student.studentId))
		    return false;
		if (!Objects.equals(this.firstName, student.firstName))
		    return false;
		if (!Objects.equals(this.lastName, student.lastName))
		    return false;
		if (!Objects.equals(this.email, student.email))
		    return false;
		if (!Objects.equals(this.idNumber, student.idNumber))
		    return false;
		if (!Objects.equals(this.exam, student.exam))
		    return false;
		return true;
	    }





	
}
