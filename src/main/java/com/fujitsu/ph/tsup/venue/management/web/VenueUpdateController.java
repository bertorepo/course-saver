package com.fujitsu.ph.tsup.venue.management.web;
/*
 * 	Author: Jimenez, John Carlo, R.
 *  Last Modified: 6/2/2020
 * 
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
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

import com.fujitsu.ph.tsup.venue.management.model.VenueListForm;
import com.fujitsu.ph.tsup.venue.management.model.VenueNames;
import com.fujitsu.ph.tsup.venue.management.model.VenueUpdateForm;

@Controller
@RequestMapping("/venues")
public class VenueUpdateController {
	
	//logger for VenueUpdateController
	private static Logger logs = LoggerFactory.getLogger(VenueUpdateController.class);
	
	@GetMapping("/update")
	public String update(Model model) {
		VenueUpdateForm venue = new VenueUpdateForm();
		List<VenueNames> vns = new ArrayList<>();
		
		/*
		 * Sets Variables for VenueNames
		 */
		VenueNames vn1 = new VenueNames();
		vn1.setId(1234567);
		vn1.setName("SMX Convention Center");
		vns.add(vn1);
		
		/*
		 * Sets Variables for VNLst, Id, venue
		 */
		venue.setVNLst(vns);
		venue.setId(1234567);
		venue.setVenue("SMX Convention Center");

		model.addAttribute("venueUpdate", venue);
		return "venue-management/venueUpdate";	
	}
	
	@PostMapping("/update")
	public String submitUpdate(@Valid VenueUpdateForm venueUpdateForm, BindingResult bres, Model model) {
		
		//Logs the bugs caught by the system
		logs.debug("Venue: {}", venueUpdateForm);
		logs.debug("Result: {}", bres);
		logs.debug("Model: {}", model);
		
		model.addAttribute("venueUpdate", venueUpdateForm);
		
		if (bres.hasErrors()) {
			return "venue-management/venueUpdate";
		}
		
		return "venue-management/venueUpdate";
	
	}
}