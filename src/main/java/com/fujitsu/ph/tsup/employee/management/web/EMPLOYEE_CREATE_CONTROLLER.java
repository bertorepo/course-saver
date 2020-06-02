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
		employee.setNUMBER("2014-104334");
		employee.setLAST_NAME("Lumontad");
		employee.setFIRST_NAME("Mark Evan");
		employee.setEMAIL_ADDRESS("m.lumontad@fujitsu.com");
		employee.setUSERNAME("m.lumontad");
		
		return "course-management/EMPLOYEE_CREATE_FORM";
}
}
