package com.fujitsu.ph.tsup.venue.management.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

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

@Controller
@RequestMapping("/venues")
public class VenueUpdateController {
	
	@GetMapping("/update")
	public String update(Model model) {
		VenueListForm venue = new VenueListForm();
		Set<VenueNames> vns = new HashSet<>();
		
		VenueNames vn1 = new VenueNames();
		vn1.setId(1);
		vn1.setName("Mutien Hall");
		vns.add(vn1);
		
		venue.setVNs(vns);
		model.addAttribute("venueUpdate", venue);
		return "venue-management/venueUpdate";
		
	}
}
