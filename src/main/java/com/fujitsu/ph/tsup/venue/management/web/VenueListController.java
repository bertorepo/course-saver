/* Created by: Jeamel de Guzman */
package com.fujitsu.ph.tsup.venue.management.web;

import java.util.Arrays;
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

import com.fujitsu.ph.tsup.venue.management.model.VenueListForm;
import com.fujitsu.ph.tsup.venue.management.model.VenueNames;

@Controller
@RequestMapping("/venues")
public class VenueListController {
    private static Logger logger = LoggerFactory.getLogger(VenueListController.class);

    @GetMapping()
    public String show(Model model) {
        logger.debug("Model:{}", model);

        if (model.containsAttribute("venueList")) {
            return "venue-management/venueList";
        }

        VenueListForm venue = new VenueListForm();
        venue.setSearch("");
        venue.setVns(createVns());
        model.addAttribute("venueList", venue);

        return "venue-management/venueList";

    }

    @PostMapping("")
    public String submit(@Valid @ModelAttribute("venueList") VenueListForm venueList, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {

        logger.debug("Venue List:{}", venueList);
        logger.debug("Result:{}", result);

        venueList.setVns(createVns());

        model.addAttribute("venueList", venueList);
        if (result.hasErrors()) {
            return "venue-management/venueList";
        }

        redirectAttributes.addFlashAttribute("venueList", venueList);
        return "redirect:/venues";

    }

    private Set<VenueNames> createVns() {
        Set<VenueNames> vns = new HashSet<>();

        VenueNames vn1 = new VenueNames();
        vn1.setId(1);
        vn1.setName("Mutien Hall");
        vns.add(vn1);

        VenueNames vn2 = new VenueNames();
        vn2.setId(2);
        vn2.setName("Benilde Hall");
        vns.add(vn2);

        return vns;
    }
}
