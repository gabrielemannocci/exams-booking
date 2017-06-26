package edu.unifi.tap.examsbooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.service.interfaces.ExamService;


@Controller
public class HomeController {

	final static Logger LOGGER = Logger.getLogger(HomeController.class);

	private ExamService examService;

	@Autowired
	public HomeController(ExamService examService) {
		this.examService = examService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView returnHome(){
		ModelAndView modelAndView = new ModelAndView("index");
		List<Exam> exams  = examService.findAll();
		modelAndView.addObject("exams", exams);
		return modelAndView;
	}
	
}
