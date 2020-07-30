package com.fujitsu.ph.tsup.attendance.web;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.attendance.model.AttendanceParticipantDetail;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusCourse;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusForm;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusParticipant;
import com.fujitsu.ph.tsup.attendance.model.CourseAttendanceForm;
import com.fujitsu.ph.tsup.attendance.model.CourseParticipantsForm;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleForm;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.attendance.model.GenerateAttendanceCourse;
import com.fujitsu.ph.tsup.attendance.model.GenerateAttendanceForm;
import com.fujitsu.ph.tsup.attendance.service.AttendanceService;

//==================================================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : AttendanceController.java
//
//<<Modification History>>
//Version | Date       | Updated By                                                              | Content
//--------+------------+---------------------------------------------------------+-----------------
//0.01    | 06/23/2020 | WS) K.Abad, WS) M.Angara, WS) H.Francisco, WS) J.Iwarat, WS) R.Ramos    | New Creation
//0.02    | 06/29/2020 | WS) J.Iwarat                                                            | Update
//0.03    | 06/30/2020 | WS) J.Iwarat                                                            | Update
//0.04    | 07/08/2020 | WS) R.Ramos                                                             | Update
//0.05    | 07/30/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos   								 | Update
//==================================================================================================
/**
 * <pre>
* It is the implementation of attendance service
* In this class, it implements the AttendanceService class for the initial setting of the database
 * </pre>
 * 
 * @version 0.05
 * @author k.abad
 * @author m.angara
 * @author h.francisco
 * @author j.iwarat
 * @author r.ramos
 * 
 */

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    /*
     * <pre> It is the interface of attendance service
     * 
     * <pre>
     */
    @Autowired
    private AttendanceService attendanceService;

    /*
     * <pre> Useful methods for logging and also decouple the logging implementation
     * from application
     * 
     * <pre>
     */
    private static Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    /**
     * <pre>
     * US25. As an instructor, I can view the courses where I am the instructor. URL
     * Value = /schedules , method = GET if bindingResult.hasErrors(), Return the
     * course schedule list form and view Set the following fields, if
     * courseScheduleListForm.toDateTime < courseScheduleListForm.fromDateTime
     * Return the course schedule list form and view with a message "To Date should
     * be greater than or equal to From Date" Call
     * attendanceService.findAllScheduledCoursesByInstructor using the given
     * courseScheduleListForm.fromDateTime, courseScheduleListForm.toDateTime and
     * the user id from the Authentication object Return the course schedule list
     * form and view
     * 
     * <pre>
     * 
     * @param courseScheduleListForm
     * @param bindingResult
     * @return model
     */
    // @GetMapping
    // public void viewAllCourseSchedule

    /**
     * <pre>
     * US26. As an instructor, I can view the participants of my courses. URL Value
     * = /schedules/{courseScheduleId}/participants, method = GET Call
     * attendanceService.findAllScheduledCoursesByInstructor using the date today,
     * date today + 5 days and the user id from the Authentication object Call
     * attendanceService.findCourseScheduleById using the given id Set the values
     * from the previous step into the CourseParticipantsForm Return the course
     * participants form and view
     * 
     * <pre>
     * 
     * @param id
     * @return attendance/viewInstructorCourseParticipants
     */
    @GetMapping("/schedules/{courseScheduleId}/participants")
    public String showCourseParticipantsForm(@PathVariable("courseScheduleId") Long id, Model model) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ZonedDateTime toDateTime = ZonedDateTime.now().plusMonths(1);
        ZonedDateTime fromDateTime = ZonedDateTime.now().minusMonths(1);

        logger.debug("Model:{}", model);

        CourseParticipantsForm courseParticipantsForm = new CourseParticipantsForm();

        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();

        Set<CourseScheduleForm> setCourseScheduleForm = new HashSet<CourseScheduleForm>();
        Set<CourseScheduleDetailForm> setCourseScheduleDetailForm = new HashSet<CourseScheduleDetailForm>();
        Set<AttendanceParticipantDetail> setAttendanceParticipantDetail = new HashSet<AttendanceParticipantDetail>();

        Set<CourseSchedule> courseScheduleList = attendanceService.findAllScheduledCoursesByInstructor(fromDateTime,
                toDateTime, user.getId());

        for (CourseSchedule courseSchedule : courseScheduleList) {
            CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
            courseScheduleForm.setCourseId(courseSchedule.getCourseId());
            Set<CourseScheduleDetail> CourseScheduleDetailSet = courseSchedule.getCourseScheduleDetail();
            for (CourseScheduleDetail courseScheduleDetail : CourseScheduleDetailSet) {
                courseScheduleForm.setCourseName(
                        courseSchedule.getCourseName() + " | " + courseScheduleDetail.getScheduledStartDateTime()
                                + " --- " + courseScheduleDetail.getScheduledEndDateTime());
            }

            setCourseScheduleForm.add(courseScheduleForm);
        }

        Set<CourseParticipant> courseParticipantList = attendanceService.findCourseScheduleById(id);
        for (CourseParticipant courseParticipant : courseParticipantList) {
            courseParticipantsForm.setCourseName(courseParticipant.getCourseName());
            courseParticipantsForm.setInstructorName(courseParticipant.getInstructorName());

            courseScheduleDetailForm.setScheduledStartDateTime(courseParticipant.getScheduledStartDateTime());
            courseScheduleDetailForm.setScheduledEndDateTime(courseParticipant.getScheduledEndDateTime());
            setCourseScheduleDetailForm.add(courseScheduleDetailForm);

            float totalDuration = courseParticipant.getDuration();
            model.addAttribute("duration", totalDuration);

            attendanceParticipantDetail.setName(courseParticipant.getParticipantName());
            attendanceParticipantDetail.setId(courseParticipant.getId());
            attendanceParticipantDetail.setEmail(courseParticipant.getEmail());
            attendanceParticipantDetail.setEmployeeNumber(courseParticipant.getEmployeeNumber());
            setAttendanceParticipantDetail.add(attendanceParticipantDetail);
            System.out.println(attendanceParticipantDetail.getId());
        }
        courseParticipantsForm.setCourseScheduleDetails(setCourseScheduleDetailForm);
        courseParticipantsForm.setParticipants(setAttendanceParticipantDetail);
        courseParticipantsForm.setCourseSchedules(setCourseScheduleForm);

        model.addAttribute("lastSelected", id);
        model.addAttribute("courseParticipant", courseParticipantsForm);
        return "attendance/viewInstructorCourseParticipants";

    }

    /**
     * <pre>
     * US13. As an instructor, I can set the attendance status - Present or Absent.
     * URL Value = /participants, method = GET Call
     * attendanceService.findAllScheduledCoursesByInstructor using the date today as
     * from and to date if form.id is not null and not zero, Call
     * attendanceService.findCourseAttendanceByCourseScheduleDetailId using the
     * given form.id Set the values from the previous step into the ChangeStatusForm
     * Return the change status form and view
     * 
     * <pre>
     * 
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @GetMapping("/{courseScheduleDetailId}/participants")
    public String showChangeStatusParticipantsForm(@PathVariable("courseScheduleDetailId") Long id, 
            @Valid ChangeStatusForm form, BindingResult bindingResult, Model model) {
          
        FpiUser user = (FpiUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        ZonedDateTime toDateTime = ZonedDateTime.now().plusMonths(5);
        ZonedDateTime fromDateTime = ZonedDateTime.now().minusMonths(5);
        
        Set<ChangeStatusCourse> changeStatusCourseSet = new HashSet<ChangeStatusCourse>();
        Set<CourseSchedule> courseScheduleSet = attendanceService.
                findAllScheduledCoursesByInstructor(fromDateTime, toDateTime, user.getId());
        
        for(CourseSchedule courseSchedule : courseScheduleSet) {
            ChangeStatusCourse changeStatusCourseForm = new ChangeStatusCourse();
            changeStatusCourseForm.setId(courseSchedule.getId());
            Set<CourseScheduleDetail> CourseScheduleDetailSet = courseSchedule.getCourseScheduleDetail();
            
            for (CourseScheduleDetail courseScheduleDetail : CourseScheduleDetailSet) {
                changeStatusCourseForm.setCourseName(courseSchedule.getCourseName() + " | " + 
                courseScheduleDetail.getScheduledStartDateTime() + " --- " + 
                courseScheduleDetail.getScheduledEndDateTime());
          }    

            changeStatusCourseSet.add(changeStatusCourseForm);
        }      
           
            form.setCourses(changeStatusCourseSet);
                      
        if (id != null && id != 0) {
            
            Set<CourseAttendance> courseAttendanceSet = attendanceService
                    .findCourseAttendanceByCourseScheduleDetailId(id);
            Set<ChangeStatusParticipant> changeStatusParticipantSet = new HashSet<ChangeStatusParticipant>();
            
            for (CourseAttendance courseAttendance : courseAttendanceSet) {
                ChangeStatusParticipant changeStatusParticipantForm = new ChangeStatusParticipant();
                changeStatusParticipantForm.setCourseAttendanceId(courseAttendance.getId());
                changeStatusParticipantForm.setName(courseAttendance.getParticipantName());
                changeStatusParticipantForm.setLoginDateTime(courseAttendance.getLoginDateTime());
                changeStatusParticipantForm.setParticipantId(courseAttendance.getParticipantId());
                changeStatusParticipantForm.setStatus(courseAttendance.getStatus());
                changeStatusParticipantSet.add(changeStatusParticipantForm);               
            }
                
                form.setParticipants(changeStatusParticipantSet);
        }
                model.addAttribute("changeAttendanceStatus", form);
                model.addAttribute("lastSelected", id);
                logger.debug("ChangeStatusForm: {}", form);
                logger.debug("Result: {}", bindingResult);
                return "attendance/changeAttendanceStatus";
    }

    /**
     * <pre>
     * US13. As an instructor, I can set the attendance status - Present or Absent.
     * URL Value = /participants, method = POST Call
     * attendanceService.findAllScheduledCoursesByInstructor using the date today as
     * from and to date if form.id is not null and not zero, Call
     * attendanceService.findCourseAttendanceByCourseScheduleDetailId using the
     * given form.id if bindingResult.hasErrors(), Return the change status form and
     * view Transform the form into CourseAttendance and add into a Set Call
     * attendanceService.changeStatus using the Set created in the previous step
     * Redirect to /participants and return a success message.
     * 
     * <pre>
     * 
     * @param form
     * @param bindingResult
     * @param model
     * @return redirect:/participants
     */
    @PostMapping("/{courseScheduleDetailId}/participants")
    public String submitChangeStatusParticipantsForm(@PathVariable("courseScheduleDetailId") Long id, 
        @Valid @ModelAttribute("changeStatusForm") ChangeStatusForm form, BindingResult bindingResult, 
        Model model, RedirectAttributes redirectAttributes) {
        
        logger.debug("ChangeStatusForm: {}", form);
        logger.debug("Result: {}", bindingResult);
        
        FpiUser user = (FpiUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        ZonedDateTime toDateTime = ZonedDateTime.now().plusMonths(5);
        ZonedDateTime fromDateTime = ZonedDateTime.now().minusMonths(5);
        
        CourseAttendanceForm courseAttendanceForm = new CourseAttendanceForm();
        ChangeStatusParticipant changeStatusParticipantForm = new ChangeStatusParticipant();
       
        Set<ChangeStatusCourse> changeStatusCourseSet = new HashSet<ChangeStatusCourse>();
        Set<CourseSchedule> courseScheduleSet = attendanceService.
                findAllScheduledCoursesByInstructor(fromDateTime, toDateTime, user.getId());
        
        for(CourseSchedule courseSchedule : courseScheduleSet) {           
        ChangeStatusCourse changeStatusCourseForm = new ChangeStatusCourse();        
        changeStatusCourseForm.setId(courseSchedule.getId());
        changeStatusCourseSet.add(changeStatusCourseForm); 
        form.setId(courseSchedule.getId());     
        }
    
        if (form.getId() != null && form.getId() != 0) {
        Set<ChangeStatusParticipant> changeStatusParticipantSet = new HashSet<ChangeStatusParticipant>();
        Set<CourseAttendance> courseAttendanceSet = attendanceService
                .findCourseAttendanceByCourseScheduleDetailId(form.getId());
        
        for (CourseAttendance courseAttendance : courseAttendanceSet) {           
            courseAttendanceForm.setId(courseAttendance.getId());
            courseAttendanceForm.setCourseScheduleDetailId(courseAttendance.getCourseScheduleDetailId());
            courseAttendanceForm.setCourseName(courseAttendance.getCourseName());
            courseAttendanceForm.setInstructorName(courseAttendance.getInstructorName());
            courseAttendanceForm.setVenueName(courseAttendance.getVenueName());
            courseAttendanceForm.setScheduledStartDateTime(courseAttendance.getScheduleStartDateTime());
            courseAttendanceForm.setScheduledEndDateTime(courseAttendance.getScheduleEndDateTime());
            courseAttendanceForm.setDuration(courseAttendance.getDuration());
          
            changeStatusParticipantForm.setCourseAttendanceId(courseAttendance.getId());
            changeStatusParticipantForm.setName(courseAttendance.getParticipantName());
            changeStatusParticipantForm.setLoginDateTime(courseAttendance.getLoginDateTime());
            changeStatusParticipantForm.setParticipantId(courseAttendance.getParticipantId());
            changeStatusParticipantForm.setStatus(courseAttendance.getStatus());
            changeStatusParticipantSet.add(changeStatusParticipantForm);               
        }
            form.setParticipants(changeStatusParticipantSet);
        }

        if (bindingResult.hasErrors()) {
            return "attendance/changeAttendanceStatus";
        }
        
        model.addAttribute("changeStatusForm", form);
        
        Set<CourseAttendance> setCourseAttendance = new HashSet<CourseAttendance>();
        
        CourseAttendance courseAttendance1 = new CourseAttendance.Builder(form.getId(), courseAttendanceForm.getCourseScheduleDetailId(), 
            courseAttendanceForm.getCourseName(), courseAttendanceForm.getInstructorName(), courseAttendanceForm.getVenueName(), 
            changeStatusParticipantForm.getParticipantId(), changeStatusParticipantForm.getName(), courseAttendanceForm.getScheduledStartDateTime(), 
            courseAttendanceForm.getScheduledEndDateTime(), courseAttendanceForm.getDuration(), changeStatusParticipantForm.getLoginDateTime(), 
            changeStatusParticipantForm.getStatus()).absent().present(changeStatusParticipantForm.getLoginDateTime()).build();
            setCourseAttendance.add(courseAttendance1);
  
        attendanceService.changeStatus(setCourseAttendance);
        return "redirect:/attendance/0/participants";
        
    }

    /**
     * <pre>
     * UC15. As an instructor, I can generate an attendance sheet. URL Value =
     * /generate/{courseScheduleDetailId}/present, method = GET Call
     * attendanceService.findAllScheduledCoursesByInstructor using the date today
     * minus 5 days as from and date today as to date if id is not null and not
     * zero, Call attendanceService.findCourseAttendanceByCourseScheduleDetailId
     * using the given id Set the values from the previous step into the
     * GenerateAttendanceForm Return the generate status form and view
     * 
     * <pre>
     * 
     * @param id
     * @param model
     * @return
     * 
     */
    @GetMapping("/generate/{courseScheduleDetailId}/present")
    public String showGenerateAttendanceForm(@PathVariable("courseScheduleDetailId") Long id, Model model) {

        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ZonedDateTime fromDate = ZonedDateTime.now().minusMonths(1);
        ZonedDateTime toDate = ZonedDateTime.now().plusMonths(1);

        GenerateAttendanceForm generateAttendanceForm = new GenerateAttendanceForm();

        Set<GenerateAttendanceCourse> setGenerateAttendanceCourse = new HashSet<GenerateAttendanceCourse>();
        Set<AttendanceParticipantDetail> setAttendanceParticipantDetail = new HashSet<AttendanceParticipantDetail>();

        /*
         * 
         * <pre> Id is for authentication implementation <pre>
         */
        Set<CourseSchedule> courseScheduleList = attendanceService.findAllScheduledCoursesByInstructor(fromDate, toDate,
                user.getId());

        for (CourseSchedule courseSchedule : courseScheduleList) {
            GenerateAttendanceCourse generateAttendanceCourseForm = new GenerateAttendanceCourse();
            generateAttendanceCourseForm.setId(courseSchedule.getId());

            Set<CourseScheduleDetail> CourseScheduleDetailSet = courseSchedule.getCourseScheduleDetail();
            for (CourseScheduleDetail courseScheduleDetail : CourseScheduleDetailSet) {
                generateAttendanceCourseForm.setCourseName(
                        courseSchedule.getCourseName() + " | " + courseScheduleDetail.getScheduledStartDateTime()
                                + " --- " + courseScheduleDetail.getScheduledEndDateTime());
            }
            setGenerateAttendanceCourse.add(generateAttendanceCourseForm);
        }

        if (id != null && id != 0) {

            Set<CourseAttendance> courseAttendanceList = attendanceService
                    .findCourseAttendanceByCourseScheduleDetailId(id);

            for (CourseAttendance courseAttendance : courseAttendanceList) {
                generateAttendanceForm.setCourseName(courseAttendance.getCourseName());
                generateAttendanceForm.setInstructorName(courseAttendance.getInstructorName());
                generateAttendanceForm.setVenueName(courseAttendance.getVenueName());
                generateAttendanceForm.setDuration(courseAttendance.getDuration());
                generateAttendanceForm.setScheduledStartDateTime(courseAttendance.getScheduleStartDateTime());
                generateAttendanceForm.setScheduledEndDateTime(courseAttendance.getScheduleEndDateTime());

                AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();
                /*
                 * <pre> Email and EmployeeNumber is on Q.A.Does not exist Company name and
                 * Signature is on Q.A no. 11 and 14 in UI wireframe 画面レイアウト <pre>
                 */
                // attendanceParticipantDetail.setEmail(CourseAttendance.getEmail());
                // attendanceParticipantDetail.setEmployeeNumber(courseAttendance.getParticipantId());
                attendanceParticipantDetail.setLoginDateTime(courseAttendance.getLoginDateTime());
                attendanceParticipantDetail.setName(courseAttendance.getParticipantName());
                attendanceParticipantDetail.setStatus(courseAttendance.getStatus());
                setAttendanceParticipantDetail.add(attendanceParticipantDetail);

            }
        }

        generateAttendanceForm.setCourses(setGenerateAttendanceCourse);
        generateAttendanceForm.setParticipants(setAttendanceParticipantDetail);

        model.addAttribute("lastSelected", id);
        model.addAttribute("generateAttendance", generateAttendanceForm);
        return "attendance/generateAttendanceSheet";

    }

    /**
     * <pre>
     * As a PMO, I can generate an attendance sheet together with the sign up sheet
     * so that I will know who signed up but didn't attend. URL Value =
     * /generate/{courseScheduleDetailId}/absent, method = GET Call
     * attendanceService.findAllScheduledCoursesByInstructor using the date today
     * minus 5 days as from and date today as to date if id is not null and not zero
     * Call attendanceService.findCourseAbsentParticipantByCourseScheduleDetailId
     * using the given id Set the values from the previous step into the
     * GenerateAttendanceForm 4. Return the generate status form and view
     * 
     * <pre>
     * 
     * @param id
     * @param model
     * @param @PathVariable("courseScheduleDetailId")
     * @return attendance/generateAbsentSheet
     * 
     */
    @GetMapping("/generate/{courseScheduleDetailId}/absent")
    public String showGenerateAbsentForm(@PathVariable("courseScheduleDetailId") Long id, Model model) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        ZonedDateTime fromDate = ZonedDateTime.now().minusMonths(5);
        ZonedDateTime toDate = ZonedDateTime.now().plusMonths(5);

        logger.debug("Model:{}{}", model,id);
        
        GenerateAttendanceForm generateAttendanceForm = new GenerateAttendanceForm();
        
        Set<GenerateAttendanceCourse> generateAttendanceCourseSet = new HashSet<GenerateAttendanceCourse>();
        Set<CourseSchedule> courseScheduleSet = attendanceService.
                findAllScheduledCoursesByInstructor(fromDate, toDate, user.getId());

        for (CourseSchedule courseSchedule : courseScheduleSet) {
            GenerateAttendanceCourse generateAttendanceCourseForm = new GenerateAttendanceCourse(); 
            generateAttendanceCourseForm.setId(courseSchedule.getId());

                       
            Set<CourseScheduleDetail> CourseScheduleDetailSet = courseSchedule.getCourseScheduleDetail();
            for (CourseScheduleDetail courseScheduleDetail : CourseScheduleDetailSet) {
                generateAttendanceCourseForm.setCourseName(courseSchedule.getCourseName() + " | " + 
                courseScheduleDetail.getScheduledStartDateTime() + " --- " + 
                courseScheduleDetail.getScheduledEndDateTime());
          }
                generateAttendanceCourseSet.add(generateAttendanceCourseForm);

        }
            generateAttendanceForm.setCourses(generateAttendanceCourseSet);
                           
                if (id != null && id != 0) {  
                    Set<CourseAttendance> courseAttendanceSet = attendanceService
                            .findCourseAbsentParticipantByCourseScheduleDetailId(id);  
                    
                    Set<AttendanceParticipantDetail> attendanceParticipantDetailSet = new HashSet<AttendanceParticipantDetail>();
                    
                    for (CourseAttendance courseAttendance : courseAttendanceSet) {
                        AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();
                        
                        /*
                         * <pre> Email and EmployeeNumber variable does not exist and it is on Q.A.
                         * COMPANY/BU/DEPT and SIGNATURE is on Q.A no. 31 and 34 in UI wireframe
                         * (24_AttendanceForAbsent) </pre>
                         */
//                        attendanceParticipantDetail.setEmployeeNumber(courseAttendance.getParticipantId());
//                        attendanceParticipantDetail.setEmail(courseAttendance.getEmail());
                        attendanceParticipantDetail.setName(courseAttendance.getParticipantName());
                        attendanceParticipantDetail.setLoginDateTime(courseAttendance.getLoginDateTime());
                        attendanceParticipantDetail.setStatus(courseAttendance.getStatus());                       
                        attendanceParticipantDetailSet.add(attendanceParticipantDetail);
                        
                        generateAttendanceForm.setCourseName(courseAttendance.getCourseName());
                        generateAttendanceForm.setInstructorName(courseAttendance.getInstructorName());
                        generateAttendanceForm.setVenueName(courseAttendance.getVenueName());
                        generateAttendanceForm.setScheduledStartDateTime(courseAttendance.getScheduleStartDateTime());
                        generateAttendanceForm.setScheduledEndDateTime(courseAttendance.getScheduleEndDateTime());
                        generateAttendanceForm.setDuration(courseAttendance.getDuration());
                    } 
                    
                        generateAttendanceForm.setParticipants(attendanceParticipantDetailSet);
               }
                        model.addAttribute("attendanceForAbsent", generateAttendanceForm);
                        model.addAttribute("lastSelected", id);
                        return "attendance/attendanceForAbsent";
    }

    /**
     * <pre>
     * US14. As an member, I can attend the courses. URL Value =
     * /signin/{courseScheduleDetailId}, method = GET Call
     * attendanceService.findCourseScheduleDetailById using the given id Set the
     * values from the previous step into the CourseAttendanceForm Return the course
     * attendance form and view
     * 
     * <pre>
     * 
     * @param id
     * @param model
     * @return attendance/attend
     * @author r.ramos
     */
	@GetMapping("/signin/{courseScheduleDetailId}")
	public String showAttendanceForm(@PathVariable("courseScheduleDetailId")Long id, Model model) {
		logger.debug("Model:{}", model);
		
		CourseAttendanceForm courseAttendanceForm = new CourseAttendanceForm();

		Set<CourseAttendance> courseAttendanceList = attendanceService.findCourseScheduleDetailById(id);
		Set<CourseAttendance> setCourseAttendance = new HashSet<CourseAttendance>();

		for (CourseAttendance courseAttendance : courseAttendanceList) {
			courseAttendanceForm.setId(courseAttendance.getId());
			courseAttendanceForm.setCourseScheduleDetailId(courseAttendance.getCourseScheduleDetailId());
			courseAttendanceForm.setCourseName(courseAttendance.getCourseName());
			courseAttendanceForm.setInstructorName(courseAttendance.getInstructorName());
			courseAttendanceForm.setScheduledStartDateTime(courseAttendance.getScheduleStartDateTime());
			courseAttendanceForm.setScheduledEndDateTime(courseAttendance.getScheduleEndDateTime());
			courseAttendanceForm.setVenueName(courseAttendance.getVenueName());
			setCourseAttendance.add(courseAttendance);
		}
		
		model.addAttribute("courseAttendance", courseAttendanceForm);
		return "attendance/attend";
	}

    /**
     * <pre>
     * US14. As an member, I can attend the courses. URL Value =
     * /signin/{courseScheduleDetailId}, method = POST if bindingResult.hasErrors()
     * Return the course attendance form and view Transform the form into
     * CourseAttendance Call attendanceService.attend using the courseAttendance
     * from previous step Return the course attendance form and view
     * 
     * <pre>
     * 
     * @param id
     * @param form
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @return redirect:/attendance/signin/{courseScheduleDetailId}
     * @author r.ramos
     */
	@PostMapping("/signin/{courseScheduleDetailId}")
	public String submitAttendanceForm(@PathVariable("courseScheduleDetailId")Long id,
			@Valid @ModelAttribute("courseAttendance") CourseAttendanceForm form, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		
		logger.debug("CourseAttendanceForm: {}", form);
		logger.debug("Result: {}", bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "attendance/attend";
		}
		
		model.addAttribute("courseAttendance", form);
		
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CourseAttendance courseAttendance = new CourseAttendance.Builder(form.getCourseScheduleDetailId(), user.getId())
				.present(ZonedDateTime.now()).build();

		attendanceService.attend(courseAttendance);
		
		return "redirect:/attendance/signin/{courseScheduleDetailId}";

	}

    /**
	 * <pre>
	 * US14. As an member, I can attend the courses (view a list of enrolled course
	 * schedules). URL Value = /signin, method = GET
	 * 
	 * <pre>
	 * 
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return attendance/attendCourseList
	 * @author r.ramos
	 */
	@GetMapping("/signin")
	public String viewEnrolledCourseSchedule(CourseScheduleListForm form, BindingResult bindingResult,
            Model model) {
		FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		logger.debug("CourseScheduleListForm: {}", form);
        logger.debug("Result: {}", bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "attendance/attendCourseList";
        }
        
        if (form.getFromDateTime() == null) {
            form.setFromDateTime(ZonedDateTime.now().minusMonths(5));
        }
        
        if (form.getToDateTime() == null) {
            form.setToDateTime(ZonedDateTime.now().plusMonths(5));
        }
        
        if(form.getFromDateTime().isAfter(form.getToDateTime())) {
            model.addAttribute(form);
            model.addAttribute("error", "To Date should be greater than or equal to From Date");
            return "attendance/attendCourseList";
      }
        
        Set<CourseParticipant> courseParticipantList = attendanceService.findAllScheduledCoursesByParticipant(form.getFromDateTime(), 
        		form.getToDateTime(), user.getId());
        
        Set<CourseScheduleForm> setCourseScheduleForm = new HashSet<CourseScheduleForm>();
       
        Set<CourseScheduleDetailForm> setCourseScheduleDetailForm = new HashSet<CourseScheduleDetailForm>();
         
        CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
        
        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        
        for (CourseParticipant courseParticipant : courseParticipantList) {
        		
        		courseScheduleForm.setCourseName(courseParticipant.getCourseName());
        		courseScheduleForm.setInstructorName(courseParticipant.getInstructorName());
        		setCourseScheduleForm.add(courseScheduleForm);        		
        		
        		courseScheduleDetailForm.setId(courseParticipant.getCourseScheduleId());
        		courseScheduleDetailForm.setScheduledStartDateTime(courseParticipant.getScheduledStartDateTime());
        		courseScheduleDetailForm.setScheduledEndDateTime(courseParticipant.getScheduledEndDateTime());
        		courseScheduleDetailForm.setDuration(courseParticipant.getDuration());
        		setCourseScheduleDetailForm.add(courseScheduleDetailForm);
                
        		}     
        
        courseScheduleForm.setCourseScheduleDetails(setCourseScheduleDetailForm);
        form.setCourseSchedules(setCourseScheduleForm);
             
        model.addAttribute("enrolledCourse", form);
        return "attendance/attendCourseList";
	}
}
