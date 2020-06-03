package com.fujitsu.ph.tsup.course.management.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* @Author: Freo, Kristine Faith */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.management.model.CourseDelete;
import com.fujitsu.ph.tsup.course.management.web.CourseDeleteController;


@Controller
@RequestMapping("/Course")
public class CourseDeleteController {
	private static Logger logger = LoggerFactory.getLogger(CourseDeleteController.class);
	
	@GetMapping("/delete")
	public String show(Model model) {
		logger.debug("course:{}", model);
		if (model.containsAttribute("CourseDelete")) {
			return "course-management/CourseDelete";
		}
		CourseDelete course = new CourseDelete();
		 course.setCourseId(20202);
		 course.setCourseName("SDEM1");
		 course.setSearch("");
	
		model.addAttribute("courseDelete", course);
		return "course-management/courseDelete";
	}
	@PostMapping("/delete")
	public String submit(
			@Valid @ModelAttribute("CourseDelete") CourseDelete CourseDelete, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		logger.debug("CourseDelete:{}", CourseDelete);
		logger.debug("Result:{}", result);
		CourseDelete.setCourseId(20202);
		CourseDelete.setCourseName("SDEM1");
		
		model.addAttribute("CourseDelete", CourseDelete);
		if (result.hasErrors()) {
			return "course-management/CourseDelete";
		}
		redirectAttributes.addFlashAttribute("courseDelete", CourseDelete);
		return "redirect:/courseDelete";
	}
}
