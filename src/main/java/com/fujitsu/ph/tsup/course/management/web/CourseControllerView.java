package com.fujitsu.ph.tsup.course.management.web;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fujitsu.ph.tsup.course.management.model.CourseViewForm;

@Controller
@RequestMapping("/courses")
public class CourseControllerView {
	private static Logger logger = LoggerFactory.getLogger(CourseControllerView.class);
	
	@GetMapping("/view")
	public String show(Model model) {
		CourseViewForm courses = new CourseViewForm();
		courses.setCourseid("12345678");
		courses.setCoursename("JAVA Programming");
		
		model.addAttribute("courses", courses);
		logger.info("Mode:{}", model);
		return "course-management/coursesForm";
		
	}
	
	@PostMapping("/view")
	public String submit(@Valid CourseViewForm courses, BindingResult result, Model model) {
		
		logger.debug("Courses:{}", courses);
		logger.debug("Result:{}", result);
		
		
		if (result.hasErrors()) {
			model.addAttribute("coursesForm1", courses);
			return "course-management/coursesForm";
		}
		model.addAttribute("coursesForm1", courses);
		return "course-management/coursesForm";

	}

}

