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
		if (model.containsAttribute("venueCreate")) {
			return "venue-management/venueCreate";
		}

		venueCreateForm venueCreate = new venueCreateForm();

		venueCreate.setId(123456);
		venueCreate.setVenueName("Marquis Hall");
		
		
		model.addAttribute("venueCreate", venueCreate);
		return "venue-management/venueCreate";
	}
	
	@PostMapping("/new")
	public String submit(
			@Valid @ModelAttribute("venueCreate") venueCreateForm venueCreate,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		logger.debug("VenueCreate:{}", venueCreate);
		logger.debug("Result:{}", result);

		model.addAttribute("venueCreate", venueCreate);
		if (result.hasErrors()) {
			return "venue-management/venueCreate";
		}
		
		redirectAttributes.addFlashAttribute("venueCreate", venueCreate);
		return "redirect:/venues/new";
		}
		
	}
	
	
	
