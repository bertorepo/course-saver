/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.venue.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fujitsu.ph.tsup.venue.domain.Venue;
import com.fujitsu.ph.tsup.venue.model.VenueForm;
import com.fujitsu.ph.tsup.venue.service.VenueService;

//=======================================================
//Project Name: Training Sign Up
//Class Name: VenueController.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 03/06/2021 | WS) dw.cardenas  | Created
//=======================================================

/**
 *
 * @version 0.01
 * @author dw.cardenas
 *
 */
@Controller
@RequestMapping("/venue")
public class VenueController {

	@Autowired
	VenueService venueService;

	/**
	 * <pre>
	 * View venue creation page.
	 * Method = GET
	 * <pre>
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	public String showCreateVenueForm(Model model) {
		Set<Venue> venues = venueService.findAllVenues();
		List<Venue> venueList = venues.stream().collect(Collectors.toList());

		model.addAttribute("venueList", venueList);

		return "venue-management/venueCreate";
	}

	/**
	 * <pre>
	 * Addition of new venue.
	 * Method = POST
	 * <pre>
	 *
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/create")
	public String submitCreateVenueForm(VenueForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		String venueName = form.getName().replaceAll("\\s+", " ").trim();

		Set<Venue> venueSet = venueService.findVenueByName(venueName);
		for (Venue venue : venueSet) {
			if (venueName.equals(venue.getName())) {
				String message = String.format("An error occured when trying to add new venue \"%s\"", venueName);
				redirectAttributes.addFlashAttribute("message", message);

				return "venue-management/venueCreate";
			}
		}

		venueService.createVenue(new Venue.Builder(venueName).build());

		String message = String.format("You have successfuly added the venue \"%s\"", venueName);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/venue/create#successModal";
	}

	/**
	 * <pre>
	 * Display manage venue page with all of venues.
	 * Method = GET
	 * <pre>
	 *
	 * @param model
	 * @return
	 */
	@GetMapping ("/load")
	public String load(Model model) {
		Set<Venue> venues = venueService.findAllVenues();
		List<Venue> venueList = venues.stream().collect(Collectors.toList());

		model.addAttribute("venueList", venueList);

		return "venue-management/venueView";
	}

	/**
	 * <pre>
	 * Display specific venues from search key in manage venue page.
	 * Method = GET
	 * <pre>
	 *
	 * @param searchKeyName
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String submitSearchVenueForm(@RequestParam("searchKeyName") String searchKeyName, Model model) {
		String searchKey = searchKeyName.trim();

		if (searchKey.isEmpty()) {
			return "redirect:/venue/load";
		}

		List<Venue> venue = venueService.findVenueByName(searchKey)
				.stream().collect(Collectors.toList());

		model.addAttribute("venueList", venue);

		return "venue-management/venueView";
	}

	/**
	 * <pre>
	 * Update an existing venue.
	 * Method = POST
	 * <pre>
	 *
	 * @param venue
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/update")
	public String updateVenueForm(@ModelAttribute VenueForm venue, RedirectAttributes redirectAttributes) {
		Venue updatedVenue = new Venue.Builder(venue.getId(), venue.getName()).build();
		venueService.updateVenue(updatedVenue);

		String message = String.format("You have successfuly updated the venue \"%s\"", venue.getName());
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/venue/load#successModal";
	}

	/**
	 * <pre>
	 * Delete an existing venue using id.
	 * Method = POST
	 * <pre>
	 *
	 * @param id
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@PostMapping("{venueId}/delete")
	public String deleteVenue(@PathVariable("venueId") Long id, RedirectAttributes redirectAttributes, Model model) {
		venueService.deleteVenue(id);

		String message = String.format("You have successfuly deleted the venue");
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/venue/load#successModal";
	}
}
