package com.fujitsu.ph.tsup.employee.management.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

import com.fujitsu.ph.tsup.employee.management.model.EmployeeForm;

@Controller
@RequestMapping("/employees")
public class EmployeeDeleteController {
	private static Logger logger = LoggerFactory.getLogger(EmployeeDeleteController.class);
	
	@GetMapping("/delete")
	public String show(Model model) {
		logger.debug("Model:{}", model);
		
		if (model.containsAttribute("employee")) {
			return "employee-management/delete";
		}
		
		EmployeeForm employee = new EmployeeForm();
		employee.setId(123456);
		employee.setFirstName("JAY IAN");
		employee.setLastName("MACABUDBUD");
		employee.setEmployeeNumber("A12345");
		employee.setEmailAddress("j.macabudbud@fujitsu.com");
		employee.setUsername("j.macabudbud");
		
		model.addAttribute("employee", employee);
		return "employee-management/delete";
		
	}
	
	@PostMapping("/delete")
	public String submit(
			@Valid @ModelAttribute("employee") EmployeeForm employee,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		logger.debug("Employee:{}", employee);
		logger.debug("Result:{}", result);
		
		model.addAttribute("employee", employee);
		if (result.hasErrors()) {
			return "employee-management/delete";
		}
		
		redirectAttributes.addFlashAttribute("employee", employee);
		return "redirect:/employees/delete";
	}
}
