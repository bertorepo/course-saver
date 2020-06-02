package com.fujitsu.ph.tsup.course.management.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.management.model.CourseDelete;

@Controller
@RequestMapping("/Course")
public class CourseDeleteController {

	@GetMapping("/delete")
	public String show(Model model) {
		CourseDelete course = new CourseDelete();
		 course.setCourseId(20202);
		 course.setCourseName("SDEM1");
	
		model.addAttribute("courseDelete", course);
		return "course-management/courseDelete";
	}
}
