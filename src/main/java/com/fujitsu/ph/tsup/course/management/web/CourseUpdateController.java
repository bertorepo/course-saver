//@Author Thomas Oviedo
package com.fujitsu.ph.tsup.course.management.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.management.model.CourseUpdateForm;

@Controller
@RequestMapping("/courses")
public class CourseUpdateController {

	@GetMapping()
	public String show(Model model) {
		CourseUpdateForm course = new CourseUpdateForm();
		course.setId(123456);
		course.setName("Course 1");

		model.addAttribute("course", course);
		return "course-management/CourseUpdateForm";
	}

	@PostMapping("/update")
	public String submit(@Valid @ModelAttribute("course")CourseUpdateForm course, BindingResult result, Model model) {
		/*logger.debug("course:{}", course);
		logger.debug("Result:{}", result);
		logger.debug("Model:{}", model);*/
		model.addAttribute("course", course);
		if (result.hasErrors()) {
			return "course-management/CourseUpdateForm";
		}
		return "course-management/CourseUpdateForm";

	}

}
