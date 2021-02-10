package com.fujitsu.ph.tsup.autoregister.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationForm;
import com.fujitsu.ph.tsup.autoregister.service.AutoRegistrationService;

@Controller
@RequestMapping("/register")
public class AutoRegistrationController {
	
	// Auto Registration Service class
    @Autowired
    AutoRegistrationService autoRegistrationService;
	
	@GetMapping("")
	public String showCreateAutoRegistationForm(Model model) {
		FpiUser user = (FpiUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("username", user.getUserName());
		model.addAttribute("departmentList", autoRegistrationService.getAllDepartment());
		return "register";
	}
	
	@PostMapping("/createNewMember")
	public String submitCreateAutoRegistationForm(AutoRegistrationForm form, BindingResult result) {
		FpiUser user = (FpiUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		AutoRegistration autoRegistrationDetails = new AutoRegistration.Builder(form.getId(),form.getFirstName(), form.getLastName(), 
				form.getEmailAddress(), form.getDepartmentid(), user.getUserName(), form.getEmploymentDate()).build();
		autoRegistrationService.addAutoRegistration(autoRegistrationDetails);
		
		return "redirect:/register/#successModal";
	}

}
