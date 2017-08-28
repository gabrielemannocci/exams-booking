package edu.unifi.tap.exambooking.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;
import edu.unifi.tap.exambooking.util.ExamsbookingApplicationParams;


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


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView returnHome(@ModelAttribute("student")  Student student,@ModelAttribute("exam")  Exam exam){
		LOGGER.debug("+++ ENTER returnIndex +++");
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
		LOGGER.debug("--- EXIT returnIndex ---");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("student")  Student student,  @RequestParam("examParam") Long examId) throws InvalidStudentException, ExamsNotFoundException{
		LOGGER.debug("+++ registerUser +++");
		
		Exam examFound = examService.findById(examId);
		
		ModelAndView modelAndView = new ModelAndView();
		Student studentFound = studentService.findStudentByIdNumberAndExam(student.getIdNumber(),examId);
		if(studentFound == null){
			studentService.registerStudent(student,examFound);
			modelAndView.setViewName(ExamsbookingApplicationParams.EXAMSBOOKING_RESULT_VIEW);
			modelAndView.addObject("message", ExamsbookingApplicationParams.STUDENT_REGISTRATION_SUCCESS_MSG.
			replace("#x#", student.getLastName()).replace("#y#", examFound.getExamName()));
		}
		else{
			
			String msg_error_str = ExamsbookingApplicationParams.STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG
					.replace("#x#", studentFound.getLastName())
					.replace("#y#", examFound.getExamName());
			System.out.println(msg_error_str);
			modelAndView.setViewName(ExamsbookingApplicationParams.EXAMSBOOKING_ERROR_VIEW);
			modelAndView.addObject("errormessage",ExamsbookingApplicationParams.STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG
					.replace("#x#", studentFound.getLastName())
					.replace("#y#", examFound.getExamName()));
		}
		LOGGER.debug("--- registerUser ---");
		return modelAndView;
	}
	
	
}
