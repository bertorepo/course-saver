/* @Author j.balanon */

package com.fujitsu.ph.tsup.venue.management.web;


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

import com.fujitsu.ph.tsup.venue.management.model.venueCreateForm;

@Controller
@RequestMapping("/venues")

public class venueCreateController {
	private static Logger logger = LoggerFactory.getLogger(venueCreateController.class);

	@GetMapping("/new")
	public String show(Model model) {
		logger.debug("Model:{}", model);
		if (model.containsAttribute("venue")) {
			return "venue-management/venueCreate";
		}

		venueCreateForm venue = new venueCreateForm();

		venue.setId(123);
		venue.setVenueName("Marquis Hall");
		
		
		model.addAttribute("venueCreate", venue);
		return "venue-management/venueCreate";
	}
	
	@PostMapping("/new")
	public String submit(
			@Valid @ModelAttribute("venueCreate") venueCreateForm venue,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		logger.debug("id:{}", venue);
		logger.debug("venueCreate:{}", result);

		model.addAttribute("venueCreate", venue);
		if (result.hasErrors()) {
			return "venue-management/venueCreate";
		}
		redirectAttributes.addFlashAttribute("venue-management", venue);
		return "venue-management/venueCreate";
		
	}
	
	
	
}
