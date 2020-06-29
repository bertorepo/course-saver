package com.fujitsu.ph.tsup.employee.management.web;

//========================================================================================================================
//$id:PR03$
//Project Name :Training Sign Up
//System Name  :Cancel Course Schedule Controller
//Class Name   :CancelCourseScheduleController.java
//
//<<Modification History>>
//Version | Date       | Updated By      | Content
//--------+------------+-----------------+--------------------------------------------------------------------------------
//0.01    | 06/19/2020 | WS) T.Oviedo    | New Creation
//========================================================================================================================
/**
* <pre>
* JavaBean for CancelCourseScheduleController
* In this class, Instance 
* <pre>
* 
* @version 0.01
* @author t.oviedo
* 
*/
import com.fujitsu.ph.tsup.course.management.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.enrollment.service.EnrollmentService;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/enrollment")
public class CancelCourseScheduleController {
	private static Logger logger = LoggerFactory.getLogger(CancelCourseScheduleController.class);
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@GetMapping("")
	public String viewAllCourseSchedule(CourseScheduleListForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {

			model.addAttribute("errorMessage", result.getAllErrors());
			return "view/courseScheduleListForm"; 
		}
		
		ZonedDateTime zdtNow = ZonedDateTime.now();
		if(form.getFromDateTime() == null) {
			form.setFromDateTime(zdtNow);
		}
		if(form.getToDateTime() == null) {
			form.setToDateTime(zdtNow.plusDays(5));
		}
		long difference = form.getToDateTime().compareTo(form.getFromDateTime());
		
		if(difference > 0 ) {
			logger.debug("CourseScheduleListForm:{}",form);
			
			model.addAttribute("CourseScheduleListForm", form);	
			
			return "view/courseSCheduleListForm"; 
		}
		
		return "view/courseScheduleListForm";
	}
	
	@PostMapping("/schedules/{courseScheduleId}/cancel")
	public String submitCourseEnrollmentCancelForm(Long id, Model model, RedirectAttributes redirectAttributes) {
		//call enrollmentService.cancel using the given id
		enrollmentService.cancel(id);
		redirectAttributes.addFlashAttribute("successMessage","Successfully Canceled the Course Schedule");
		return "redirect:/schedule";
	}
	
}
