package com.fujitsu.ph.tsup.enrollment.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.management.model.CourseDeclineForm;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: EnrollmentController.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/25/2020 | WS) K.Freo | New Creation
//
//
//=======================================================


/**
* <pre>
* The implementation of Enrollment Controller
* <pre>
* @version 0.01
* @author K.Freo
*/

@Controller
@RequestMapping("/Enrollment")
public class EnrollmentController {
private static Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
	
	@GetMapping("/myschedules/{courseParticipantId}/decline")
	public String showCourseDeclineForm(Long id, Model model) {
		logger.debug("Model:{}", model);
		if (model.containsAttribute("courseDecline")) {
			return "enrollment-management/CourseDeclineForm";
		}
		
		
		return "enrollment-management/CourseDeclineForm";
	}
	
	@PostMapping("/myschedules/{courseParticipantId}/decline")
	public String submitCourseDeclineForm( Long id, CourseDeclineForm courseDeclineForm,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		logger.debug("courseDecline:{}", courseDeclineForm);
		logger.debug("Result:{}", result);

		model.addAttribute("courseDecline", courseDeclineForm);
		if (result.hasErrors()) {
			return "enrollment-management/CourseDeclineForm";
		}
		
		
		redirectAttributes.addFlashAttribute("courseDecline", courseDeclineForm);	
		return "redirect:/enrollment/CourseDeclineForm";
		}
}
