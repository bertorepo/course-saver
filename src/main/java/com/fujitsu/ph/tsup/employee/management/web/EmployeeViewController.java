/* Author : Macabugao, Janella Marie */
package com.fujitsu.ph.tsup.employee.management.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.employee.management.model.EmployeeViewForm;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeViewController.class);

	@GetMapping("/view")
	public String viewEmployeeForm(Model model) {

		EmployeeViewForm employee = new EmployeeViewForm();

		employee.setFirstName("Janella");
		employee.setLastName("Macabugao");
		employee.setEmailAddress("j.macabugao@fujitsu.com");
		employee.setUserName("Janella");

		model.addAttribute("employeeView", employee);

		return "employee-management/viewEmployee";

	}

	@PostMapping("/view")
	public String viewEmployeeSubmit(@Valid EmployeeViewForm employeeView, BindingResult bindingResult, Model model) {

		logger.debug("EmployeeView:{}", employeeView);
		logger.debug("Result:{}", bindingResult);

		employeeView.setFirstName("Janella");
		employeeView.setLastName("Macabugao");
		employeeView.setEmailAddress("j.macabugao@fujitsu.com");
		employeeView.setUserName("Janella");
		model.addAttribute("employeeView", employeeView);
		if (bindingResult.hasErrors()) {
			return "employee-management/viewEmployee";

		}

		return "redirect:/employees/view";

	}

}

