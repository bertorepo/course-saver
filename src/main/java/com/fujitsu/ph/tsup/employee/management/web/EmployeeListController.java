package com.fujitsu.ph.tsup.employee.management.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.employee.management.model.EmployeeListForm;

@Controller
@RequestMapping("/employees")
public class EmployeeListController {

	
	@GetMapping()
	public String EmployeeListForm(Model model) {
		
		model.addAttribute("employee", employee);
		
		return "employee-management/employeeList";
	}
	
	private List<EmployeeListForm> employee;
	@PostConstruct
	private void loadData() {
		
		// create employees
		EmployeeListForm emp1 = new EmployeeListForm();
		int id=1;
		int employeeId=123456;
		
		emp1.setId(id++);
		emp1.setEmpNumber(employeeId++);
		emp1.setFirstName("Juan");
		emp1.setLastName("Dela Cruz");
		emp1.setEmailAddress("jDelacruz@gmail.com");
		emp1.setUserName("One");

		EmployeeListForm emp2 = new EmployeeListForm();
		emp2.setId(id++);
		emp2.setEmpNumber(employeeId++);
		emp2.setFirstName("Jose");
		emp2.setLastName("Rizal");
		emp2.setEmailAddress("jRizal@gmail.com");
		emp2.setUserName("Jose");
		
		EmployeeListForm emp3 = new EmployeeListForm();
		emp3.setId(id++);
		emp3.setEmpNumber(employeeId++);
		emp3.setFirstName("Queen");
		emp3.setLastName("Elizabet");
		emp3.setEmailAddress("UK@gmail.com");
		emp3.setUserName("Queen");

		// create the list
		employee = new ArrayList<>();
		
		// add to the list
		employee.add(emp1);
		employee.add(emp2);
		employee.add(emp3);
	}
	
	
	
	
} 
