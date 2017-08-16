package edu.unifi.tap.exambooking.services.interfaces;

import java.util.List;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;


public interface ExamService {
		
	public List<Exam> findAll()  throws ExamsNotFoundException;
	public Exam findById(Long id)  throws ExamsNotFoundException;
}
