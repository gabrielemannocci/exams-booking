package edu.unifi.tap.exambooking.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.unifi.tap.exambooking.model.Student;

@Repository("studentRepository")
public interface StudentRepository extends CrudRepository<Student, Long> {
	
	@Query("select s from Student s where s.idNumber = :idNumber and s.exam.id = :examId")
	Student findStudentByIdNumberAndExam(@Param("idNumber") String idNumber, @Param("examId") Long examId);
}
