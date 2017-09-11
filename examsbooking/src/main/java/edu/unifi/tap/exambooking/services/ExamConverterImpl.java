package edu.unifi.tap.exambooking.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import edu.unifi.tap.exambooking.controller.HomeController;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;


@Service("examConverter")
public class ExamConverterImpl implements Converter<String, Exam> {

	static final Logger LOGGER = Logger.getLogger(HomeController.class);

	@Autowired
	private ExamService examService;

	@Override
	public Exam convert(String arg0) {
		Long id = new Long(arg0);
		Exam exam = null;
		try {
			exam = examService.findById(id);
		} catch (ExamsNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		return exam;
	} 



}
