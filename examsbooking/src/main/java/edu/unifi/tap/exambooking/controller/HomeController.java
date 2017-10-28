package edu.unifi.tap.exambooking.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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


@Controller
public class HomeController {

	static final Logger LOGGER = Logger.getLogger(HomeController.class);

	private static final String EXAMSBOOKING_HOME_VIEW = "index";
	private static final String EXAMSBOOKING_RESULT_VIEW = "results";
	private static final String EXAMSBOOKING_ERROR_VIEW = "error";
	
	private static final String MODELVIEW_RESULT = "message";
	private static final String MODELVIEW_ERROR = "errormessage";
	
	private static final String STUDENT_REGISTRATION_SUCCESS_MSG = "Student #x# correctly registered for exam #y# !";
	private static final String STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG = "Student #x# already registered for exam #y# !";
	
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
		try {
			exams = examService.findAll(); 
		} catch (ExamsNotFoundException e) {
			LOGGER.debug(e.getMessage());
			modelAndView.addObject(MODELVIEW_ERROR, e.getMessage());
			modelAndView.setViewName(EXAMSBOOKING_ERROR_VIEW);
			return modelAndView;
		}
		modelAndView.setViewName(EXAMSBOOKING_HOME_VIEW);
		modelAndView.addObject("exams", exams);
		LOGGER.debug("--- EXIT returnIndex ---");
		return modelAndView;
	}

	@ExceptionHandler(InvalidStudentException.class)
	public ModelAndView caughtInvalidStudentException(Exception exception) {
		LOGGER.debug("----Caught InvalidStudentException----");
		ModelAndView mav = new ModelAndView();
		mav.addObject(MODELVIEW_ERROR, exception.getMessage());
		mav.setViewName(EXAMSBOOKING_ERROR_VIEW);
		return mav;
	}

	@ExceptionHandler(ExamsNotFoundException.class)
	public ModelAndView caughtExamsNotFoundException(Exception exception) {
		LOGGER.debug("----Caught ExamsNotFoundException----");
		ModelAndView mav = new ModelAndView();
		mav.addObject(MODELVIEW_ERROR, exception.getMessage());
		mav.setViewName(EXAMSBOOKING_ERROR_VIEW);
		return mav;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("student")  Student student,  @RequestParam("examParam") Long examId) throws InvalidStudentException, ExamsNotFoundException{
		LOGGER.debug("+++ registerUser +++"); 
		Exam examFound = examService.findById(examId);

		ModelAndView modelAndView = new ModelAndView();
		Student studentFound = studentService.findStudentByIdNumberAndExam(student.getIdNumber(),examId);
		if(studentFound == null){
			Student saved = studentService.registerStudent(student,examFound);
			modelAndView.setViewName(EXAMSBOOKING_RESULT_VIEW);
			modelAndView.addObject(MODELVIEW_RESULT,STUDENT_REGISTRATION_SUCCESS_MSG.
					replace("#x#", saved.getLastName()).replace("#y#", examFound.getExamName()));
		}
		else{
			modelAndView.setViewName(EXAMSBOOKING_ERROR_VIEW);
			modelAndView.addObject(MODELVIEW_ERROR,STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG
					.replace("#x#", studentFound.getLastName())
					.replace("#y#", examFound.getExamName()));
		}
		LOGGER.debug("--- registerUser ---");
		return modelAndView;
	}


}
