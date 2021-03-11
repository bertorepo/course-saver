/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.autoregister.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationForm;
import com.fujitsu.ph.tsup.autoregister.service.AutoRegistrationService;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * AutoRegistrationController Class
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 *
 */
@Controller
@RequestMapping("/register")
public class AutoRegistrationController {

    // Auto Registration Service class
    @Autowired
    private AutoRegistrationService autoRegistrationService;

    /**
     * <pre>
     * Load Register Page
     * 
     * <pre>
     * 
     * @param Model model
     * @return register
     */
    @GetMapping("")
    public String showCreateAutoRegistationForm(Model model) {
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("username", user.getUserName());
        model.addAttribute("departmentList", autoRegistrationService.getAllDepartments());
        return "register";
    }
    
    @PostMapping("/validateId/{employeeNumber}")
    @ResponseBody
    public int validateEmployeeNumber(@PathVariable("employeeNumber") String employeeNumber) {
        Employee employee = autoRegistrationService.findDetailsByEmployeeNumber(employeeNumber);
        if (employee == null) {
            return 0;
        } else {
            return 1;
        }
  }

    /**
     * <pre>
     * Create new member. Method = POST
     * 
     * <pre>
     * 
     * @param form AutoRegistrationForm
     * @param result BindingResult
     * @return redirect:/register/#successModal
     */
    @PostMapping("/createNewMember")
    public String submitCreateAutoRegistationForm(AutoRegistrationForm form, BindingResult result) {
    	try {
	    	FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	        AutoRegistration autoRegistrationDetails = new AutoRegistration.Builder(form.getEmployeeNumber(),
	                form.getFirstName(), form.getLastName(), form.getEmailAddress(), form.getDepartmentid(),
	                user.getUserName(), form.getEmploymentDate()).build();
	        autoRegistrationService.addAutoRegistration(autoRegistrationDetails);
	
	        return "redirect:/register/#successModal";
    	}catch(Exception a) {
    		throw new IllegalArgumentException("Error Employee Registration");
    	}
    }

}
