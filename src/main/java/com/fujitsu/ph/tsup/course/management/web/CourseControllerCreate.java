package com.fujitsu.ph.tsup.course.management.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.management.model.CourseCreate;

@Controller
@RequestMapping("/courses")
public class CourseControllerCreate {
	
	@GetMapping("/new")
	public String show(Model model) {
		CourseCreate course = new CourseCreate();
		course.setId(123456);
		course.setName("Course 1");
		
		
		return "course-management/courseCreate";
		
	}
}
