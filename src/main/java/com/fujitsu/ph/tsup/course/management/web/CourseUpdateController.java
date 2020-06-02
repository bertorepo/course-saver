package com.fujitsu.ph.tsup.course.management.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
