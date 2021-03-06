/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.jdu.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.department.domain.Department;
import com.fujitsu.ph.tsup.department.service.DepartmentService;
import com.fujitsu.ph.tsup.jdu.domain.Jdu;
import com.fujitsu.ph.tsup.jdu.model.JduForm;
import com.fujitsu.ph.tsup.jdu.service.JduService;

//=======================================================
//Project Name: Training Sign Up
//Class Name: JduController.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 28/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @author dw.cardenas
 *
 */
@Controller
@RequestMapping("/jdu")
public class JduController {
	@Autowired
	JduService jduService;
	
	@Autowired
	DepartmentService departmentService;

	/**
	 * <pre>
	 * View for creation page of a new JDU
	 * Method: GET
	 * <pre>
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	public String showCreateJduForm(Model model) {
		List<Jdu> jduList = jduService.findAllJdus().stream().collect(Collectors.toList());

		model.addAttribute("jduList", jduList);
		return "jdu-management/jduCreate";
	}

	/**
	 * <pre>
	 * Create a new JDU.
	 * Method: POST
	 * <pre>
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/create")
	public String submitCreateJduForm(@ModelAttribute JduForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		String jduName = form.getJduName().replaceAll("\\s+", " ").trim();
		String timezone = form.getTimezone();

		Set<Jdu> jduSet = jduService.findJduByName(jduName);
		for (Jdu jdu : jduSet) {
			if (jdu.getJduName() == jduName && jdu.getTimezone() == timezone) {
				String message = String.format("An error occured when trying to add new Jdu \"%s\"", jduName);
				redirectAttributes.addFlashAttribute("message", message);

				return "jdu-management/jduCreate";
			}
		}

		Jdu newJdu = Jdu.builder()
				.addJduName(jduName)
				.addTimezone(timezone)
				.build();
		jduService.createJdu(newJdu);

		String message = String.format("You have successfully added the new jdu \"%s\"", jduName);
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/jdu/create#successModal";
	}

	/**
	 * <pre>
	 * View for management of JDUs
	 * Method: GET
	 * <pre>
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/load")
	public String load(Model model) {
		List<Jdu> jduList = jduService.findAllJdus().stream().collect(Collectors.toList());
		List<Department> departmentList = departmentService.findAllDepartments().stream().collect(Collectors.toList());
		
		model.addAttribute("jduList", jduList);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("jduForm", new JduForm());
		
		return "jdu-management/jduView";
	}

	/**
	 * <pre>
	 * Updates an existing JDU
	 * Method: POST
	 * <pre>
	 *
	 * @param form
	 * @param bindingResult
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/update")
	public String updateJduForm(@ModelAttribute("jduForm") JduForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		Jdu updatedJdu = Jdu.builder()
				.addId(form.getId())
				.addJduName(form.getJduName().replaceAll("\\s+", " ").trim())
				.addTimezone(form.getTimezone())
				.build();

		jduService.updateJdu(updatedJdu);

		String message = String.format("You have successfully updated the JDU \"%s\"", form.getJduName());
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/jdu/load#successModal";
	}

	/**
	 * <pre>
	 * Deletes an existing Jdu
	 * Method: POST
	 * <pre>
	 *
	 * @param id
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@PostMapping("{jduId}/delete")
	public String deleteJdu(@PathVariable("jduId") Long id, RedirectAttributes redirectAttributes, Model model) {
		jduService.deleteDepartment(id);

		String message = "You have successfully deleted the JDU.";
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/jdu/load#successModal";
	}

	/**
	 * <pre>
	 * Searches for a specific JDU and shows a view with JDU/s found with search key
	 * Method: GET
	 * <pre>
	 *
	 * @param searchKeyName
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String submitSearchJduForm(@RequestParam("searchKeyName") String searchKeyName, Model model) {
		String searchKey = searchKeyName.replaceAll("\\s+", " ").trim();

		if (searchKey.isEmpty()) {
			return "redirect:/jdu/load";
		}

		List<Jdu> jduList = jduService.findJduByName(searchKey).stream().collect(Collectors.toList());
		List<Department> departmentList = departmentService.findAllDepartments().stream().collect(Collectors.toList());

		model.addAttribute("jduList", jduList);
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("jduForm", new JduForm());
		
		return "jdu-management/jduView";
	}
}
