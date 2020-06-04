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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fujitsu.ph.tsup.venue.management.model.VenueNames;
import com.fujitsu.ph.tsup.venue.management.model.VenueUpdateForm;

@Controller
@RequestMapping("/venues")
public class VenueUpdateController {
    //VenueName
    Set<VenueNames> vname = new HashSet<>();
    VenueNames vn = new VenueNames();
    
    //VenueUpdateForm
    VenueUpdateForm venue = new VenueUpdateForm();
	
    //logger for VenueUpdateController
    private static Logger logs = LoggerFactory.getLogger(VenueUpdateController.class);
	
    @GetMapping("/update")
    public String update(Model model) {
        venue.setId((long) 12345);
        venue.setVenue("SMX Convention Center");
        venue.setVNLst(createVenueLst());

        model.addAttribute("venueUpdate", venue);
        return "venue-management/venueUpdate";	
    }
	
    @PostMapping("/update")
    public String submitUpdate(@Valid @ModelAttribute("venueUpdate") VenueUpdateForm venueUpdateForm, 
            BindingResult bres, Model model, RedirectAttributes redirectAttributes) {
		
        //Logs the bugs caught by the system
        logs.debug("Venue: {}", venueUpdateForm);
        logs.debug("Result: {}", bres);
        
        venueUpdateForm.setId((long) 12345);
        venueUpdateForm.setVenue("SMX Convention Center");
        venueUpdateForm.setVNLst(createVenueLst());
        
        /*
         * Sets Variables for VenueNames
         */
		
        model.addAttribute("venueUpdate", venueUpdateForm);
        
        if (bres.hasErrors()) {
            return "venue-management/venueUpdate";
        }
		
        redirectAttributes.addFlashAttribute("venueUpdate", venueUpdateForm);
        return "redirect:/venues";
        
    }
	
    public Set<VenueNames> createVenueLst(){
        /*
         * Sets Variables for VenueNames
         */
        vn.setId(1234567);
        vn.setName("SMX Convention Center");
        vname.add(vn);
        
        return vname;
    }
	
}
    