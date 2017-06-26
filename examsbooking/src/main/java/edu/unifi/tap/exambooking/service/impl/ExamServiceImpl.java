package edu.unifi.tap.exambooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.service.interfaces.ExamService;

@Service("examService")
public class ExamServiceImpl implements ExamService{

	final static Logger LOGGER = Logger.getLogger(ExamServiceImpl.class);
	
	@Override
	public List<Exam> findAll() {

		List<Exam> exams = new ArrayList<Exam>();
		
		Exam e1 = new Exam(0L,"TAP","Tecniche avanzate di programmazione");
		Exam e2 = new Exam(1l,"DWH","Datawarehouse");
		Exam e3 = new Exam(2L,"CSZ","Codici e sicurezza");
		
		exams.add(e1);
		exams.add(e2);
		exams.add(e3);
		
		return exams;
	}

}
