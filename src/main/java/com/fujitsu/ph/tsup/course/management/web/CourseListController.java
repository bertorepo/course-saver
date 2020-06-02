package com.fujitsu.ph.tsup.course.management.web;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.management.model.CourseListForm;
import com.fujitsu.ph.tsup.course.management.model.CourseNames;

@Controller
@RequestMapping("/course")
public class CourseListController {
	@GetMapping()
	public String show(Model model) {
		CourseListForm course = new CourseListForm();
		Set<CourseNames> cns = new HashSet<>();
		
		CourseNames cn1 = new CourseNames();
		cn1.setId(123456789);
		cn1.setName("Information Technology");
		cns.add(cn1);
		
		CourseNames cn2 = new CourseNames();
		cn2.setId(987654321);
		cn2.setName("Computer Science");
		cns.add(cn2);
		
		CourseNames cn3 = new CourseNames();
		cn3.setId(987632451);
		cn3.setName("Information Technology DA");
		cns.add(cn3);
		
		course.setCNs(cns);
		model.addAttribute("courseList", course);
		
		return "course-management/courseList";
}
}
