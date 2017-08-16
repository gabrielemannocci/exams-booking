package edu.unifi.tap.exambooking.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import edu.unifi.tap.exambooking.exception.ExamsNotFoundException;
import edu.unifi.tap.exambooking.exception.InvalidStudentException;
import edu.unifi.tap.exambooking.exception.StudentAlreadyRegisteredForExamException;
import edu.unifi.tap.exambooking.model.Exam;
import edu.unifi.tap.exambooking.model.Student;
import edu.unifi.tap.exambooking.services.interfaces.ExamService;
import edu.unifi.tap.exambooking.services.interfaces.StudentService;
import edu.unifi.tap.exambooking.util.ExamsbookingApplicationParams;

import org.springframework.validation.BindingResult;



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
	public ModelAndView registerUser(@ModelAttribute("student")  Student student,  @RequestParam("examParam") Long examId) throws InvalidStudentException, ExamsNotFoundException{
		System.out.println("+++ registerUser +++");
		System.out.println("EXAM_PARAM: "+examId);
		System.out.println("STUDENT_PARAM: "+student.toString());
		
		Exam examFound = examService.findById(examId);
		Student studentFound =  studentService.findStudentByIdNumber(student.getIdNumber());
		
		ModelAndView modelAndView = new ModelAndView();

		if(studentFound == null || studentService.checkForStudentRegistration(studentFound.getStudentId(),examId) ){
			studentService.registerStudent(studentFound,examFound);
			modelAndView.setViewName(ExamsbookingApplicationParams.EXAMSBOOKING_RESULT_VIEW);
			modelAndView.addObject("message", ExamsbookingApplicationParams.STUDENT_REGISTRATION_SUCCESS_MSG.
			replace("#x#", student.getLastName()).replace("#y#", examId.toString()));
			
		}
		else{
			
			String msg_error_str = ExamsbookingApplicationParams.STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG
					.replace("#x#", studentFound.getLastName())
					.replace("#y#", examFound.getExamId().toString());
			System.out.println(msg_error_str);
			modelAndView.setViewName(ExamsbookingApplicationParams.EXAMSBOOKING_ERROR_VIEW);
			modelAndView.addObject("errormessage",ExamsbookingApplicationParams.STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG
					.replace("#x#", studentFound.getLastName())
					.replace("#y#", examFound.getExamId().toString()));
		}
		System.out.println("--- registerUser ---");
		return modelAndView;
	}
	
	
//	@RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public ModelAndView registerUser(  @ModelAttribute("student")  Student student,  
//	@RequestParam("examParam") Long examId ) throws ExamsNotFoundException, InvalidStudentException{
//		ModelAndView modelAndView = new ModelAndView();
//		
//		Exam examFound = examService.findById(examId);
//		Student studentFound =  studentService.findStudentByIdNumber(student.getIdNumber());
//		if(studentService.checkForStudentRegistration(studentFound.getStudentId(),examId))
//			studentService.registerStudent(studentFound,examFound);
//		modelAndView.setViewName(ExamsbookingApplicationParams.
//		EXAMSBOOKING_RESULT_VIEW);
//		modelAndView.addObject("message", ExamsbookingApplicationParams.
//		STUDENT_REGISTRATION_SUCCESS_MSG.
//		replace("#x#", student.getLastName()).replace("#y#", examId.toString()));
//		return modelAndView;
//	}
	
	
//	 else{
//			System.out.println("FALSE");
//			modelAndView.setViewName(ExamsbookingApplicationParams.EXAMSBOOKING_ERROR_VIEW);
//			modelAndView.addObject("errormessage",ExamsbookingApplicationParams.STUDENT_REGISTRATION_ERROR_MSG);
//		}
	 
//	modelAndView.addObject("errormessage",ExamsbookingApplicationParams.STUDENT_ALREADY_REGISTERED_FOR_EXAM_ERROR_MSG.
//			replace("#x#", student.getLastName()).replace("#y#", examId.toString()));
	
//	@RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public ModelAndView registerUser( @Valid @ModelAttribute("student")  Student student,  @RequestParam("examParam") Long examId) throws StudentAlreadyRegisteredForExamException{
//		System.out.println("+++ registerUser +++");
//		System.out.println("STUD: "+student.getIdNumber());
//		System.out.println("EXAM: "+examId);
//		Student studentFound = studentService.findStudentRegistration(student.getIdNumber(),examId);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("message", "Student registered");
//		modelAndView.setViewName("results");
//		System.out.println("--- registerUser ---");
//		return modelAndView;
//	}
	
//	@RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public ModelAndView registerUser(  @ModelAttribute("student")  Student student, BindingResult bindingResult){
//		System.out.println("+++ registerUser +++");
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("message", e.getMessage());
//		modelAndView.setViewName("results");
//		System.out.println("--- registerUser ---");
//		return modelAndView;
//	}
}
