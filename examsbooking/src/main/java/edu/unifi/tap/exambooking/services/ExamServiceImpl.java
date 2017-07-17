package edu.unifi.tap.exambooking.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;

@Service("examService")
public class ExamServiceImpl implements ExamService{

	final static Logger LOGGER = Logger.getLogger(ExamServiceImpl.class);
	
	
//	@Override
//	public List<Exam> findAll() throws ExamsNotFoundException {
//		List<Exam> exams = new ArrayList<Exam>();
//		if(exams.size()==0)
//			throw new ExamsNotFoundException("No exams found!");
//		return exams;
//	}
	
	@Override
	public List<Exam> findAll()  throws ExamsNotFoundException{
		System.out.println("+++ findAll +++");
		List<Exam> exams = new ArrayList<Exam>();
		
		Exam e1 = new Exam(0L,"TAP","Tecniche avanzate di programmazione",new Date(),"");
		Exam e2 = new Exam(1l,"DWH","Datawarehouse",new Date(),"");
		Exam e3 = new Exam(2L,"CSZ","Codici e sicurezza",new Date(),"");
		
		exams.add(e1);
		exams.add(e2);
		exams.add(e3);

		System.out.println("--- findAll ---");

		return exams;
	}

}
