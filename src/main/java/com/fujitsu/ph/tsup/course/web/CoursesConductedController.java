package com.fujitsu.ph.tsup.course.web;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.model.CourseForm;
import com.fujitsu.ph.tsup.course.model.CoursesConducted;
import com.fujitsu.ph.tsup.course.model.CoursesConductedForm;
import com.fujitsu.ph.tsup.course.model.CoursesConductedListForm;
import com.fujitsu.ph.tsup.course.service.CoursesConductedService;


/**
 * <pre>
 * The controller for the courses conducted
 * 
 * <pre>
 * 
 * @version 0.01
 * @author m.rivera
 */

@Controller
@RequestMapping("/report/")
public class CoursesConductedController {

    /**
     * Courses Conducted Service 
     */
    @Autowired
    private CoursesConductedService coursesConductedService;

    /**
     * Logger Factory
     */
    private static Logger logger = LoggerFactory.getLogger(CoursesConductedController.class);
    
    /**
     * <pre>
     * View all course schedule. Method = GET
     * 
     * <pre>
     * 
     * @param courseConductedForm    form
     * @param BindingResult          bindingResult
     * @param Model                  model
     * @return courseConductedListForm and view
     */    
    
    @GetMapping("/course")
	public String viewCoursesConducted(@Valid @ModelAttribute("coursesConducted") CoursesConductedListForm coursesConductedListForm, BindingResult bindingResult,Model model){
	    
        logger.debug("CourseConductedListForm: {}", coursesConductedListForm);
        logger.debug("Result: {}", bindingResult);
        

        if (bindingResult.hasErrors()) {
            return "course-management/generateReports";
        }
		
        if (coursesConductedListForm.getScheduledStartDateTime() == null || coursesConductedListForm.getScheduledEndDateTime() == null) {
        	coursesConductedListForm.setScheduledStartDateTime(ZonedDateTime.now().withHour(0).withMinute(0));
        	coursesConductedListForm.setScheduledEndDateTime(ZonedDateTime.now().plusDays(5));
        }
        Set<CoursesConducted> courseConducted = coursesConductedService.findAllCoursesConducted(coursesConductedListForm.getScheduledStartDateTime(), 
        		coursesConductedListForm.getScheduledEndDateTime());
        
        Set<CoursesConductedForm> coursesConductedFormSet = new HashSet<>();
        
        for(CoursesConducted coursesConducted :  courseConducted) {
			 CoursesConductedForm coursesConductedForm = new CoursesConductedForm();
			 coursesConductedForm.setId(coursesConducted.getId());
			 coursesConductedForm.setCourseName(coursesConducted.getCourseName());
			 coursesConductedForm.setScheduledStartDateTime(coursesConducted.getScheduledStartDateTime());
			 coursesConductedForm.setScheduledEndDateTime(coursesConducted.getRescheduledStartDateTime());
			 
			 coursesConductedFormSet.add(coursesConductedForm);
		 }
        
        coursesConductedListForm.setCoursesConductedSet(coursesConductedFormSet);
        
        
        model.addAttribute("coursesConductedForm", coursesConductedListForm);
        model.addAttribute("coursesConducted", coursesConductedFormSet);
        
	   return "course-management/generateReports";
	}
    
    @GetMapping("/load")
    public String viewSummaryCourseConducted() {

    	return "course-management/generateReports";
    }

}
