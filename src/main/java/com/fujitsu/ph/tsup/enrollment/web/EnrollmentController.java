package com.fujitsu.ph.tsup.enrollment.web;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
//import com.fujitsu.ph.tsup.enrollment.domain.Participant;
import com.fujitsu.ph.tsup.enrollment.model.CourseDeclineForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrollCancelForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrolledListForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseEnrollmentForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleForm;
import com.fujitsu.ph.tsup.enrollment.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
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
//0.02    | 09/15/2020 | WS) J.Yu        | Updated
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
    private CourseEnrolledListForm enrolledListForm = new CourseEnrolledListForm();

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

        if(form.getFromDateTime().isAfter(form.getToDateTime()) || form.getFromDateTime().isEqual(form.getToDateTime())) {
        	  model.addAttribute(form);
              model.addAttribute("error", "To Date should be greater than From Date");
              model.addAttribute("nullMessage", "No schedules found");
              
              return "enrollment/viewCourseEnroll";
        }
        
        List<TopLearnerForm> listTopLearnerByMonth = enrollmentService.findTopLearner(ZonedDateTime.now(), ZonedDateTime.now().plusMonths(1));
        
        List<TopLearnerForm> listTopLearnerByQuarter = enrollmentService.findTopLearner(ZonedDateTime.now(), ZonedDateTime.now().plusMonths(4));
        
        Set<CourseSchedule> courseScheduleAllActive = enrollmentService.findAllActiveCourseSchedule();
        System.out.println(courseScheduleAllActive.size() + "SIZE OF COURSE ACTIVE");
        Set<CourseScheduleForm> courseScheduleSetForm = new HashSet<>();
        for(CourseSchedule courseSchedule:courseScheduleAllActive) {
        	
        	CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
        	
        	courseScheduleForm.setId(courseSchedule.getId());
        	courseScheduleForm.setCourseId(courseSchedule.getCourseId());
        	courseScheduleForm.setCourseName(courseSchedule.getCourseName());
        	courseScheduleForm.setInstructorId(courseSchedule.getInstructorId());
        	courseScheduleForm.setInstructorName(courseSchedule.getInstructorLastName()+" "+courseSchedule.getInstructorFirstName());
        	courseScheduleForm.setVenueId(courseSchedule.getVenueId());
        	courseScheduleForm.setVenueName(courseSchedule.getVenueName());
        	courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
        	courseScheduleForm.setMaxAllowed(courseSchedule.getMaxAllowed());
        	courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
        	courseScheduleForm.setCourseDetails(courseSchedule.getCourseDetails());

        	CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        	CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        	
        	courseScheduleDetailForm.setId(courseScheduleDetail.getId());
        	courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
        	courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
        	courseScheduleDetailForm.setDuration(courseScheduleDetail.getDuration());
        	
        	courseScheduleForm.setCourseScheduleDetails(courseScheduleDetailForm);
        	courseScheduleSetForm.add(courseScheduleForm);
        	form.setTopLearnerByMonth(listTopLearnerByMonth);
            form.setTopLearnerByQuarter(listTopLearnerByQuarter);
//        	System.out.println("(COURSE ACTIVE)Course Id: " + courseScheduleForm.getCourseId());
//        	System.out.println("(COURSE ACTIVE)Course Name: " + courseScheduleForm.getCourseName());
//        	System.out.println("(COURSE ACTIVE)Instructor Name: " + courseScheduleForm.getInstructorName());
//        	System.out.println("(COURSE ACTIVE)Course Schedule Detail Id: " + courseScheduleDetailForm.getId());
//        	System.out.println("(COURSE ACTIVE)Start DateTime: " + courseScheduleDetailForm.getScheduledStartDateTime());
//        	System.out.println("(COURSE ACTIVE)End DateTime: " + courseScheduleDetailForm.getScheduledEndDateTime());
//        	System.out.println("(COURSE ACTIVE)Duration: " + courseScheduleDetailForm.getDuration());
//        	System.out.println("(COURSE ACTIVE)Venue Name: " + courseScheduleForm.getVenueName());
//        	System.out.println("(COURSE ACTIVE)Min Participants: " + courseScheduleForm.getMinRequired());
//        	System.out.println("(COURSE ACTIVE)Max Participants: " + courseScheduleForm.getMaxAllowed());
//        	System.out.println("(COURSE ACTIVE)Total Participants: " + courseScheduleForm.getTotalParticipants());
        	
        }
        model.addAttribute("activeCourseSchedule", courseScheduleSetForm);

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
	            courseScheduleForm.setCourseDetails(courseSchedule.getCourseDetails());
	            
	            CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
	            CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();

	            courseSchedDetailForm.setId(courseScheduleDetail.getId());
	            courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
	            courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
	            courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
	            
	            courseScheduleForm.setCourseScheduleDetails(courseSchedDetailForm);
	            courseScheduleFormSet.add(courseScheduleForm);
	            form.setCourseSchedules(courseScheduleFormSet);
	            
	            
	            for(TopLearnerForm top : listTopLearnerByQuarter) {

	                System.out.println("PartName" + top.getParticipantName());}
	        }
        }catch(Exception e) {
        	model.addAttribute("nullMessage", e.getMessage());
        }
        
        
        model.addAttribute("viewCourseEnroll",form);
        logger.debug("courseScheduleListForm: {}", form);
        
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
            model.addAttribute("error", "To Date should be greater than or equal to From Date");
            return "enrollment/myCourseSched";
        }

        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {

            Set<CourseParticipant> enrolledCourses = enrollmentService.findAllEnrolledCoursesByParticipantId(
                    user.getId(), courseEnrolledListForm.getFromDateTime(), courseEnrolledListForm.getToDateTime());

            List<CourseEnrollmentForm> courseSchedules = new ArrayList<CourseEnrollmentForm>();

            for (CourseParticipant enrolledCourse : enrolledCourses) {

                CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();

                courseEnrollmentForm.setId(enrolledCourse.getId());
                courseEnrollmentForm.setCourseId(enrolledCourse.getCourseId());
                courseEnrollmentForm.setCourseScheduleId(enrolledCourse.getCourseScheduleId());
                courseEnrollmentForm.setCourseName(enrolledCourse.getCourseName());
                courseEnrollmentForm.setInstructorName(enrolledCourse.getInstructorName());
                courseEnrollmentForm.setVenueName(enrolledCourse.getVenueName());
                courseEnrollmentForm.setRegistrationDate(enrolledCourse.getRegistrationDate());
                courseEnrollmentForm.setParticipantId(enrolledCourse.getParticipantId());
                courseEnrollmentForm.setAttendanceStatus(enrolledCourse.getAttendanceStatus());
                courseEnrollmentForm.setCourseDetails(enrolledCourse.getCourseDetails());

                CourseScheduleDetail courseSchedDet = enrolledCourse.getCourseScheduleDetail();
                
                CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
                courseScheduleDetailForm.setScheduledStartDateTime(courseSchedDet.getScheduledStartDateTime());
                courseScheduleDetailForm.setScheduledEndDateTime(courseSchedDet.getScheduledEndDateTime());
                courseScheduleDetailForm.setDuration(courseSchedDet.getDuration());
                
                
                courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailForm);
                courseSchedules.add(courseEnrollmentForm);
                courseEnrolledListForm.setCourseScheduleDetailForm(courseSchedules);
            }
            
            
            List<CourseEnrollmentForm> setSortedCourseScheduleForm = courseSchedules.stream().collect(Collectors.toCollection(ArrayList::new));
            List<CourseEnrollmentForm> sortedCourseScheduleForm = setSortedCourseScheduleForm.stream().sorted((e1, e2) ->
            e1.getCourseName().compareTo(e2.getCourseName())).collect(Collectors.toList());
            
            courseEnrolledListForm.setCourseScheduleDetailForm(sortedCourseScheduleForm);
            
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
    @DeleteMapping("/mySchedules/decline")
    public String submitCourseDeclineForm(@Valid @ModelAttribute("courseDecline") CourseDeclineForm courseDeclineForm,
                Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes ) {
        
        System.out.println("DELETE MAPPING");
        logger.debug("courseDeclineForm:{}", courseDeclineForm);
        logger.debug("BindingResult:{}", bindingResult);


        if (bindingResult.hasErrors()) {
            model.addAttribute("courseDecline", courseDeclineForm);
            return "enrollment/myCourseSched";
        }
     
        try {
        
        CourseEnrolledListForm listForm = new CourseEnrolledListForm();
        listForm.setFromDateTime(enrolledListForm.getFromDateTime());
        listForm.setToDateTime(enrolledListForm.getToDateTime());
        
        
        System.out.println("ID: " + courseDeclineForm.getId());
        System.out.println("courseName: " + courseDeclineForm.getCourseName());
        System.out.println("courseId: " + courseDeclineForm.getCourseId());
        System.out.println("courseScheduleId: " + courseDeclineForm.getCourseScheduleId());
        System.out.println("instructorName: " + courseDeclineForm.getInstructorName());
        System.out.println("venueName: " + courseDeclineForm.getVenueName());
        System.out.println("registrationDate: " + courseDeclineForm.getRegistrationDate());
        System.out.println("Reason: " + courseDeclineForm.getReason());

        CourseParticipant courseParticipant = new CourseParticipant.Builder(courseDeclineForm.getId(), 
                courseDeclineForm.getCourseId(),
                courseDeclineForm.getCourseScheduleId(),
                courseDeclineForm.getCourseName(),
                courseDeclineForm.getInstructorName(),
                courseDeclineForm.getVenueName(),
                courseDeclineForm.getId(), 
                courseDeclineForm.getParticipantName(),
                courseDeclineForm.getRegistrationDate())
              
                .decline(courseDeclineForm.getReason()).build();

        enrollmentService.declineCourse(courseParticipant);

        redirectAttributes.addFlashAttribute("myCourseSched", listForm);
        
        /*
         * Success Message
         * an attribute to be passed to Thymeleaf to show Success Message
         */
        redirectAttributes.addFlashAttribute("courseDeclineSuccess", "You have declined the course successfully!");
        return "redirect:/enrollment/mySchedules";
        
        
        } catch (Exception e) {
            
            e.printStackTrace();
            
            /*
             * Error Message
             * an attribute to be passed to Thymeleaf to show Error Message
             */
            redirectAttributes.addFlashAttribute("errorDeclineMessage", e.getMessage());
        
    }
        return "redirect:/enrollment/mySchedules";
    }

    
    
    @GetMapping("/schedules/{courseScheduleId}/enroll")
    public String showCourseEnrollmentForm(@PathVariable("courseScheduleId")Long id, Model model, RedirectAttributes redirectAttributes) {
        logger.debug("Model:{}", model);
        if (model.containsAttribute("courseEnrollmentForm")) {
            return "enrollment/viewCourseEnroll";	
        }

        CourseSchedule courseSchedule = enrollmentService.findCourseScheduleById(id);
        CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        
        courseScheduleDetailForm.setId(courseScheduleDetail.getCourseScheduleId());
        courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
        courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());

        CourseEnrollmentForm courseEnrollmentForm = new CourseEnrollmentForm();

        courseEnrollmentForm.setCourseScheduleId(courseSchedule.getId());
        courseEnrollmentForm.setCourseName(courseSchedule.getCourseName());
        courseEnrollmentForm.setInstructorName(
                courseSchedule.getInstructorLastName() + ", " + courseSchedule.getInstructorFirstName());
        courseEnrollmentForm.setVenueName(courseSchedule.getVenueName());
        courseEnrollmentForm.setCourseScheduleDetails(courseScheduleDetailForm);
        courseEnrollmentForm.setRegistrationDate(ZonedDateTime.now());

        model.addAttribute("courseEnrollmentForm", courseEnrollmentForm);

        logger.debug("courseEnrollmentForm: {}", courseEnrollmentForm);
        return "redirect:/enrollment/viewCourseEnroll";
    }

    @PostMapping("/schedules/{courseScheduleId}/enroll")
    public String submitCourseEnrollmentForm(@Valid @ModelAttribute("enroll") CourseEnrollmentForm courseEnrollmentForm,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    	System.out.println("START ENROLL");
    	 logger.debug("submitCourseEnrollmentForm:{}", courseEnrollmentForm);
         logger.debug("Result:{}", result);
         System.out.println("IF ENROLL");
         if (result.hasErrors()) {
         	redirectAttributes.addFlashAttribute("errorMsg", result.getAllErrors());
         	return "redirect:/enrollment/viewCourseEnroll";
         }
         System.out.println("TRY ENROLL");
         try {
         	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
             
             CourseScheduleDetailForm courseScheduleDetailForm = courseEnrollmentForm.getCourseScheduleDetails();
             CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(courseScheduleDetailForm.getId()).build();
             CourseParticipant courseParticipant = new CourseParticipant.Builder(courseEnrollmentForm.getCourseScheduleId(),
                     user.getId(),user.getUserName()+"@fujitsu.com" , ZonedDateTime.now()).addDetail(courseScheduleDetail).build();

         	  enrollmentService.enroll(courseParticipant);
         	  System.out.println("ADDING FLASH ATTRIBUTE");
               redirectAttributes.addFlashAttribute("successMessage", "Successfully Enrolled a Course!!!");
               redirectAttributes.addFlashAttribute("courseEnrollmentForm", courseEnrollmentForm);
               System.out.println("ADDING FLASH ATTRIBUTE");
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
    public String submitCourseEnrollmentCancelForm(@RequestParam("courseScheduleId") Long id, Model model, 
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
//        	form.setFromDateTime( ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-01 08:30:00").toInstant(),ZoneId.of("UTC")));
        	form.setFromDateTime(ZonedDateTime.now());
//        	System.out.println("SECOND FROM DATE TIME: "+form.getFromDateTime());
        }
        
        if (form.getToDateTime() == null) {
//        	form.setToDateTime( ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-10 08:30:00").toInstant(),ZoneId.of("UTC")));
        	form.setToDateTime(ZonedDateTime.now().plusDays(5));
//            System.out.println("SECOND TO DATE TIME: "+form.getToDateTime());
        }

        if(form.getFromDateTime().isAfter(form.getToDateTime()) || form.getFromDateTime().isEqual(form.getToDateTime())) {
        	  model.addAttribute(form);
              model.addAttribute("error", "To Date should be greater than or equal to From Date");
              model.addAttribute("nullMessage", "No schedules found");
              return "enrollment/viewMemberCourse";
        }
        System.out.println("From Date Time: "+ form.getFromDateTime());
        System.out.println("To Date Time: "+ form.getToDateTime());

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
	            
	            CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
	            CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();

	            courseSchedDetailForm.setId(courseScheduleDetail.getId());
	            courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
	            courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
	            courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());
	            
	            courseScheduleForm.setCourseScheduleDetails(courseSchedDetailForm);
	            courseScheduleFormSet.add(courseScheduleForm);
	            form.setCourseSchedules(courseScheduleFormSet);
	        }
        
        
        model.addAttribute("viewMemberCourse",form);
        logger.debug("courseScheduleListForm: {}", form);
        
        }catch(Exception e){
        	model.addAttribute("nullMessage", e.getMessage());
        }
        return "enrollment/viewMemberCourse";
    }
    
    /**
     * Find all active course schedules by month/quarter - ajax
     * @param queryBy
     * @return
     */
    @PostMapping("/findSchedules")
    @ResponseBody
    public Set<CourseSchedule> findAllCourseScheduleByMonthOrQuarter(@RequestBody String queryBy){
//    	String queryBy = "Quarter";
    	Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>();
//    	System.out.println("STRING IS: " + queryBy);
    	try {
    		courseScheduleSet = enrollmentService.findAllCouresScheduleByMonthOrQuarter(queryBy);
        	return courseScheduleSet;
    	}catch(Exception e) {
//    		return e.getMessage();
    		return courseScheduleSet;
    	}
    	
    }
    
    /**
     * Find all below minimum course schedule - ajax
     * @return
     */
    @GetMapping("/getAllScheduleBelowMinimum")
    @ResponseBody
    public Set<CourseScheduleForm> findAllCourseScheduleBelowMinimumParticipants(){
//    	System.out.println("CANCE BELOW MINIMUM");
    	try{
    		Set<CourseSchedule> courseScheduleSet = enrollmentService.findAllCourseScheduleBelowMinimumParticipants();
    		Set<CourseScheduleForm> courseScheduleFormSet = new HashSet<CourseScheduleForm>();
    		
    		for(CourseSchedule courseSchedule:courseScheduleSet) {
    			CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
    			CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
    			CourseScheduleDetail courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
    			
    			courseScheduleForm.setId(courseSchedule.getId());
    			courseScheduleForm.setCourseName(courseSchedule.getCourseName());
    			courseScheduleForm.setInstructorName(courseSchedule.getInstructorFirstName() + " " + courseSchedule.getInstructorLastName());
    			courseScheduleForm.setTotalParticipants(courseSchedule.getTotalParticipants());
    			courseScheduleForm.setMinRequired(courseSchedule.getMinRequired());
    			
    			courseScheduleDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
    			courseScheduleDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
    			courseScheduleDetailForm.setDuration(courseScheduleDetail.getDuration());
    			courseScheduleForm.setCourseScheduleDetails(courseScheduleDetailForm);
    			
    			courseScheduleFormSet.add(courseScheduleForm);
    			
//    			System.out.println("CONTROLLER COURSE " + courseScheduleForm.getId() + " " + courseScheduleForm.getCourseName());
//    			System.out.println("START:" + courseScheduleDetailForm.getScheduledStartDateTime() + " END: " + courseScheduleDetailForm.getScheduledEndDateTime() + " Duration: " + courseScheduleDetailForm.getDuration() );
    		}
    		return courseScheduleFormSet;
    	}catch(Exception e) {
    		throw new IllegalArgumentException("Cant get CourseSchedule Below Minimum" + e.getMessage());
    	}
    	
    }
    /**
     * Reschedule course schedule
     * @param form
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/reschedule")
    public String rescheduleCourseScheduleById(@Valid @ModelAttribute("courseScheduleDetailForm") CourseScheduleDetailForm form,BindingResult result,Model model,
    			RedirectAttributes redirectAttributes) {
    	System.out.println("RESCHEDULE!");
    	System.out.println("(RESCHEDULE)Course Schedule Detail ID: " + form.getId());
    	System.out.println("(RESCHEDULE)Scheduled Start DateTime: " + form.getScheduledStartDateTime());
    	System.out.println("(RESCHEDULE)Scheduled End DateTime: " + form.getScheduledEndDateTime());
    	try {
    	Long durationToHours = Duration.between(form.getScheduledStartDateTime(), form.getScheduledEndDateTime()).toHours();
        Long durationToMinutes = Duration.between(form.getScheduledStartDateTime(), form.getScheduledEndDateTime()).toMinutes();
        float duration = 0;
        if (durationToMinutes % 60 == 0) {
        	duration = durationToHours;
        } else {
        	float minutes = durationToMinutes % 60;
        	float hour = durationToMinutes /60;
        	duration = hour + (minutes/60);
        }
    	CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(form.getId(), form.getScheduledStartDateTime(), form.getScheduledEndDateTime(), duration).build();
    	System.out.println("CONTROLLER ID: " + courseScheduleDetail.getId());
    	System.out.println("CONTROLLER START: " + courseScheduleDetail.getScheduledStartDateTime());
    	System.out.println("CONTROLLER END: " + courseScheduleDetail.getScheduledEndDateTime());
    	System.out.println("CONTROLLER DURATION: "+ courseScheduleDetail.getDuration());
    	enrollmentService.rescheduleCourseScheduleById(courseScheduleDetail);
    	return "redirect:/enrollment/viewCourseEnroll";
    	   }catch(NullPointerException e){
    	       redirectAttributes.addFlashAttribute("errorMessageReschedule", "Hours Count must stay the same!");
    	   }
    	return "redirect:/enrollment/viewCourseEnroll";
    }
    
    
    /**
     * Find all member not enrolled in course schedule
     * @param courseScheduleId
     * @return
     */
    @PostMapping("/notEnrolledByCourseScheduleId")
    @ResponseBody
    public Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(@RequestBody Long courseScheduleId){
    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();
//    	try {
    		CourseParticipant courseParticipant = new CourseParticipant.Builder().addCourseScheduleIdAndEmployeeNumber(courseScheduleId, user.getEmployeeNumber()).build();
        	courseParticipantSet = enrollmentService.findAllMemberNotEnrolledByCourseScheduleId(courseParticipant);
//    	}catch(Exception e) {
//    		e.printStackTrace();
//    	}
    	return courseParticipantSet;
    }
    
    /**
     * Find all member enrolled in course schedule
     * @param courseScheduleId
     * @return
     */
    @PostMapping("/findEnrolledMember")
    @ResponseBody
    public Set<CourseParticipant> findAllEnrolledMemberByCourseScheduleId(@RequestBody Long courseScheduleId){
    	Set<CourseParticipant> courseParticipant = new HashSet<CourseParticipant>();
    	
//    	try {
    		courseParticipant = enrollmentService.findAllParticipantByCourseScheduleId(courseScheduleId);
//    	}catch(Exception e) {
//    		e.printStackTrace();
//    	}
    	return courseParticipant;
    }
    
    /**
     * Find all member not enrolled in course schedule
     * @param search
     * @param request
     * @return
     */
    @PostMapping("/findMember")
    @ResponseBody
    public Set<CourseParticipant> findMember(@RequestBody SearchForm search, HttpServletRequest request){
    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Set<CourseParticipant> courseParticipant = new HashSet<CourseParticipant>();
		System.out.println("REQUEST: " + request);
//		try {
			search.setEmployeeNumber(user.getEmployeeNumber());
			courseParticipant = enrollmentService.findMemberNotEnrolledByCourseScheduleId(search);
//    	}catch(Exception e) {
//    		e.printStackTrace();
//    	}
		return courseParticipant;
		
    	
    }
    
    /**
     * Enroll a member to course schedule
     * @param courseEnrollmentForm
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/enrollMember")
    public String submitCourseEnrollmentMemberForm(@Valid @ModelAttribute CourseEnrollmentForm courseEnrollmentForm,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    	try {
	    		System.out.println("CourseScheduleId: " + courseEnrollmentForm.getCourseScheduleId() + 
	        			" CourseScheduleDetailId: " + courseEnrollmentForm.getCourseScheduleDetails().getId() +
	        			" ParticipantId: " + courseEnrollmentForm.getId() + "EMAIL: " + courseEnrollmentForm.getEmailAddress());
	        	System.out.println("IT WORKS");
	        	System.out.println("(POST ENROLL)Participant ID: " + courseEnrollmentForm.getId());
	            System.out.println("(POST ENROLL)Course Schedule ID: " + courseEnrollmentForm.getCourseScheduleId());
	            System.out.println("(POST ENROLL)Email: " + courseEnrollmentForm.getCourseName());
	           
	            CourseScheduleDetailForm courseScheduleDetailForm = courseEnrollmentForm.getCourseScheduleDetails();
	            System.out.println("(POST ENROLL)Course Schedule Detail ID: " + courseScheduleDetailForm.getId());

	            CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(courseScheduleDetailForm.getId()).build();

	            CourseParticipant courseParticipant = new CourseParticipant.Builder(courseEnrollmentForm.getCourseScheduleId(),
	            		courseEnrollmentForm.getId(), courseEnrollmentForm.getEmailAddress(), ZonedDateTime.now()).addDetail(courseScheduleDetail).build();
	        	  enrollmentService.enroll(courseParticipant);
	            
	            redirectAttributes.addFlashAttribute("successMsg", "Successfully Enrolled a Member!!!");
	            redirectAttributes.addFlashAttribute("courseEnrollmentForm", courseEnrollmentForm);
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		model.addAttribute("errorMessage01", e.getMessage());
    	}
    	return "redirect:/enrollment/viewMemberCourse";
    }
    
    /**
     * Cancel all below minimum participant course schedule
     * @param courseEnrollCancelForm
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/cancelCourseSchedules")
    public String cancelCourseScheduleByMinimumParticipants(@Valid @ModelAttribute CourseEnrollCancelForm courseEnrollCancelForm,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    	
    	Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>();
    	for(Long id:courseEnrollCancelForm.getIds()) {
    		CourseSchedule courseSchedule = new CourseSchedule.Builder(id).cancel().build();
    		courseScheduleSet.add(courseSchedule);
    	}
    	enrollmentService.cancelCourseSchedules(courseScheduleSet);
    	redirectAttributes.addFlashAttribute("successMessage", "Successfully cancelled all course schedule below minimum participant.");
    	return "redirect:/enrollment/viewCourseEnroll";
    }
    
    @PostMapping("/findCourseSchedule")
    @ResponseBody
    public Set<CourseSchedule> findCourseScheduleByCourseId(@RequestBody CourseSchedule courseSchedule){
    	System.out.println("Course ID: " + courseSchedule.getCourseId());
    	System.out.println("Course Schedule ID: " + courseSchedule.getId());

    	return enrollmentService.findCourseScheduleByCourseId(courseSchedule);
    }

    @PostMapping("/updateSchedule")
    public String updateCourseSchedule(@Valid @ModelAttribute CourseEnrollmentForm courseEnrollmentForm,
    			BindingResult bindingResult,
    			Model model,
    			RedirectAttributes redirectAttributes) {
    	System.out.println("To be replaced: " + courseEnrollmentForm.getId());
    	System.out.println("New Course Schedule ID: " + courseEnrollmentForm.getCourseScheduleId());  	

    	
    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	try {
    		CourseParticipant courseParticipant = new CourseParticipant.Builder(courseEnrollmentForm.getId(), 
        			courseEnrollmentForm.getCourseScheduleId(), user.getId()).build();
        	
        	enrollmentService.updateSchedule(courseParticipant);
        	redirectAttributes.addFlashAttribute("successMessageChangeSchedule", "Successfully change schedule");
    	}catch(Exception e) {
    		redirectAttributes.addFlashAttribute("error", e.getMessage());
    	}
    	
    	
    	return "redirect:/enrollment/mySchedules";
    }

//    @PostMapping("/findSchedules")
//    @ResponseBody
//    public Set<CourseSchedule> findAllCourseScheduleByMonthOrQuarter(@RequestBody String queryBy){
////    	String queryBy = "Quarter";
//    	Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>();
////    	System.out.println("STRING IS: " + queryBy);
//    	try {
//    		courseScheduleSet = enrollmentService.findAllCouresScheduleByMonthOrQuarter(queryBy);
//        	return courseScheduleSet;
//    	}catch(Exception e) {
////    		return e.getMessage();
//    		return courseScheduleSet;
//    	}
//    	
//    }
    
    
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
