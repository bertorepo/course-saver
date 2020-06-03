/* Author : Angara, Mary Rose */
package com.fujitsu.ph.tsup.course.management.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.management.model.CourseListForm;
import com.fujitsu.ph.tsup.course.management.model.CourseNames;

@Controller
@RequestMapping("/course")
public class CourseListController {
	
	private static Logger logger = LoggerFactory.getLogger(CourseListController.class);
	
	@GetMapping("/new")
	public String show(Model model) {
		CourseListForm course = new CourseListForm();
		course.setSearch("");
		course.setCNs(createCNs());
		 
		model.addAttribute("courseList", course);
		return "course-management/courseList";
	}
	
	@PostMapping("/new")
	public String submit(@Valid CourseListForm courseList, BindingResult result, Model model) {
		
		logger.debug("Course List:{}", courseList);
		logger.debug("Result:{}", result);
		
		courseList.setCNs(createCNs());

		model.addAttribute("courseList", courseList);
		if (result.hasErrors()) {
			return "course-management/courseList";
		}
		return "course-management/courseList";
		
	}
	
		private Set<CourseNames> createCNs() {
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
		
		CourseNames cn4 = new CourseNames();
		cn4.setId(753951852);
		cn4.setName("Computer Engineering");
		cns.add(cn4);
		
		return cns;
}
}
