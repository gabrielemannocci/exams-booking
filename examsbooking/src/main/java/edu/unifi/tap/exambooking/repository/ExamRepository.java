package edu.unifi.tap.exambooking.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.unifi.tap.exambooking.model.Exam;


@Repository("examRepository")
public interface ExamRepository extends CrudRepository<Exam, Long> {
 
	public List<Exam> findAll();
}
