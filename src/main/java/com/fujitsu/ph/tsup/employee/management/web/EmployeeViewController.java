package com.fujitsu.ph.tsup.employee.management.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.employee.management.model.EmployeeView;

@Controller
@RequestMapping("/employees")
public class EmployeeViewController {
	    // load employee data
	    List<EmployeeView> theEmployees;

	@GetMapping("/view")
	public String viewEmployeeForm(Model model) {
		// create employees
		EmployeeView emp1 = new EmployeeView();
		int id = 1;
		int employeeId = 17415;

		emp1.setId(id++);
		emp1.setEmpNumber(employeeId++);
		emp1.setFirstName("Janella");
		emp1.setLastName("Macabugao");
		emp1.setEmailAddress("macabugaoj@gmail.com");
		emp1.setUserName("Janella");

		EmployeeView emp2 = new EmployeeView();
		emp2.setId(id++);
		emp2.setEmpNumber(employeeId++);
		emp2.setFirstName("Cedie R");
		emp2.setLastName("King");
		emp2.setEmailAddress("cedirking@gmail.com");
		emp2.setUserName("Cedie123");

		EmployeeView emp3 = new EmployeeView();
		emp3.setId(id++);
		emp3.setEmpNumber(employeeId++);
		emp3.setFirstName("Lisa");
		emp3.setLastName("Valenzuela");
		emp3.setEmailAddress("liza@gmail.com");
		emp3.setUserName("Liza123");

		EmployeeView emp4 = new EmployeeView();
		emp4.setId(id++);
		emp4.setEmpNumber(employeeId++);
		emp4.setFirstName("Jonessa");
		emp4.setLastName("Mercado");
		emp4.setEmailAddress("mercado@gmail.com");
		emp4.setUserName("Jonessa123");

		EmployeeView emp5 = new EmployeeView();
		emp5.setId(id++);
		emp5.setEmpNumber(employeeId++);
		emp5.setFirstName("Vincent");
		emp5.setLastName("Cruz");
		emp5.setEmailAddress("vincentcruz@gmail.com");
		emp5.setUserName("Vincent123");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
		theEmployees.add(emp4);
		theEmployees.add(emp5);

		model.addAttribute("employee", theEmployees);

		return "employee-management/viewEmployee";
	}

}
