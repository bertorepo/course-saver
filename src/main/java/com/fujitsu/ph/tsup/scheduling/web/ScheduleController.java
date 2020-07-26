package com.fujitsu.ph.tsup.scheduling.web;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: ScheduleController.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Macabugao | New Creation
//0.01    | 06/26/2020 | WS) JC.Jimenez  | New Creation
//0.01    | 06/26/2020 | WS) J.Balanon   | New Creation
//=======================================================

/**
* <pre>
* The controller for course scheduling. 
* <pre>
* @version 0.01
* @author jc.jimenez
* @author j.balanon
* @author j.macabugao
*
*/
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleListForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleNewForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleViewForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    /**
     * Schedule Service 
     */
    @Autowired
    private ScheduleService scheduleService;

    
    /**
     * Logger Factory
     */
    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    /**
     * <pre>
     * View all course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param CourseScheduleListForm form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @return courseScheduleListForm and view
     */
    @GetMapping("/view")
    public String viewAllCourseSchedule(
            @Valid @ModelAttribute("scheduleView") CourseScheduleListForm courseScheduleListForm,
            BindingResult bindingResult, Model model) {

        logger.debug("CourseScheduleListForm: {}", courseScheduleListForm);
        logger.debug("Result: {}", bindingResult);

        if (bindingResult.hasErrors()) {
            return "scheduling/scheduleView";
        }
        
        if (courseScheduleListForm.getFromDateTime() == null) {
//                  form.setFromDateTime( ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-01 08:30:00").toInstant(),ZoneId.of("UTC")));
            courseScheduleListForm.setFromDateTime(ZonedDateTime.now().minusMonths(1));
                }
                if (courseScheduleListForm.getToDateTime() == null) {
//                  form.setToDateTime( ZonedDateTime.ofInstant(Timestamp.valueOf("2020-07-10 08:30:00").toInstant(),ZoneId.of("UTC")));
                    courseScheduleListForm.setToDateTime(ZonedDateTime.now().plusDays(5));
                }

      /*  if (courseScheduleListForm.getToDateTime() == null || courseScheduleListForm.getFromDateTime() == null) {
            courseScheduleListForm.setFromDateTime(ZonedDateTime.now());
            courseScheduleListForm.setToDateTime(ZonedDateTime.now().plusDays(5));
        }

        if (courseScheduleListForm.getToDateTime().isBefore(courseScheduleListForm.getFromDateTime())) {
            model.addAttribute("scheduleView", courseScheduleListForm);
            model.addAttribute("error", "To Date should be greater than or equal to From Date");
            return "scheduling/scheduleView";
        } */

        Set<CourseSchedule> courseSchedule = scheduleService.findAllScheduledCourses(
                courseScheduleListForm.getFromDateTime(), courseScheduleListForm.getToDateTime());

        Set<CourseScheduleViewForm> courseScheduleViewFormSet = courseScheduleListForm.getCourseSchedules();

        for (CourseSchedule courseSched : courseSchedule) {
            CourseScheduleViewForm courseScheduleViewForm = new CourseScheduleViewForm();

            courseScheduleViewForm.setId(courseSched.getId());
            courseScheduleViewForm.setCourseId(courseSched.getCourseId());
            courseScheduleViewForm.setCourseName(courseSched.getCourseName());
            courseScheduleViewForm.setInstructorId(courseSched.getInstructorId());
            courseScheduleViewForm.setInstructorName(
                    courseSched.getInstructorLastName() + ", " + courseSched.getInstructorFirstName());

            Set<CourseScheduleDetail> courseSchedDetSet = courseSched.getCourseScheduleDetail();
            Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = new HashSet<>();

            for (CourseScheduleDetail courseScheduleDetail : courseSchedDetSet) {
                CourseScheduleDetailForm courseSchedDetailForm = new CourseScheduleDetailForm();

                courseSchedDetailForm.setScheduledStartDateTime(courseScheduleDetail.getScheduledStartDateTime());
                courseSchedDetailForm.setScheduledEndDateTime(courseScheduleDetail.getScheduledEndDateTime());
                courseSchedDetailForm.setDuration(courseScheduleDetail.getDuration());

                courseScheduleDetailFormSet.add(courseSchedDetailForm);

            }

            courseScheduleViewForm.setCourseScheduleDetails(courseScheduleDetailFormSet);

            courseScheduleViewFormSet.add(courseScheduleViewForm);
        }

        model.addAttribute("scheduleView", courseScheduleListForm);
        return "scheduling/instructorCourseScheduleList";
    }

    /**
     * <pre>
     * Show the Course Schedule New Form. Method = GET
     * 
     * <pre>
     * 
     * @param Model model
     * @return courseScheduleListForm and view
     */
    @GetMapping("/new")
    public String showCourseScheduleNewForm(Model model) {

        logger.debug("Model:{}", model);
        
        if (model.containsAttribute("scheduleNew")) {
            return "scheduling/createSched";
        }

        Set<CourseForm> courseFormList = scheduleService.findAllCourses();
        Set<VenueForm> venueFormList = scheduleService.findAllVenues();
        Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();

        CourseScheduleNewForm courseScheduleNewForm = new CourseScheduleNewForm();
        
        

        courseScheduleNewForm.setInstructors(instructorFormList);
        courseScheduleNewForm.setVenues(venueFormList);
        courseScheduleNewForm.setCourses(courseFormList);

        model.addAttribute("scheduleNew", courseScheduleNewForm);

        return "scheduling/createSched";

    }

    /**
     * <pre>
     * Create the course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param CourseScheduleListForm form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @param RedirectAttributes     redirectAttributes
     * @return courseScheduleListForm and view
     */
    @PostMapping("/new")
    public String submitCourseScheduleNewForm(@Valid @ModelAttribute("scheduleNew") CourseScheduleNewForm form,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        Set<CourseForm> courseFormList = scheduleService.findAllCourses();
        Set<VenueForm> venueFormList = scheduleService.findAllVenues();
        Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();

        logger.debug("CourseScheduleNewForm: {}", form);
        logger.debug("Result: {}", bindingResult);

        if (bindingResult.hasErrors()) {
            form.setCourses(courseFormList);
            form.setVenues(venueFormList);
            form.setInstructors(instructorFormList);
            model.addAttribute("scheduleNew", form);
            return "scheduling/createSched";
        }

        Set<CourseScheduleDetailForm> courseScheduleDetailFormSet = form.getCourseScheduleDetails();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();

        for (CourseScheduleDetailForm courseSchedDetForm : courseScheduleDetailFormSet) {
            CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(1L,
                    courseSchedDetForm.getScheduledStartDateTime(), courseSchedDetForm.getScheduledEndDateTime())
                            .build();
            courseScheduleDetailSet.add(courseScheduleDetail);
        }

        CourseSchedule courseSchedule = new CourseSchedule.Builder(form.getCourseId(), form.getInstructorId(),
                form.getVenueId(), form.getMinRequired(), courseScheduleDetailSet).maxAllowed(form.getMaxAllowed())
                        .build();
        
        scheduleService.createCourseSchedule(courseSchedule);

        redirectAttributes.addFlashAttribute("scheduleNew", form);
        return "redirect:/schedules/new";

    }

}
