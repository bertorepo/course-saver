/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.category.service.CourseCategoryManagementService;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.course.model.CourseForm;
import com.fujitsu.ph.tsup.course.service.CourseManagementService;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseManagementController.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten       | Initial Version
//0.02    | 2021/04/20 | WS) i.fajardo       | Updated
//0.03    | 2021/05/10 | WS) D.Escala        | Updated
//==================================================================================================

@Controller
@RequestMapping("/courses")
public class CourseManagementController {
    private static Logger LOGGER = LoggerFactory.getLogger(CourseManagementController.class);
    
    // Course Management Service class
    @Autowired
    CourseManagementService courseManagementService;
    
    @Autowired
    CourseCategoryManagementService courseCategoryManagementService;

    @GetMapping("/load")
    public String load(Model model) {

        Set<Course> course = courseManagementService.findAllCourses();

	List<Course> listOfCourse = course.stream()
					  .sorted(Comparator.comparing(Course::getName))
					  .collect(Collectors.toList());
        
        model.addAttribute("courseList", listOfCourse);
        
	List<CourseCategory> courseCategoryList = courseCategoryManagementService.findAllCourseCategory()
										 .stream()
										 .collect(Collectors.toList());
    	model.addAttribute("courseCategory",courseCategoryList);
        
        model.addAttribute("course", new CourseForm());

        return "course-management/manageCourse";

    }

    @GetMapping("/update")
    public String showUpdateCourseForm(@ModelAttribute CourseForm course,
	    RedirectAttributes redirectAttributes) {
	if (Objects.isNull(course.getDeadline()) || course.getDeadline().equals("Nan")) {
	    course.setDeadline("-");
	}

	redirectAttributes.addFlashAttribute("updateCourse", course);

	return "redirect:/courses/load#updateConfirmModal";
    }
    
    @PostMapping("/update")
    public String updateCourseForm(@ModelAttribute CourseForm courseForUpdate, RedirectAttributes redirectAttributes) {
	Course updatedCourse = Course.builder()
				     .withId(courseForUpdate.getId())
				     .withCourseCategoryId(courseForUpdate.getCourseCategoryId())
				     .withName(courseForUpdate.getName())
				     .withDetail(courseForUpdate.getDetail())
				     .withIsMandatory(courseForUpdate.getIsMandatory())
				     .withDeadline(courseForUpdate.getDeadline())
				     .build();

	courseManagementService.updateCourse(updatedCourse);
        redirectAttributes.addFlashAttribute("deleteSuccessMessage",
                "You have successfully update this course "+ courseForUpdate.getName());
	
	return "redirect:/courses/load#successModal";
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
    	
    	String searchName = searchCourseName.trim();
    	
    	if(searchName.isEmpty()) {
    		return "redirect:/courses/load";
    	}
    	
    	try {
	    List<Course> listOfCourse = Optional.ofNullable(courseManagementService.findCoursesByName(searchName))
						.orElse(Collections.emptySet())
						.stream()
						.collect(Collectors.toList());
	    
	    model.addAttribute("courseList", listOfCourse);
	    model.addAttribute("course", new CourseForm());
	} catch (NullPointerException e) {
	    return "course-management/manageCourse";
	}
    	
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
    	Set<CourseCategory> courseCategory = courseCategoryManagementService.findAllCourseCategory();
    	List<CourseCategory> courseCategoryList = courseCategory.stream().collect(Collectors.toList());
    	model.addAttribute("courseList", courseList);
    	model.addAttribute("create");
    	model.addAttribute("courseCategory",courseCategoryList);
    	
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
    public String submitCreateCourseForm(CourseForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
			
	    	//remove irregular spaces
	        String cName = form.getName().replaceAll("\\s+", " ");
	        
	        Set<Course> course = courseManagementService.loadAllCourse();
	        List<Course> courseList = course.stream().collect(Collectors.toList());
	        model.addAttribute("courseList", courseList);
	        
	        //check if course and course category matches in the list
	        for(Course courseContains : courseList)	{
	        	 
	        	if(courseContains.getName().equals(form.getName()) && 
	        		courseContains.getCourseCategoryId() == form.getCourseCategoryId()) {
	        		redirectAttributes.addFlashAttribute("ErrorModal", 1);
	        		return "redirect:/courses/create";
	        	}
	        }
	        //proceed with creating course
		Course courseDetails = Course.builder()
			     .withName(cName.trim())
					     .withDetail(form.getDetail())
					     .withIsMandatory(form.getIsMandatory())
					     .withDeadline(form.getDeadline())
					     .withCourseCategoryId(form.getCourseCategoryId())
					     .build();
		
		
    		courseManagementService.createCourse(courseDetails);
    		redirectAttributes.addFlashAttribute("ErrorModal", 2);

    		return "redirect:/courses/create";
    }
    
}
