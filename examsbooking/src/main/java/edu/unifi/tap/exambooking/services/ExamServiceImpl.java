package edu.unifi.tap.exambooking.services;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.repository.ExamRepository;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;

@Service("examService")
public class ExamServiceImpl implements ExamService{

	final static Logger LOGGER = Logger.getLogger(ExamServiceImpl.class);
	
	@Autowired
	private ExamRepository examRepository;

	@Override
	public Exam findById(Long id) throws ExamsNotFoundException {
		LOGGER.info("findById method called");
		return examRepository.findOne(id);
	}

	@Override
	public List<Exam> findAll() throws ExamsNotFoundException {
		System.out.println("findAll method called");
		List<Exam> exams =  examRepository.findAll();
		if(exams.isEmpty())
			throw new ExamsNotFoundException();
		return exams;
	}

}
