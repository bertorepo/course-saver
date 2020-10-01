package com.fujitsu.ph.tsup.coursemanagement.web;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.coursemanagement.model.Course;
import com.fujitsu.ph.tsup.coursemanagement.service.CourseManagementService;
import com.fujitsu.ph.tsup.coursemanagement.model.CourseForm;

@Controller
@RequestMapping("/courses")
public class CourseManagementController {

    @Autowired
    CourseManagementService courseManagementService;

    @GetMapping("/load")
    public String load() {

        return "course-management/courseDelete";

    }

    @GetMapping("/courses/{courseId}/delete")
    public String showDeleteCourseForm(@PathVariable("courseId") Long id,
            CourseForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "course-management/courseDelete";

        }

        Course course = courseManagementService.findCourseById(id);

        form.setId(course.getId());
        form.setName(course.getName());

        model.addAttribute("deleteCourse", form);

        return "course-management/courseDelete";

    }

    @DeleteMapping("/courses/{courseId}/delete")
    public String submitDeleteCourseForm(@PathVariable("courseId") Long id,
            RedirectAttributes redirectAttributes, Model model) {

        courseManagementService.deleteCourseById(id);

        redirectAttributes.addFlashAttribute("deleteSuccessMessage",
                "You have successfully delete this course");
//        try {
//
//        } catch (Exception e) {
//
//        }
        return "redirect:/course-management/courseDelete";
    }
    
    /**
     * Author: WS)C.Arias
     * <pre>
     * Create the course. Method = GET
     * 
     * <pre>
     * @return course Form and view
     */
    @GetMapping("/create")
    public String showCreateCourseForm(Model model) {
    	
    	model.addAttribute("create");
    	
    	return "course-management/courseCreate";
    	
    }
    
    /**
     * Author: WS)C.Arias
     * <pre>
     * Create the course. Method = POST
     * 
     * <pre>
     * 
     * @param CourseForm form
     * @param BindingResult bindingResult
     * @return course Form and view
     */
    @PostMapping("/create")
    public String submitCreateCourseForm(CourseForm form, BindingResult bindingResult,
    		Model model) {
		
    		Set<Course> courseSize = courseManagementService.findCourseByName(form.getName());
    		if(courseSize == null) {
    			Course courseDetails = new Course.Builder(form.getName(),form.getDetail()).build();
    			courseManagementService.createCourse(courseDetails);
    		} else {
    			model.addAttribute("successMessage", "The course is already existing.");
    		}
    		  
        	return "course-management/courseCreate";
    	
}
