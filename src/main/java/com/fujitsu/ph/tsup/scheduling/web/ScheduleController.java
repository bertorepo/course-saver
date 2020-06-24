package com.fujitsu.ph.tsup.scheduling.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleNewForm;
import com.fujitsu.ph.tsup.scheduling.service.ScheduleService;

@Controller
@RequestMapping("/scheduling")
public class ScheduleController {
    private ScheduleService scheduleService;
    private static Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    
    @PostMapping("/new")
    public String createCourseSchedule(@Valid @ModelAttribute("scheduleNew") CourseScheduleNewForm form, 
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        
        logger.debug("CourseScheduleNewForm: {}", form);
        logger.debug("Result: {}", bindingResult);
        
        if(bindingResult.hasErrors()) {
            return "scheduling-management/scheduleNew";
        }
        
        CourseScheduleDetailForm courseScheduleDetailForm = new CourseScheduleDetailForm();
        Set<CourseScheduleDetail> courseScheduleDetailSet = new HashSet<>();
        
        
        model.addAttribute("scheduleNew", form);
        
        CourseScheduleDetail courseScheduleDetail = 
                new CourseScheduleDetail.Builder(courseScheduleDetailForm.getId(), 
                        courseScheduleDetailForm.getScheduledStartDateTime(), 
                        courseScheduleDetailForm.getScheduledEndDateTime()).build();
      
        courseScheduleDetailSet.add(courseScheduleDetail);
        
        CourseSchedule courseSchedule = new CourseSchedule.Builder(form.getCourseId(), form.getInstructorId(),
                form.getVenueId(), form.getMinRequired(), courseScheduleDetailSet).build();
        scheduleService.createCourseSchedule(courseSchedule);
        
        redirectAttributes.addFlashAttribute("scheduleNew", form);
        return "redirect:/scheduling";
        
        
    }
    
    
}
