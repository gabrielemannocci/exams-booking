package edu.unifi.tap.exambooking.service.interfaces;

import java.util.List;
import edu.unifi.tap.exambooking.model.Exam;


public interface ExamService {
		
	public List<Exam> findAll();
}
