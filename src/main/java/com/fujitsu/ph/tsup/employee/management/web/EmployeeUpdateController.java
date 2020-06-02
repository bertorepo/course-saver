package com.fujitsu.ph.tsup.employee.management.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fujitsu.ph.tsup.employee.management.model.EmployeeUpdateForm;

@Controller
@RequestMapping("/employee")
public class EmployeeUpdateController {
	
	@GetMapping("/Update")
	public String show(Model model) {
		EmployeeUpdateForm employeeUpdate = new EmployeeUpdateForm();
		employeeUpdate.setID(7);
		employeeUpdate.setNUMBER("220053693");
		employeeUpdate.setLAST_NAME("Iwarat");
		employeeUpdate.setFIRST_NAME("Harvey");
		employeeUpdate.setEMAIL_ADDRESS("j.iwarat@fujitsu.com");
		employeeUpdate.setUSERNAME("j.iwarat");
		
		model.addAttribute("updating", employeeUpdate);
		return "employee-management/EmployeeUpdate";
		
		
	}

}
