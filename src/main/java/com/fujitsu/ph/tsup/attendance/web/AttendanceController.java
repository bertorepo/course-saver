package com.fujitsu.ph.tsup.attendance.web;

import java.time.Duration;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.attendance.domain.CourseAttendance;
import com.fujitsu.ph.tsup.attendance.domain.CourseParticipant;
import com.fujitsu.ph.tsup.attendance.domain.CourseSchedule;
import com.fujitsu.ph.tsup.attendance.model.AttendanceParticipantDetail;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusCourse;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusForm;
import com.fujitsu.ph.tsup.attendance.model.ChangeStatusParticipant;
import com.fujitsu.ph.tsup.attendance.model.CourseAttendanceForm;
import com.fujitsu.ph.tsup.attendance.model.CourseParticipantsForm;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.attendance.model.CourseScheduleForm;
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
//==================================================================================================
/**
 * <pre>
* It is the implementation of attendance service
* In this class, it implements the AttendanceService class for the initial setting of the database
 * </pre>
 * 
 * @version 0.03
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
    public String showCourseParticipantsForm(Long id, Model model,
            Set<CourseScheduleDetailForm> setCourseScheduleDetails) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ZonedDateTime toDateTime = ZonedDateTime.now();
        ZonedDateTime fromDateTime = ZonedDateTime.now().plusDays(5);

        logger.debug("Model:{}", model);

        CourseParticipantsForm courseParticipantsForm = new CourseParticipantsForm();
        CourseScheduleForm courseScheduleForm = new CourseScheduleForm();
        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();

        Set<CourseScheduleForm> setCourseScheduleForm = new HashSet<CourseScheduleForm>();
        Set<CourseScheduleDetailForm> setCourseScheduleDetailForm = new HashSet<CourseScheduleDetailForm>();
        Set<AttendanceParticipantDetail> setAttendanceParticipantDetail = new HashSet<AttendanceParticipantDetail>();

        Set<CourseSchedule> courseScheduleList = attendanceService.findAllScheduledCoursesByInstructor(fromDateTime,
                toDateTime, user.getId());
        for (CourseSchedule courseSchedule : courseScheduleList) {
            courseScheduleForm.setCourseName(courseSchedule.getCourseName());
            setCourseScheduleForm.add(courseScheduleForm);
        }

        Set<CourseParticipant> courseParticipantList = attendanceService.findCourseScheduleById(id);
        for (CourseParticipant courseParticipant : courseParticipantList) {
            courseParticipantsForm.setCourseName(courseParticipant.getCourseName());
            courseParticipantsForm.setInstructorName(courseParticipant.getInstructorName());

            courseScheduleDetailForm.setScheduledStartDateTime(courseParticipant.getScheduledStartDateTime());
            courseScheduleDetailForm.setScheduledEndDateTime(courseParticipant.getScheduledEndDateTime());
            setCourseScheduleDetailForm.add(courseScheduleDetailForm);

            Duration totalDuration = Duration.between(courseScheduleDetailForm.getScheduledStartDateTime(),
                    courseScheduleDetailForm.getScheduledEndDateTime());
            model.addAttribute("duration", totalDuration);
            
            attendanceParticipantDetail.setName(courseParticipant.getParticipantName());
            attendanceParticipantDetail.setEmail(courseParticipant.getEmail());
            attendanceParticipantDetail.setEmployeeNumber(courseParticipant.getEmployeeNumber());
            setAttendanceParticipantDetail.add(attendanceParticipantDetail);
        }
        courseParticipantsForm.setCourseScheduleDetails(setCourseScheduleDetailForm);
        courseParticipantsForm.setParticipants(setAttendanceParticipantDetail);
        courseParticipantsForm.setCourseSchedules(setCourseScheduleForm);
         
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
    @GetMapping("/participants")
    public String showChangeStatusParticipantsForm(@Valid ChangeStatusForm form, BindingResult bindingResult,
            Model model) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ZonedDateTime toDateTime = ZonedDateTime.now();
        ZonedDateTime fromDateTime = toDateTime.minusDays(5);
        ChangeStatusForm statusForm = new ChangeStatusForm();

        GenerateAttendanceForm genAttendForm = new GenerateAttendanceForm();

        attendanceService.findAllScheduledCoursesByInstructor(fromDateTime, toDateTime, user.getId());
        if (form.getId() != null || form.getId() != 0) {
            Set<CourseAttendance> courseAttendance = attendanceService
                    .findCourseAttendanceByCourseScheduleDetailId(form.getId());
            Set<ChangeStatusCourse> setCSCourse = new HashSet<ChangeStatusCourse>();
            Set<ChangeStatusParticipant> setSParticipant = new HashSet<ChangeStatusParticipant>();
            for (CourseAttendance cAttendance : courseAttendance) {
                ChangeStatusCourse cSCourse = new ChangeStatusCourse();
                ChangeStatusParticipant cSParticipant = new ChangeStatusParticipant();

                genAttendForm.setCourseName(cAttendance.getCourseName());
                cSCourse.setCourseName(cAttendance.getCourseName());
                setCSCourse.add(cSCourse);

                cSParticipant.setLoginDateTime(cAttendance.getLoginDateTime());
                cSParticipant.setName(cAttendance.getParticipantName());
                cSParticipant.setCourseAttendanceId(cAttendance.getId());
                cSParticipant.setParticipantId(cAttendance.getParticipantId());
                cSParticipant.setStatus(cAttendance.getStatus());
                setSParticipant.add(cSParticipant);
            }
            statusForm.setCourses(setCSCourse);
            statusForm.setParticipants(setSParticipant);
        }

        model.addAttribute("statusForm", statusForm);
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
    @PostMapping("/participants")
    public String submitChangeStatusParticipantsForm(@Valid ChangeStatusForm form, BindingResult bindingResult,
            Model model) {
        ZonedDateTime toDateTime = ZonedDateTime.now();
        ZonedDateTime fromDateTime = toDateTime.minusDays(5);
        attendanceService.findAllScheduledCoursesByInstructor(fromDateTime, toDateTime, 1L);

        if (form.getId() != null || form.getId() != 0) {
            attendanceService.findCourseAttendanceByCourseScheduleDetailId(form.getId());
        }

        if (bindingResult.hasErrors()) {
            return "attendance/changeAttendanceStatus";
        }

        Set<CourseAttendance> setCourseAttendance = new HashSet<CourseAttendance>();

        for (ChangeStatusParticipant changeStatParticipant : form.getParticipants()) {
            CourseAttendance courseAttendance = new CourseAttendance.Builder(form.getId(),
                    changeStatParticipant.getParticipantId()).build();
            setCourseAttendance.add(courseAttendance);
        }

        attendanceService.changeStatus(setCourseAttendance);
        return "redirect:/participants";
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
    public String showGenerateAttendanceForm(Long id, Model model) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ZonedDateTime toDate = ZonedDateTime.now();
        ZonedDateTime fromDate = ZonedDateTime.now().minusDays(5);

        logger.debug("Model:{}", model);
        GenerateAttendanceForm generateAttendanceForm = new GenerateAttendanceForm();
        GenerateAttendanceCourse generateAttendanceCourse = new GenerateAttendanceCourse();

        Set<GenerateAttendanceCourse> setGenerateAttendanceCourse = new HashSet<GenerateAttendanceCourse>();

        /*
         * <pre> Id is for authentication implementation <pre>
         */
        Set<CourseSchedule> courseScheduleList = attendanceService.findAllScheduledCoursesByInstructor(fromDate, toDate,
                user.getId());

        for (CourseSchedule courseSchedule : courseScheduleList) {
            generateAttendanceCourse.setCourseName(courseSchedule.getCourseName());// course schedule
            setGenerateAttendanceCourse.add(generateAttendanceCourse);

        }

        if (id != null && id != 0) {// id for course schedule line 54

            Set<CourseAttendance> courseAttendanceList = attendanceService
                    .findCourseAttendanceByCourseScheduleDetailId(id);

            Set<AttendanceParticipantDetail> setAttendanceParticipantDetail = new HashSet<AttendanceParticipantDetail>();

            for (CourseAttendance courseAttendance : courseAttendanceList) {
                generateAttendanceForm.setCourseName(courseAttendance.getCourseName());
                generateAttendanceForm.setInstructorName(courseAttendance.getInstructorName());
                generateAttendanceForm.setVenueName(courseAttendance.getVenueName());
                generateAttendanceForm.setScheduledStartDateTime(courseAttendance.getScheduleStartDateTime());
                generateAttendanceForm.setScheduledEndDateTime(courseAttendance.getScheduleEndDateTime());
                generateAttendanceForm.setDuration(courseAttendance.getDuration());

                AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();

                /*
                 * <pre> Email and EmployeeNumber is on Q.A.Does not exist Company name and
                 * Signature is on Q.A no. 11 and 14 in UI wireframe 画面レイアウト <pre>
                 */
                // attendanceParticipantDetail.setEmail(CourseAttendance.getEmail());
                // attendanceParticipantDetail.setEmployeeNumber(courseAttendance.getParticipantId());
                attendanceParticipantDetail.setLoginDateTime(courseAttendance.getLoginDateTime());
                attendanceParticipantDetail.setName(courseAttendance.getParticipantName());
                setAttendanceParticipantDetail.add(attendanceParticipantDetail);

            }

            generateAttendanceForm.setCourses(setGenerateAttendanceCourse);
            generateAttendanceForm.setParticipants(setAttendanceParticipantDetail);

        }

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
     * @return attendance/generateAbsentSheet
     * 
     */
    @GetMapping("/generate/{courseScheduleDetailId}/absent")
    public String showGenerateAbsentForm(Long id, Model model) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ZonedDateTime toDate = ZonedDateTime.now();
        ZonedDateTime fromDate = ZonedDateTime.now().minusDays(5);

        logger.debug("Model:{}", model);
        Set<CourseSchedule> courseList = attendanceService.findAllScheduledCoursesByInstructor(fromDate, toDate,
                user.getId());
        Set<GenerateAttendanceCourse> setGenerateAttendanceCourse = new HashSet<GenerateAttendanceCourse>();
        GenerateAttendanceCourse generateAttendanceCourse = new GenerateAttendanceCourse();

        for (CourseSchedule courseSchedule : courseList) {
            generateAttendanceCourse.setCourseName(courseSchedule.getCourseName());
            setGenerateAttendanceCourse.add(generateAttendanceCourse);
        }

        GenerateAttendanceForm generateAttendanceForm = new GenerateAttendanceForm();

        if (id != null && id != 0) {
            Set<CourseAttendance> attendanceList = attendanceService
                    .findCourseAbsentParticipantByCourseScheduleDetailId(id);
            Set<AttendanceParticipantDetail> setAttendanceParticipantDetail = new HashSet<AttendanceParticipantDetail>();

            for (CourseAttendance courseAttendance : attendanceList) {
                generateAttendanceForm.setCourseName(courseAttendance.getCourseName());
                generateAttendanceForm.setScheduledStartDateTime(courseAttendance.getScheduleStartDateTime());
                generateAttendanceForm.setScheduledEndDateTime(courseAttendance.getScheduleEndDateTime());
                generateAttendanceForm.setDuration(courseAttendance.getDuration());
                generateAttendanceForm.setVenueName(courseAttendance.getVenueName());
                generateAttendanceForm.setInstructorName(courseAttendance.getInstructorName());

                AttendanceParticipantDetail attendanceParticipantDetail = new AttendanceParticipantDetail();
                /*
                 * <pre> Email and EmployeeNumber variable does not exist and it is on Q.A.
                 * COMPANY/BU/DEPT and SIGNATURE is on Q.A no. 31 and 34 in UI wireframe
                 * (24_AttendanceForAbsent) </pre>
                 */
//                attendanceParticipantDetail.setEmployeeNumber(courseAttendance.getParticipantId());
//                attendanceParticipantDetail.setEmail(courseAttendance.getEmail());
                attendanceParticipantDetail.setName(courseAttendance.getParticipantName());
                attendanceParticipantDetail.setLoginDateTime(courseAttendance.getLoginDateTime());

                setAttendanceParticipantDetail.add(attendanceParticipantDetail);
            }
            generateAttendanceForm.setCourses(setGenerateAttendanceCourse);
            generateAttendanceForm.setParticipants(setAttendanceParticipantDetail);
        }

        model.addAttribute("generateAbsent", generateAttendanceForm);
        return "attendance/generateAbsentSheet";
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
     * 
     */
    @GetMapping("/signin/{courseScheduleDetailId}")
    public String showAttendanceForm(Long id, Model model) {

        logger.debug("Model:{}", model);
        CourseAttendanceForm courseAttendanceForm = new CourseAttendanceForm();

        Set<CourseAttendance> courseAttendanceList = attendanceService.findCourseScheduleDetailById(id);
        Set<CourseAttendance> setCourseAttendance = new HashSet<CourseAttendance>();

        for (CourseAttendance courseAttendance : courseAttendanceList) {
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
     * @return redirect:/attendance
     */
    @PostMapping("/signin/{courseScheduleDetailId}")
    public String submitAttendanceForm(Long id, CourseAttendanceForm form, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {

        logger.debug("CourseAttendanceForm: {}", form);
        logger.debug("Result: {}", bindingResult);

        if (bindingResult.hasErrors()) {
            return "attendance/attend";
        }

        model.addAttribute("attend", form);

        CourseAttendance courseAttendance = new CourseAttendance.Builder(form.getId(), form.getCourseScheduleDetailId(),
                form.getCourseName(), form.getInstructorName(), form.getVenueName(), form.getParticipantId(), "",
                form.getScheduledStartDateTime(), form.getScheduledEndDateTime(), form.getDuration(),
                form.getScheduledStartDateTime()).build();

        attendanceService.attend(courseAttendance);

        redirectAttributes.addFlashAttribute("attend", form);
        return "redirect:/attendance";

    }

}
