package com.fujitsu.ph.tsup.employee.management.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

import com.fujitsu.ph.tsup.employee.management.model.EmployeeListForm;

@Controller
@RequestMapping("/employees")
public class EmployeeListController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeListController.class);

	private List<EmployeeListForm> employee;

	@GetMapping()
	public String EmployeeListForm(Model model) {
		logger.debug("Model:{}", model);

		if (model.containsAttribute("employeeList")) {
			return "employee-management/employeeList";
		}
		EmployeeListForm search = new EmployeeListForm();
		search.setSearch("");

		EmployeeListForm emp1 = new EmployeeListForm();
		int id = 1;
		int employeeId = 9190022;

		emp1.setId(id++);
		emp1.setEmpNumber(employeeId++);
		emp1.setFirstName("Juan");
		emp1.setLastName("Dela Cruz");
		emp1.setEmailAddress("jDelacruz@gmail.com");
		emp1.setUserName("ph_One");

		EmployeeListForm emp2 = new EmployeeListForm();
		emp2.setId(id++);
		emp2.setEmpNumber(employeeId++);
		emp2.setFirstName("Jose");
		emp2.setLastName("Rizal");
		emp2.setEmailAddress("jRizal@gmail.com");
		emp2.setUserName("ph_Jose");

		employee = new ArrayList<>();

		employee.add(emp1);
		employee.add(emp2);

		model.addAttribute("employee", employee);

		return "employee-management/employeeList";
	}

	@PostMapping("")
	public String submit(@Valid @ModelAttribute("employeeList") EmployeeListForm employeeList, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		logger.debug("Venue List:{}", employeeList);
		logger.debug("Result:{}", result);

		model.addAttribute("venueList", employeeList);
		if (result.hasErrors()) {
			return "employee-management/employeeList";
		}

		redirectAttributes.addFlashAttribute("venueList", employeeList);
		return "redirect:/empooyees";

	}

}
