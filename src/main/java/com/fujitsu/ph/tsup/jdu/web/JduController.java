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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.department.domain.Department;
import com.fujitsu.ph.tsup.department.model.DepartmentForm;
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

	@GetMapping("/create")
	public String showCreateJduForm(Model model) {
		List<Jdu> jduList = jduService.findAllJdus().stream().collect(Collectors.toList());

		model.addAttribute("jduList", jduList);
		return null;
	}

	@PostMapping("/create")
	public String submitCreateJduForm(@ModelAttribute JduForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		String jduName = form.getJduName();

		Set<Jdu> jduSet = jduService.findJduByName(jduName);
		for (Jdu jdu : jduSet) {
			if (jdu.getJduName() == jduName) {
				String message = String.format("An error occured when trying to add new Jdu \"%s\"", jduName);
				redirectAttributes.addFlashAttribute("message", message);

				return "venue-management/venueCreate";
			}
		}

		Jdu newJdu = Jdu.builder().addJduName(jduName).build();
		jduService.createJdu(newJdu);

		String message = String.format("You have successfully added the new jdu \"%s\"", jduName);
		redirectAttributes.addFlashAttribute("message", message);

		return null;
	}

	@GetMapping("/load")
	public String load(Model model) {
		List<Jdu> jduList = jduService.findAllJdus().stream().collect(Collectors.toList());

		model.addAttribute("jduList", jduList);
		return "jdu-management/jduView";
	}

	@PostMapping("/update")
	public String updateJduForm(@ModelAttribute JduForm form, RedirectAttributes redirectAttributes) {
		Jdu updatedJdu = Jdu.builder()
				.addId(form.getId())
				.addJduName(form.getJduName())
				.build();

		jduService.updateJdu(updatedJdu);

		String message = String.format("You have successfully updated the jdu \"%s\"", form.getJduName());
		redirectAttributes.addFlashAttribute("message", message);

		return null;
	}
	
	@PostMapping("{jduId}/delete")
	public String deleteJdu(@PathVariable("jduId") Long id, RedirectAttributes redirectAttributes, Model model) {
		jduService.deleteDepartment(id);

		String message = "You have successfully deleted the jdu";
		redirectAttributes.addFlashAttribute("message", message);

		return null;
	}
}
