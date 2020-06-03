package com.fujitsu.ph.tsup.venue.management.web;
/* @Author Kenneth Abad */


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
import com.fujitsu.ph.tsup.venue.management.model.VenueDeleteForm;

@Controller
@RequestMapping("/venues")
public class VenueDeleteController {
	private static Logger logger = LoggerFactory.getLogger(VenueDeleteController.class);
	
	@GetMapping("/delete")
	public String showDelete(Model model) {
		logger.debug("Venue:{}", model);
		
		if (model.containsAttribute("venueDeleteForm")) {
			return "venue-management/venueDelete";
		}
		
		VenueDeleteForm namesofvenue = new VenueDeleteForm();	
		namesofvenue.setId(2020);
		namesofvenue.setVname("Fujitsu Building");
		namesofvenue.setDelete("");
		
		model.addAttribute("venueDeleteForm", namesofvenue);
		
		return "venue-management/venueDelete";	
	}
	
	@PostMapping("/delete")
	public String submit(
			@Valid @ModelAttribute("venueDeleteForm") VenueDeleteForm venueDeleteForm, 
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		logger.debug("Venue:{}", venueDeleteForm);
		logger.debug("Result:{}", result);
		
		venueDeleteForm.setId(2020);
		venueDeleteForm.setVname("Fujitsu Building");
		
		model.addAttribute("venueDeleteForm", venueDeleteForm);
		if (result.hasErrors()) {
			return "venue-management/venueDelete";
		}
		
		redirectAttributes.addFlashAttribute("venueDeleteForm", venueDeleteForm);
		return "redirect:/venues";
		
	}
	
}