package com.fujitsu.ph.tsup.venue.management.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.venue.management.model.VenueNames;
import com.fujitsu.ph.tsup.venue.management.model.venueCreateForm;

@Controller
@RequestMapping("/venues")

public class venueCreateController {

	@GetMapping("/new")
	public String show(Model model) {

		venueCreateForm venue = new venueCreateForm();
				
		venue.setId(123);
		venue.setVenueName("Marquis Hall");
	
		
		Set<VenueNames> vns = new HashSet<>();
		
		VenueNames vn1 = new VenueNames();
		vn1.setId(1);
		vn1.setName("Mutien Hall");
		vns.add(vn1);
		
		VenueNames vn2 = new VenueNames();
		vn2.setId(2);
		vn2.setName("Benilde Hall");
		vns.add(vn2);
		
		venue.setVNs(vns);

		model.addAttribute("venueCreate", venue);

		return "venue-management/venueCreate";
	}
}
