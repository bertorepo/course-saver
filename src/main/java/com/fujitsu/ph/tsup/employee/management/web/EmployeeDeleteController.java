package com.fujitsu.ph.tsup.employee.management.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.employee.management.model.EmployeeForm;

@Controller
@RequestMapping("/employees")
public class EmployeeDeleteController {
	@GetMapping("/delete")
	public String show(Model model) {
		EmployeeForm employee = new EmployeeForm();
		employee.setFirstName("JAY IAN");
		employee.setLastName("MACABUDBUD");
		employee.setEmployeeNumber("A10231558");
		employee.setEmailAddress("j.macabudbud@fujitsu.com");
		employee.setUsername("j.macabudbud");
		
		model.addAttribute("employee", employee);
		return "employee-management/delete";
		
	}
}
