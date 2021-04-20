/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.course.model.CourseForm;
import com.fujitsu.ph.tsup.course.service.CourseManagementService;
import com.fujitsu.ph.tsup.roletype.domain.RoleType;


/**
 * CourseManagementController Class
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 *
 */
@Controller
@RequestMapping("/courses")
public class CourseManagementController {

    // Course Management Service class
    @Autowired
    CourseManagementService courseManagementService;

    @GetMapping("/load")
    public String load(Model model) {

        Set<Course> course = courseManagementService.findAllCourses();

        List<Course> listOfCourse = course.stream()
                .collect(Collectors.toList());

        model.addAttribute("courseList", listOfCourse);

        return "course-management/manageCourse";

    }

    /**
     * Method for getting course id to delete
     * 
     * @param id            Course Id
     * @param form          Course Form
     * @param bindingResult Binding Result
     * @param model         Model
     * @return View
     */
    @GetMapping("/{courseId}/delete")
    public String showDeleteCourseForm(@RequestParam(value = "courseIdInput") Long id,
            CourseForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "redirect:/courses/load?courseId=" + id + "#confirmModal";

        }

        //set value for Course object
        Course course = courseManagementService.findCourseById(id);

        //set value for CoruseForm object
        form.setId(course.getId());
        form.setName(course.getName());
        form.setDetail(course.getDetail());
        form.setIsMandatory(course.getIsMandatory());
        form.setDeadline(course.getDeadline());

        model.addAttribute("deleteCourseForm", form);

        return "redirect:/courses/load?courseId=" + id + "#confirmModal";
    }

    /**
     * Method for deleting course with the given id
     * 
     * @param id                 Course id
     * @param redirectAttributes RedirectAttributes
     * @param model              Model
     * @return View
     */
    @PostMapping("/{courseId}/delete")
    public String submitDeleteCourseForm(@PathVariable("courseId") Long id,
            RedirectAttributes redirectAttributes, Model model) {

        //Call deleteCourseById() method.
        courseManagementService.deleteCourseById(id);

        redirectAttributes.addFlashAttribute("deleteSuccessMessage",
                "You have successfully delete this course");

        return "redirect:/courses/load#successModal";
    }
    
    @PostMapping("/search")
    public String submitSearchCourseForm(@RequestParam(name = "searchCourseName") String searchCourseName, Model model) {
    	
    	if(searchCourseName.isEmpty()) {
    		return "redirect:/courses/load";
    	}
    	
    	Set<Course> course = courseManagementService.findCoursesByName(searchCourseName);
    	
    	List<Course> listOfCourse = course.stream()
                .collect(Collectors.toList());
    	
    	model.addAttribute("courseList", listOfCourse);
    	
    	return "course-management/manageCourse";
    }
    
    /**
     * Author: WS)C.Arias
     * Updated: WS)I.Fajardo
     * <pre>
     * Create the course. Method = GET
     * 
     * <pre>
     * @return course Form and view
     */
    @GetMapping("/create")
    public String showCreateCourseForm(Model model) {
    	Set<Course> course = courseManagementService.loadAllCourse();
    	List<Course> courseList = course.stream().collect(Collectors.toList());
    	model.addAttribute("courseList", courseList);
    	model.addAttribute("create");
    	
    	return "course-management/courseCreate";
    	
    }
    
    /**
     * Author: WS)C.Arias
 	 * Updated: WS)I.Fajardo
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
    public String submitCreateCourseForm(CourseForm form, BindingResult bindingResult, Model model) {
			
	    	//remove irregular spaces
	        String cName = form.getName().replaceAll("\\s+", " ");
	        
	        Set<Course> course = courseManagementService.loadAllCourse();
	        List<Course> courseList = course.stream().collect(Collectors.toList());
	        model.addAttribute("courseList", courseList);
	        Set<Course> courseSize = courseManagementService.findCoursesByName(form.getName().toLowerCase());
	        
    		if(courseSize == null) {
    			Course courseDetails = new Course.Builder(cName.trim(),form.getDetail(),form.getIsMandatory(),form.getDeadline()).build();
    			courseManagementService.createCourse(courseDetails);
    		} else {
    			model.addAttribute("successMessage", "The course is already existing.");
    		}
    		  
        	return "course-management/courseCreate";
        	
    }
    
}
