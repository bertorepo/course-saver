/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.department.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.department.domain.Department;
import com.fujitsu.ph.tsup.department.model.DepartmentForm;
import com.fujitsu.ph.tsup.department.service.DepartmentService;
import com.fujitsu.ph.tsup.jdu.domain.Jdu;
import com.fujitsu.ph.tsup.jdu.service.JduService;

//=======================================================
//Project Name: Training Sign Up
//Class Name: DepartmentController.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 23/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 * 
 * @version 0.01
 * @author dw.cardenas
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@Autowired
	JduService jduService;

	@GetMapping("/create")
	public String showCreateDepartmentForm(Model model) {
		List<Department> departmentList = departmentService.findAllDepartments().stream().collect(Collectors.toList());
		List<Jdu> jduList = jduService.findAllJdus().stream().collect(Collectors.toList());

		model.addAttribute("departmentList", departmentList);
		model.addAttribute("jduList", jduList);
		model.addAttribute("departmentForm", new DepartmentForm());

		return "department-management/departmentCreate";
	}

	@PostMapping("/create")
	public String submitCreateDepartmentForm(@ModelAttribute DepartmentForm department, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		String departmentName = department.getDepartmentName().replaceAll("\\s+", " ").trim();
		Long jduId = department.getJduId();

		Set<Department> departmentSet = departmentService.findDepartmentByName(departmentName);
		for (Department dept : departmentSet) {
			if (dept.getName().equalsIgnoreCase(departmentName) &&
					dept.getJduId() == jduId) {
				String message = String.format("An error occured when trying to add new department \"%s\"", departmentName);
				redirectAttributes.addFlashAttribute("errorMessage", message);

				return "department-management/venueCreate";
			}
		}

		Department newDepartment = Department.builder()
				.addJduId(jduId)
				.addDepartmentName(departmentName)
				.build();
		departmentService.createDepartment(newDepartment);

		String message = String.format("You have successfully added the new department \"%s\"", departmentName);
		redirectAttributes.addFlashAttribute("successMessage", message);

		return "redirect:/department/create#successModal";
	}

	@GetMapping("/load")
	public String load(Model model) {
		List<Department> departmentList = departmentService.findAllDepartments().stream().collect(Collectors.toList());
		List<Jdu> jduList = jduService.findAllJdus().stream().collect(Collectors.toList());

		model.addAttribute("departmentList", departmentList);
		model.addAttribute("jduList", jduList);
		model.addAttribute("departmentForm", new DepartmentForm());

		return "department-management/departmentView";
	}

	@PostMapping("/update")
	public String updateDepartmentForm(@ModelAttribute("departmentForm") DepartmentForm form, BindingResult result, RedirectAttributes redirectAttributes) {
		Department updatedDept = Department.builder()
				.addId(form.getId())
				.addDepartmentName(form.getDepartmentName().replaceAll("\\s+", " ").trim())
				.addJduId(form.getJduId())
				.build();
		departmentService.updateDepartment(updatedDept);

		String message = String.format("You have successfuly updated the department \"%s\"", updatedDept.getName());
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/department/load#successModal";
	}
	
	@PostMapping("{departmentId}/delete")
	public String deleteDepartment(@PathVariable("departmentId") Long id, RedirectAttributes redirectAttributes, Model model) {
		departmentService.deleteDepartment(id);

		String message = "You have successfuly deleted the department";
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/department/load#successModal";
	}
}
