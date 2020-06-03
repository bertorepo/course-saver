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

import com.fujitsu.ph.tsup.employee.management.model.EMPLOYEE_CREATE;
import com.fujitsu.ph.tsup.employee.management.model.EmployeeForm;

@Controller
@RequestMapping("/Employee")
public class EMPLOYEE_CREATE_CONTROLLER {
    private static Logger logger = LoggerFactory.getLogger(EMPLOYEE_CREATE_CONTROLLER.class);

    @GetMapping("/Register")
    public String show(Model model) {
        logger.debug("Model:{}", model);

        if (model.containsAttribute("employee")) {
            return "employee-management/EMPLOYEE_CREATE_FORM";
        }

        EMPLOYEE_CREATE employee = new EMPLOYEE_CREATE();
        employee.setEmployeeID(1L);
        employee.setEmployeeNumber("0000000000");
        employee.setLastName("Last Name");
        employee.setFirstName("First Name");
        employee.setEmailAddress("email@email.com");
        employee.setUsername("i.lastname");

        model.addAttribute("employee", employee);
        return "employee-management/EMPLOYEE_CREATE_FORM";

    }

    @PostMapping("/Register")
    public String submit(@Valid @ModelAttribute("employee") EMPLOYEE_CREATE employee, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        logger.debug("Employee:{}", employee);
        logger.debug("Result:{}", result);

        model.addAttribute("employee", employee);
        if (result.hasErrors()) {
            return "employee-management/EMPLOYEE_CREATE_FORM";
        }

        redirectAttributes.addFlashAttribute("employee", employee);
        return "redirect:/Employee/Register";
    }
}