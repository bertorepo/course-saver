/* Created by: Jhaisy Jade Yu */
package com.fujitsu.ph.tsup.venue.management.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.fujitsu.ph.tsup.venue.management.model.VenueViewForm;

@Controller
@RequestMapping("/venues")
public class VenueViewController {
	
	private static Logger logger = LoggerFactory.getLogger(VenueViewController.class);
	
	@GetMapping("/view")
	public String show(Model model) {
		logger.debug("Venue:{}", model);
		if (model.containsAttribute("venueViewForm")) {
			return "venue-management/venueView";
		}
		VenueViewForm venue = new VenueViewForm();
		venue.setID(1);
		venue.setVenueName("EcoTower");
		venue.setSearch("");
		model.addAttribute("view", venue);
		return "venue-management/venueView";
	}
	@PostMapping("/view")
	public String submit(
			@Valid @ModelAttribute("view") VenueViewForm view, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		logger.debug("Venue:{}", view);
		logger.debug("Result:{}", result);
		view.setID(1);
		view.setVenueName("EcoTower");
		
		model.addAttribute("view", view);
		if (result.hasErrors()) {
			return "venue-management/venueView";
		}
		redirectAttributes.addFlashAttribute("view", view);
		return "redirect:/venues";
		
	}
}  