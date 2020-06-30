package com.fujitsu.ph.tsup.enrollment.web;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.CourseDeclineForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrolledListForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrollmentForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.enrollment.service.EnrollmentService;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: EnrollmentController.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/25/2020 | WS) K.Freo      | New Creation
//0.01    | 06/29/2020 | WS) M.Lumontad  | Updated
//0.01    | 06/29/2020 | WS) M.Rivera    | Updated
//0.01    | 06/29/2020 | WS) G.Cabiling  | Updated
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

    private EnrollmentService enrollmentService;

    /**
     * <pre>
     * US02. As a member, I can view all course that I can enroll. URL Value = /schedules , method = GET 
     * 1. if bindingResult.hasErrors()
     *      1.1. Return the course schedule list form and view
     * 2. Set the following fields
     *      Argument         | Condition          | Set Value
     *  form.fromDateTime    | isBlank            | Date Today
     *  form.toDateTime      | isBlank            | Date Today + 5 days
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
    public String viewAllCourseSchedule(@Valid @ModelAttribute
            ("viewSchedule")CourseScheduleListForm courseScheduleListForm, BindingResult result, Model model) {  
        logger.debug("CourseScheduleListForm: {}", courseScheduleListForm);
        logger.debug("Result: {}", result);
        
        if(result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors());
            return "enrollment/courseScheduleListForm"; 
        }
        
        ZonedDateTime zoneDateTimeNow = ZonedDateTime.now();
        if(courseScheduleListForm.getFromDateTime() == null) {
            courseScheduleListForm.setFromDateTime(zoneDateTimeNow);
        }
        if(courseScheduleListForm.getToDateTime() == null) {
            courseScheduleListForm.setToDateTime(zoneDateTimeNow.plusDays(5));
        }
        long difference = courseScheduleListForm.getToDateTime().compareTo(courseScheduleListForm.getFromDateTime());
        
        if(difference > 0 ) {
            logger.debug("CourseScheduleListForm:{}",courseScheduleListForm);     
            model.addAttribute("CourseScheduleListForm", courseScheduleListForm);   
            return "enrollment/courseSCheduleListForm"; 
        }      
        
        Set<CourseSchedule> courseSchedules = enrollmentService.findAllScheduledCourses(
                courseScheduleListForm.getFromDateTime(), courseScheduleListForm.getToDateTime());
        Set<CourseScheduleForm> courseScheduleFormSet = courseScheduleListForm.getCourseSchedules();
        
        for (CourseSchedule courseSchedule : courseSchedules) {
            CourseScheduleForm courseScheduleForm = new CourseScheduleForm();

            courseScheduleForm.setCourseName(courseSchedule.getCourseName());
            courseScheduleForm.setInstructorName(
                    courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());

            Set<CourseScheduleDetail> courseScheduleDetailFormSets = courseSchedule.getCourseScheduleDetail();
            Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();

            for (CourseScheduleDetail courseScheduleDetail : courseScheduleDetailFormSets) {
                CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();

                courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
                courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
                courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
                courseScheduleDetailFormSet.add(courseSchedDetailForm);
            }
            courseScheduleForm.setCourseScheduleDetails(courseScheduleDetailFormSet);
            courseScheduleFormSet.add(courseScheduleForm);
        }
       return "view/courseScheduleListForm";
    }
    
    /**
     * 
     * @param courseEnrolledListForm
     * @param bindingResult
     * @param model
     * @return String
     * 
     * @author g.cabiling
     */
    
 

	@GetMapping("/mySchedules")
	public String viewAllEnrolledCourses(CourseEnrolledListForm courseEnrolledListForm, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "scheduling/myCourseSched";
		}

		ZonedDateTime fromDateTime = courseEnrolledListForm.getFromDateTime();
		ZonedDateTime toDateTime = courseEnrolledListForm.getToDateTime();

		if (fromDateTime == null || toDateTime == null) {

			courseEnrolledListForm.setFromDateTime(ZonedDateTime.now());
			courseEnrolledListForm.setToDateTime(ZonedDateTime.now().plusDays(5));

			if (courseEnrolledListForm.getToDateTime().isBefore(courseEnrolledListForm.getFromDateTime())) {

				model.addAttribute("myCourseSched", courseEnrolledListForm);
				model.addAttribute("error", "To Date should be greater than or equal to From Date");
				return "scheduling/myCourseSched";
			}
		}

		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Set<CourseParticipant> enrolledCourses = enrollmentService.findAllEnrolledCoursesByParticipantId(user.getId(),
				fromDateTime, toDateTime);

		Set<CourseEnrollmentForm> courseSchedules = courseEnrolledListForm.getCourseSchedules();
		for (CourseParticipant enrolledCourse : enrolledCourses) {

			CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();

			courseEnrollmentForm.setId(enrolledCourse.getId());
			courseEnrollmentForm.setCourseScheduleId(enrolledCourse.getCourseScheduleId());
			courseEnrollmentForm.setCourseName(enrolledCourse.getCourseName());
			courseEnrollmentForm.setInstructorName(enrolledCourse.getInstructorName());
			courseEnrollmentForm.setVenueName(enrolledCourse.getVenueName());
			courseEnrollmentForm.setRegistrationDate(enrolledCourse.getRegistrationDate());

			Set<CourseScheduleDetail> courseSchedDetSet = enrolledCourse.getCourseScheduleDetails();
			Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();
			
			for(CourseScheduleDetail courseScheduleDetail : courseSchedDetSet) {
				
				CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
				
				courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
				courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
				courseScheduleDetailForm.setDuration(courseScheduleDetail.getDuration());
				
				
				courseScheduleDetailFormSet.add(courseScheduleDetailForm);
			}
			
			courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailFormSet);

			courseSchedules.add(courseEnrollmentForm);
		}

		model.addAttribute("myCourseSched", courseEnrolledListForm);

		return "scheduling/myCourseSched";

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
	
	@PostMapping("/schedules/{courseScheduleId}/cancel")
	public String submitCourseEnrollmentCancelForm(Long id, Model model, RedirectAttributes redirectAttributes) {
		//call enrollmentService.cancel using the given id
		enrollmentService.cancel(id);
		redirectAttributes.addFlashAttribute("successMessage","Successfully Canceled the Course Schedule");
		return "redirect:/schedule";
	}
	
	
	@GetMapping("/schedules/{courseScheduleId}/enroll")
    public String showCourseEnrollmentForm(Long id, Model model) {
        logger.debug("Model:{}", model);
        if (model.containsAttribute("courseEnrollmentForm")) {
            return "enrollment-management/CourseEnrollmentForm";
        }

        CourseSchedule courseSchedule = enrollmentService.findCourseScheduleById(id);
        
        Set<CourseScheduleDetail> courseScheduleDetail =  courseSchedule.getCourseScheduleDetail();
        Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();
        
        for(CourseScheduleDetail newCourseScheduleDetail: courseScheduleDetail){

            CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
            
            courseScheduleDetailForm.setId(newCourseScheduleDetail.getCourseScheduleId());
            courseScheduleDetailForm.setScheduledStartDateTime(newCourseScheduleDetail.getScheduledStartDateTime());
            courseScheduleDetailForm.setScheduledEndDateTime(newCourseScheduleDetail.getScheduledEndDateTime());

            courseScheduleDetailFormSet.add(courseScheduleDetailForm);
        }
        
        
        CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();

        courseEnrollmentForm.setCourseScheduleId(courseSchedule.getId());
        courseEnrollmentForm.setCourseName(courseSchedule.getCourseName());
        courseEnrollmentForm.setInstructorName(courseSchedule.getInstructorLastName()+", " +courseSchedule.getInstructorFirstName());
        courseEnrollmentForm.setVenueName(courseSchedule.getVenueName());
        courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailFormSet); 
        courseEnrollmentForm.setRegistrationDate(ZonedDateTime.now());

        model.addAttribute("courseEnrollmentForm",courseEnrollmentForm);

        return "enrollment-management/courseEnrollmentForm";
    }

    @PostMapping("/schedules/{courseScheduleId}/enroll")
    public String submitCourseEnrollmentForm(@Valid @ModelAttribute("enroll") CourseEnrollmentForm courseEnrollmentForm,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        logger.debug("submitCourseEnrollmentForm:{}", courseEnrollmentForm);
        logger.debug("Result:{}", result);

        if (result.hasErrors()) {
            return "enrollment-management/courseEnrollmentForm";
        }

        model.addAttribute("submitCourseEnrollmentForm", courseEnrollmentForm);

        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CourseParticipant courseParticipant = 
            new CourseParticipant.Builder(courseEnrollmentForm.getCourseScheduleId(), 
            user.getId()).build();

        
        enrollmentService.enroll(courseParticipant);

        redirectAttributes.addFlashAttribute("submitCourseEnrollmentForm", courseEnrollmentForm);

        return "redirect:/enrollment";
    }

	
	
}
