package edu.unifi.tap.exambooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.unifi.tap.exambooking.model.Student;

@Repository("studentRepository")
public interface StudentRepository extends CrudRepository<Student, Long> {
	Student findStudentByIdNumber(String idNumber);

}
