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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.employee.management.model.CancelCourseScheduleForm;

@RestController
@RequestMapping("/cancelcourseschedule")
public class CancelCourseScheduleController {
	private static Logger logger = LoggerFactory.getLogger(CancelCourseScheduleController.class);

	@GetMapping("/schedules")
	public String viewAllCourseSchedule(CancelCourseScheduleForm cancelCourseSchedule, BindingResult result, Model model) {
		CancelCourseScheduleForm ccs = new CancelCourseScheduleForm();
		
		if(result.hasErrors()) {
			return "view/courseSCheduleListForm"; 
		}
		
//		if() {
//			 
//		}
		
		return "view/courseSCheduleListForm";
	}
	
	@PostMapping("/schedules/{courseScheduleId}/cancel")
	public String submitCourseEnrollmentCancelForm(Long id, Model model, RedirectAttributes redirectAttributes) {
		//call enrollmentService.cancel using the given id
		return "redirect:/schedule";
	}
	
}
