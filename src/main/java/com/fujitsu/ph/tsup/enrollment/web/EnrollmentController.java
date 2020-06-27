package com.fujitsu.ph.tsup.enrollment.web;

import java.time.ZonedDateTime;

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
import com.fujitsu.ph.tsup.domain.yu.CourseScheduleListForm;

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

    /**
     * <pre>
     * US02. As a member, I can view all course that I can enroll. URL Value = /schedules , method = GET 
     * 1. if bindingResult.hasErrors()
     *      1.1. Return the course schedule list form and view
     * 2. Set the following fields
     *      Argumet         | Condition          | Set Value
     *  form.fromDateTime   | isBlank            | Date Today
     *  form.toDateTime     | isBlank            | Date Today + 5 days
     * 3. if form.toDateTime < cform.fromDateTime
     *      3.1 Return the course schedule list form and view with a message "To Date should be 
     *          greater than or equal to From Date"
     * 4. Call enrollmentService.findAllScheduledCourses using the given form.fromDateTime, form.toDateTime
     *      4.1. Return the course schedule list form and view
     * <pre>
     * 
     * @param form
     * @param bindingResult
     * @param model
     * @return String
     * @author J.yu
     */
    @GetMapping("/viewCourseEnroll")
    public String viewAllCourseSchedule(CourseScheduleListForm form, BindingResult result, Model model) {
    
        if(result.hasErrors()) {
    
            model.addAttribute("errorMessage", result.getAllErrors());
            return "enrollment/courseScheduleListForm"; 
        }
        
        ZonedDateTime zoneDateTimeNow = ZonedDateTime.now();
        if(form.getFromDateTime() == null) {
            form.setFromDateTime(zoneDateTimeNow);
        }
        if(form.getToDateTime() == null) {
            form.setToDateTime(zoneDateTimeNow.plusDays(5));
        }
        long difference = form.getToDateTime().compareTo(form.getFromDateTime());
        
        if(difference > 0 ) {
            logger.debug("CourseScheduleListForm:{}",form);
            
            model.addAttribute("CourseScheduleListForm", form); 
            
            return "enrollment/courseSCheduleListForm"; 
        }      
        
       return "view/courseScheduleListForm";
}

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
