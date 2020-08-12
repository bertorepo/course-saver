package com.fujitsu.ph.tsup.enrollment.web;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
//import com.fujitsu.ph.tsup.enrollment.domain.Participant;
import com.fujitsu.ph.tsup.enrollment.model.CourseDeclineForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrolledListForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrollmentForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.enrollment.service.EnrollmentService;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//0.01    | 06/30/2020 | WS) K.Freo      | Updated
//0.01    | 06/30/2020 | WS) M.Lumontad  | Updated
//0.01    | 07/01/2020 | WS) G.Cabiling  | Updated
//0.01    | 07/01/2020 | WS) T.Oviedo    | Updated
//0.01    | 07/30/2020 | WS) M.Lumontad  | Updated
//0.01    | 08/05/2020 | WS) J.Yu        | Updated
//=======================================================
/**
 * <pre>
 * The implementation of Enrollment Controller
 * 
 * <pre>
 * 
 * @version 0.01
 * @author K.Freo
 */
@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
    private static Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    /**
     * <pre>
     * US02. As a member, I can view all course that I can enroll. URL Value =
     * /schedules , method = GET 1. if bindingResult.hasErrors() 1.1. Return the
     * course schedule list form and view 2. Set the following fields Argument |
     * Condition | Set Value form.fromDateTime | isBlank | Date Today
     * form.toDateTime | isBlank | Date Today + 5 days 3. if form.toDateTime <
     * cform.fromDateTime 3.1 Return the course schedule list form and view with a
     * message "To Date should be greater than or equal to From Date" 4. Call
     * enrollmentService.findAllScheduledCourses using the given form.fromDateTime,
     * form.toDateTime 4.1. Return the course schedule list form and view
     * 
     * <pre>
     * 
     * @param form
     * @param bindingResult
     * @param model
     * @return String 
     * @author J.yu
     */
    @GetMapping("/viewCourseEnroll")
    public String viewAllCourseSchedule(
    		@Valid @ModelAttribute("viewCourseEnroll") CourseScheduleListForm form, 
    		BindingResult result,Model model) {

        logger.debug("CourseScheduleListForm: {}", form);
        logger.debug("Result: {}", result);
        System.out.println("From Date Time: "+ form.getFromDateTime());
        System.out.println("To Date Time: "+ form.getToDateTime());
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors());
            return "enrollment/viewCourseEnroll";
        }
        if (form.getFromDateTime() == null) {
//        	form.setFromDateTime( ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-01 08:30:00").toInstant(),ZoneId.of("UTC")));
        	form.setFromDateTime(ZonedDateTime.now());
//        	System.out.println("SECOND FROM DATE TIME: "+form.getFromDateTime());
        }
        if (form.getToDateTime() == null) {
//        	form.setToDateTime( ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-10 08:30:00").toInstant(),ZoneId.of("UTC")));
        	form.setToDateTime(ZonedDateTime.now().plusDays(5));
//            System.out.println("SECOND TO DATE TIME: "+form.getToDateTime());
        }

        if(form.getFromDateTime().isAfter(form.getToDateTime())) {
        	  model.addAttribute(form);
              model.addAttribute("error", "To Date should be greater than or equal to From Date");
              model.addAttribute("nullMessage", "No schedules found");
              return "enrollment/viewCourseEnroll";
        }

        try {
        	Set<CourseSchedule> courseSchedules = enrollmentService.findAllScheduledCourses(
            		form.getFromDateTime(), form.getToDateTime());
        	
        Set<CourseScheduleForm> courseScheduleFormSet = new HashSet<CourseScheduleForm>();

        for (CourseSchedule courseSchedule : courseSchedules) {
            CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
            courseScheduleForm.setId(courseSchedule.getId());
            courseScheduleForm.setCourseName(courseSchedule.getCourseName());
            courseScheduleForm.setInstructorName(
                    courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());
            courseScheduleForm.setVenueName(courseSchedule.getVenueName());
            courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
            courseScheduleForm.setMaxAllowed(courseSchedule.getMaxAllowed());
            courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
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
            form.setCourseSchedules(courseScheduleFormSet);
        }
        
        model.addAttribute("viewCourseEnroll",form);
        logger.debug("courseScheduleListForm: {}", form);
        
        }catch(Exception e){
        	model.addAttribute("nullMessage", e.getMessage());
        }
        return "enrollment/viewCourseEnroll";
    } 

    /**
     * 
     * @param courseEnrolledListForm
     * @param bindingResult
     * @param model
     * @return String
     * 
     * @author m.lumontad
     */

    @GetMapping("/mySchedules")
    public String viewAllEnrolledCourses(CourseEnrolledListForm courseEnrolledListForm, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "enrollment/myCourseSched";
        }

        if (courseEnrolledListForm.getFromDateTime() == null || courseEnrolledListForm.getToDateTime() == null) {

            courseEnrolledListForm.setFromDateTime(ZonedDateTime.now());
            courseEnrolledListForm.setToDateTime(ZonedDateTime.now().plusDays(5));
        }

        if (courseEnrolledListForm.getToDateTime().isBefore(courseEnrolledListForm.getFromDateTime())) {

            model.addAttribute("myCourseSched", courseEnrolledListForm);
            model.addAttribute("errorMessage", "No Course Schedule Found");
            model.addAttribute("error", "Invalid Date Input");
            return "enrollment/myCourseSched";
        }

        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {

            Set<CourseParticipant> enrolledCourses = enrollmentService.findAllEnrolledCoursesByParticipantId(
                    user.getId(), courseEnrolledListForm.getFromDateTime(), courseEnrolledListForm.getToDateTime());

            Set<CourseEnrollmentForm> courseSchedules = new HashSet<CourseEnrollmentForm>();

            for (CourseParticipant enrolledCourse : enrolledCourses) {

                CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();

                courseEnrollmentForm.setId(enrolledCourse.getId());
                courseEnrollmentForm.setCourseScheduleId(enrolledCourse.getCourseScheduleId());
                courseEnrollmentForm.setCourseName(enrolledCourse.getCourseName());
                courseEnrollmentForm.setInstructorName(enrolledCourse.getInstructorName());
                courseEnrollmentForm.setVenueName(enrolledCourse.getVenueName());
                courseEnrollmentForm.setRegistrationDate(enrolledCourse.getRegistrationDate());

                Set<CourseScheduleDetail> courseSchedDetSet = enrolledCourse.getCourseScheduleDetail();
                Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();

                for (CourseScheduleDetail courseScheduleDetail : courseSchedDetSet) {

                    CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();

                    courseScheduleDetailForm
                            .setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
                    courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
                    courseScheduleDetailForm.setDuration(courseScheduleDetail.getDuration());

                    courseScheduleDetailFormSet.add(courseScheduleDetailForm);
                }

                courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailFormSet);
                courseSchedules.add(courseEnrollmentForm);
                courseEnrolledListForm.setCourseScheduleDetailForm(courseSchedules);

            }
        } catch (Exception e) {

            model.addAttribute("errorMessage", e.getMessage());

        }
        
        model.addAttribute("myCourseSched", courseEnrolledListForm);

        return "enrollment/myCourseSched";
    }

    /**
     * Method for showCourseDeclineForm
     * 
     * A member can decline course with reason.URL Value =
     * /myschedules/{courseParticipantId}/decline, method = GET Call
     * enrollmentService.findCourseParticipantById using the given id Set the values
     * from the previous step into the CourseDeclineForm Return the Course decline
     * form and view
     */
    @GetMapping("/myschedules/{courseParticipantId}/decline")
    public String showCourseDeclineForm(@PathVariable("courseParticipantId") Long id, Model model) {
        logger.debug("Model:{}", model);

        CourseDeclineForm courseDeclineForm = new CourseDeclineForm();

        CourseParticipant courseParticipant = enrollmentService.findCourseParticipantById(id);

        courseDeclineForm.setId(courseParticipant.getId());
        courseDeclineForm.setCourseName(courseParticipant.getCourseName());
        courseDeclineForm.setInstructorName(courseParticipant.getInstructorName());
        courseDeclineForm.setVenueName(courseParticipant.getVenueName());
        courseDeclineForm.setParticipantName(courseParticipant.getParticipantName());
        courseDeclineForm.setRegistrationDate(courseParticipant.getRegistrationDate());
        courseDeclineForm.setReason(courseParticipant.getReason());

        model.addAttribute("courseDecline", courseDeclineForm);
        return "enrollment/myCourseSched";
    }

    /**
     * Method for submitCourseDeclineForm
     * 
     * A member can decline course with reason URL Value =
     * /myschedules/{courseParticipantId}/decline, method = DELETE Transform the
     * form to courseParticipant. Call enrollmentService.declineCourse using the
     * courseParticipant Return the Course decline form and view. Return also a
     * success message.
     */
    @DeleteMapping("/myschedules/{courseParticipantId}/decline")
    public String submitCourseDeclineForm(@Valid @ModelAttribute("courseDecline") CourseDeclineForm form,
            BindingResult bindingresult, Model model, RedirectAttributes redirectAttributes) {
    	
        logger.debug("courseDeclineForm:{}", form);
        logger.debug("BindingResult:{}", bindingresult);

        if (bindingresult.hasErrors()) {
        	model.addAttribute("courseDecline", form);
            return "/enrollment/myCourseSched";
        }

 CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        
        courseScheduleDetailForm.setId(courseScheduleDetailForm.getId());
        courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetailForm.getScheduledStartDateTime());
        courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetailForm.getScheduledEndDateTime());
        
        
        Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = form.getCourseScheduleDetailsForm();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        
        for(CourseScheduleDetailForm courseSchedsDetail : courseScheduleDetailFormSet) {
        CourseScheduleDetail courseScheduleDetail= new CourseScheduleDetail.Builder(courseSchedsDetail.getId(),
                courseSchedsDetail.getScheduledStartDateTime(), courseSchedsDetail.getScheduledEndDateTime())
                            .build();
        courseScheduleDetailSet.add(courseScheduleDetail);  
        }

        CourseParticipant courseParticipant = new CourseParticipant.Builder(form.getId()).build();

        enrollmentService.declineCourse(courseParticipant);

        CourseDeclineForm courseDeclineForm = new CourseDeclineForm();

        courseDeclineForm.setId(courseParticipant.getId());
        courseDeclineForm.setCourseName(courseParticipant.getCourseName());
        courseDeclineForm.setInstructorName(courseParticipant.getInstructorName());
        courseDeclineForm.setVenueName(courseParticipant.getVenueName());
        courseDeclineForm.setParticipantName(courseParticipant.getParticipantName());
        courseDeclineForm.setRegistrationDate(courseParticipant.getRegistrationDate());
        courseDeclineForm.setReason(courseParticipant.getReason());
        
        redirectAttributes.addFlashAttribute("courseDecline", courseParticipant);
        return "/enrollment/myCourseSched";
    }

    
    
    @GetMapping("/schedules/{courseScheduleId}/enroll")
    public String showCourseEnrollmentForm(@PathVariable("courseScheduleId")Long id, Model model, RedirectAttributes redirectAttributes) {
        logger.debug("Model:{}", model);
        if (model.containsAttribute("courseEnrollmentForm")) {
            return "enrollment/viewCourseEnroll";
        }

        CourseSchedule courseSchedule = enrollmentService.findCourseScheduleById(id);

        Set<CourseScheduleDetail> courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();

        for (CourseScheduleDetail newCourseScheduleDetail : courseScheduleDetail) {

            CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();

            courseScheduleDetailForm.setId(newCourseScheduleDetail.getCourseScheduleId());
            courseScheduleDetailForm.setScheduledStartDateTime(newCourseScheduleDetail.getScheduledStartDateTime());
            courseScheduleDetailForm.setScheduledEndDateTime(newCourseScheduleDetail.getScheduledEndDateTime());

            courseScheduleDetailFormSet.add(courseScheduleDetailForm);
        }

        CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();

        courseEnrollmentForm.setCourseScheduleId(courseSchedule.getId());
        courseEnrollmentForm.setCourseName(courseSchedule.getCourseName());
        courseEnrollmentForm.setInstructorName(
                courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());
        courseEnrollmentForm.setVenueName(courseSchedule.getVenueName());
        courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailFormSet);
        courseEnrollmentForm.setRegistrationDate(ZonedDateTime.now());

        model.addAttribute("courseEnrollmentForm", courseEnrollmentForm);

        logger.debug("courseEnrollmentForm: {}", courseEnrollmentForm);
        return "redirect:/enrollment/viewCourseEnroll";
    }

    @PostMapping("/schedules/{courseScheduleId}/enroll")
    public String submitCourseEnrollmentForm(@Valid @ModelAttribute("enroll") CourseEnrollmentForm courseEnrollmentForm,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        logger.debug("submitCourseEnrollmentForm:{}", courseEnrollmentForm);
        logger.debug("Result:{}", result);
        
        if (result.hasErrors()) {
        	redirectAttributes.addFlashAttribute("errorMsg", result.getAllErrors());
        	return "redirect:/enrollment/viewCourseEnroll";
        }

        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CourseParticipant courseParticipant = new CourseParticipant.Builder(courseEnrollmentForm.getCourseScheduleId(),
                user.getId(), ZonedDateTime.now()).build();
        try {
        	  enrollmentService.enroll(courseParticipant);

              redirectAttributes.addFlashAttribute("successMsg", "Successfully Enrolled a Course!!!");
              redirectAttributes.addFlashAttribute("courseEnrollmentForm", courseEnrollmentForm);
        }catch(Exception e) {
        	redirectAttributes.addFlashAttribute("duplicateMessage", e.getMessage());
        }

        return "redirect:/enrollment/viewCourseEnroll";
    }
    
    /**
     * Cancel Course Schedule
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/schedules/{courseScheduleId}/cancel")
    public String submitCourseEnrollmentCancelForm(@RequestParam Long id, Model model, 
            RedirectAttributes redirectAttributes) {
        enrollmentService.cancel(id);
        redirectAttributes.addFlashAttribute("successMessage","Successfully Canceled the Course Schedule");
//      return "redirect:/schedule";
        return "redirect:/enrollment/viewCourseEnroll";
    }
    
    /**
     * View Members Course
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/viewMemberCourse")
    public String viewAllMemberCourse(
            @Valid @ModelAttribute("viewCourseEnroll") CourseScheduleListForm form, BindingResult result,
            Model model) {
        logger.debug("CourseScheduleListForm: {}", form);
        logger.debug("Result: {}", result);

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors());
            return "enrollment/viewMemberCourse";
        }

        if (form.getFromDateTime() == null) {
            form.setFromDateTime(ZonedDateTime.now().minusMonths(1));
        }
        
        if (form.getToDateTime() == null) {
            form.setToDateTime(ZonedDateTime.now().plusDays(5));
        }
        
        Set<CourseSchedule> courseSchedules = enrollmentService.findAllScheduledCourses(
                form.getFromDateTime(), form.getToDateTime());
        
        
        model.addAttribute("courseSchedules",courseSchedules);
//        model.addAttribute("participantForm", new Participant());
        
        System.out.println(courseSchedules);
        return "enrollment/viewMemberCourse";	
    }
    
//    @RequestMapping(value = "/viewEnrolled", method = RequestMethod.GET)
//	public @ResponseBody List<Participant> viewMemberEnrolled (@RequestParam Long id) {
//    	return enrollmentService.findEnrolledMembersById(id);
//    }
    
//    @RequestMapping(value = "/addEnrolled", method = RequestMethod.POST)
//	public String addMemberEnrolled (@RequestBody @Valid @ModelAttribute("participantForm") Participant participant, 
//			RedirectAttributes redirectAttributes, BindingResult result) {
//    	
//    	if (result.hasErrors()) {
//    		List<FieldError> err=result.getFieldErrors();
//    		
//    		
//    		for(FieldError e:err){
//                System.out.println("Error on object ---> "+e.getObjectName()+" on field ---> "+e.getField()+". Message ---> "+e.getDefaultMessage());
//           }
//    		
//    		return "redirect:/enrollment/viewMemberCourse";
//    	}
//    	
//    	enrollmentService.addEnrolledMembersById(participant);
//    	
//    	redirectAttributes.addFlashAttribute("success","{employee} has been added");
//    	return "redirect:/enrollment/viewMemberCourse";
//    }
//}

//	public String submitCourseEnrollmentCancelForm(@RequestParam("courseScheduleId") Long id, Model model, 
//			RedirectAttributes redirectAttributes) {
//		enrollmentService.cancel(id);
//		redirectAttributes.addFlashAttribute("successMessage","Successfully Canceled the Course Schedule");
////		return "redirect:/schedule";
//		return "redirect:/enrollment/viewCourseEnroll";
//	}
}
