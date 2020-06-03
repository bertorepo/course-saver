/* Author : Macabugao, Janella Marie */
package com.fujitsu.ph.tsup.employee.management.web;

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

import com.fujitsu.ph.tsup.employee.management.model.EmployeeView;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeViewController.class);

	@GetMapping("/view")
	public String viewEmployeeForm(Model model) {
		logger.debug("Model:{}", model);
		
		if (model.containsAttribute("employeeView")) {
			return "employee-management/viewEmployee";
		}
		EmployeeView employee = new EmployeeView();

		employee.setFirstName("Janella");
		employee.setLastName("Macabugao");
		employee.setEmailAddress("j.macabugao@fujitsu.com");
		employee.setUserName("Janella");

		model.addAttribute("employeeView", employee);

		return "employee-management/viewEmployee";

	}

	@PostMapping("/view")
	public String viewEmployeeSubmit(@Valid @ModelAttribute("employeeView")  
	EmployeeView employeeView, BindingResult bindingResult, Model model,
	RedirectAttributes redirectAttributes) {

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
		redirectAttributes.addFlashAttribute("employeeView", employeeView);
		return "redirect:/employees/view";

	}

}
