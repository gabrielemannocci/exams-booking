package edu.unifi.tap.exambooking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;


@Component
public class ExamConverter implements Converter<String, Exam> {

@Autowired
private ExamService examService;

    @Override
    public Exam convert(String arg0) {
        Long id = new Long(arg0);
        try {
			return examService.findById(id);
		} catch (ExamsNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    } 

}
