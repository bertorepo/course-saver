//Michael Rivera

package com.fujitsu.ph.tsup.course.management.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.course.management.model.CourseCreate;

@Controller
@RequestMapping("/courses")
public class CourseControllerCreate {
	private static Logger logger = LoggerFactory.getLogger(CourseControllerCreate.class);
	
	@GetMapping("/new")
	public String show(Model model) {
		logger.debug("Model:{}", model);

        if (model.containsAttribute("course")) {
            return "course-management/courseCreate";
        }
		
		CourseCreate course = new CourseCreate();
		course.setId(123456);
		course.setName("Course 1");
		
		
		model.addAttribute("course", course);
		
		return "course-management/courseCreate";
		
	}
	
	@PostMapping("/new")
    public String submit(@Valid @ModelAttribute("course") CourseCreate course, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        logger.debug("Course:{}", course);
        logger.debug("Result:{}", result);

        model.addAttribute("course", course);
        if (result.hasErrors()) {
            return "course-management/courseCreate";
        }

        redirectAttributes.addFlashAttribute("course", course);
        return "redirect:/courses/new";
    }
	
	
	
}
