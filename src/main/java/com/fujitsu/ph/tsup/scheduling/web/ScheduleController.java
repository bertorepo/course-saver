package com.fujitsu.ph.tsup.scheduling.web;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/scheduling")
public class ScheduleController {
    private ScheduleService scheduleService;
    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @GetMapping("/new")
    public String showCourseScheduleNewForm(Model model) {

        Set<CourseForm> courseFormList = scheduleService.findAllCourses();
        Set<VenueForm> venueFormList = scheduleService.findAllVenues();
        Set<InstructorForm> instructorFormList = scheduleService.findAllInstructors();

        CourseScheduleNewForm courseScheduleNewForm = new CourseScheduleNewForm();

        courseScheduleNewForm.setInstructors(instructorFormList);
        courseScheduleNewForm.setVenues(venueFormList);
        courseScheduleNewForm.setCourses(courseFormList);

        model.addAttribute("scheduleNew", courseScheduleNewForm);

        return "scheduling/scheduleNew";

    }

    @GetMapping("/view")
    public String viewAllCourseSchedule(@Valid @ModelAttribute("scheduleView") CourseScheduleListForm form,
            BindingResult bindingResult, Model model) {

        logger.debug("CourseScheduleListForm: {}", form);
        logger.debug("Result: {}", bindingResult);

        if (bindingResult.hasErrors()) {
            return "scheduling-management/scheduleView";
        }

        CourseScheduleListForm courseScheduleListForm = new CourseScheduleListForm();

        courseScheduleListForm.setFromDateTime(ZonedDateTime.now());
        courseScheduleListForm.setToDateTime(ZonedDateTime.now().plusDays(5));

        if (courseScheduleListForm.getToDateTime() == null || courseScheduleListForm.getFromDateTime() == null) {

            if (courseScheduleListForm.getToDateTime().isBefore(courseScheduleListForm.getFromDateTime()))

            {
                model.addAttribute("scheduleView", courseScheduleListForm);
                return "scheduling/scheduleView";
            }

        }

        model.addAttribute("scheduleView", courseScheduleListForm);
        return "scheduling/scheduleView";

    }

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
            return "scheduling/scheduleNew";
        }

        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();

        model.addAttribute("scheduleNew", form);

        CourseScheduleDetail courseScheduleDetail = new CourseScheduleDetail.Builder(courseScheduleDetailForm.getId(),
                courseScheduleDetailForm.getScheduledStartDateTime(),
                courseScheduleDetailForm.getScheduledEndDateTime()).build();

        courseScheduleDetailSet.add(courseScheduleDetail);

        CourseSchedule courseSchedule = new CourseSchedule.Builder(form.getCourseId(), form.getInstructorId(),
                form.getVenueId(), form.getMinRequired(), courseScheduleDetailSet).build();
        scheduleService.createCourseSchedule(courseSchedule);

        redirectAttributes.addFlashAttribute("scheduleNew", form);
        return "redirect:/view";

    }

}
