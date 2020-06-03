/* @Author Kenneth Abad */
package com.fujitsu.ph.tsup.venue.management.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fujitsu.ph.tsup.venue.management.model.VenueDeleteForm;

@Controller
@RequestMapping("/venues")
public class VenueDeleteController {
	
	@GetMapping("/delete")
	public String showDelete(Model model) {

		VenueDeleteForm venue = new VenueDeleteForm();
		venue.setIdnumber(2020);
		venue.setVname("Fujitsu Building");
		
		model.addAttribute("venueDeleteForm", venue);
		
		return "venue-management/venueDelete";	
	}	
}
