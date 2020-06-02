package com.fujitsu.ph.tsup.employee.management.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.employee.management.model.EMPLOYEE_CREATE;

@Controller
@RequestMapping("/EmployeeCreate")
public class EMPLOYEE_CREATE_CONTROLLER {

	@GetMapping("/Register")
	public String show(Model model) {
		EMPLOYEE_CREATE employee = new EMPLOYEE_CREATE();
		employee.setID(1L);
		employee.setNUMBER("0000-000000");
		employee.setLAST_NAME("Surname");
		employee.setFIRST_NAME("Firstname");
		employee.setEMAIL_ADDRESS("email@email.com");
		employee.setUSERNAME("i.surname"); // "i" stands for First Name Initials
		
		model.addAttribute("employeeCreate", employee);
		return "employee-management/EMPLOYEE_CREATE_FORM";
}
}
//Mark
