package edu.unifi.tap.exambooking.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.exception.StudentAlreadyRegisteredForExamException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;



@Controller
public class HomeController {

	final static Logger LOGGER = Logger.getLogger(HomeController.class);

	private ExamService examService;

	private StudentService studentService;
	
	@Autowired
	public HomeController(ExamService examService,StudentService studentService) {
		this.examService = examService;
		this.studentService = studentService;
	}

//	@ModelAttribute("student")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView returnHome( ){
		System.out.println("+++ ENTER returnIndex +++");
		ModelAndView modelAndView = new ModelAndView();
		List<Exam> exams = null;
		modelAndView.setViewName("index");
		try {
			exams = examService.findAll();
		} catch (ExamsNotFoundException e) {
			System.out.println(e.getMessage());
			modelAndView.addObject("errormessage", e.getMessage());
			modelAndView.setViewName("error");
		}
		modelAndView.addObject("exams", exams);
		System.out.println("--- EXIT returnIndex ---");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUser(  @ModelAttribute("student")  Student student ) throws StudentAlreadyRegisteredForExamException{
		System.out.println("+++ registerUser +++");
		System.out.println("STUD: "+student.toString());
		
		Student studentFound = studentService.findByIdNumber(student.getIdNumber(),student.getExamId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("results");
		System.out.println("--- registerUser ---");
		return modelAndView;
	}
	
//	@RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public ModelAndView registerUser(  @ModelAttribute("student")  Student student ){
//		System.out.println("+++ registerUser +++");
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("message", e.getMessage());
//		modelAndView.setViewName("results");
//		System.out.println("--- registerUser ---");
//		return modelAndView;
//	}
}
