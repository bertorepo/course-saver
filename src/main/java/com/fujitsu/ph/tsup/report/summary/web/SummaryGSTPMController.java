//Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
package com.fujitsu.ph.tsup.report.summary.web;


import java.time.ZonedDateTime;

import javax.validation.Valid;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTForm;
import com.fujitsu.ph.tsup.report.summary.service.SummaryGSTPMService;

import ch.qos.logback.classic.Logger;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for PM
//Class Name   : SummaryGSTPMController.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/04/20 | WS) d.escala          | Initial Version
//0.02    | 2021/04/23 | WS) m.padaca          | Updated
//0.03    | 2021/04/27 | WS) m.padaca          | Updated
//==================================================================================================
/**
* <pre>
* The controller for summaryGSTPM
* </pre>
* 
* @version 0.03
* @author m.padaca
* @author d.escala
*/

@Controller
@RequestMapping("/report/summary")
public class SummaryGSTPMController {

	
	@Autowired
	private SummaryGSTPMService summaryGSTPMService ;
	
//	Logger Factory
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(SummaryGSTPMController.class);
	
//	 <pre>
//    
//    @param summaryGSTForm    form
//    @param BindingResult          bindingResult
//    @param Model                  model
//    @return summaryGSTForm and view
       
	
	 @GetMapping("/standardization/pm") 
     public String viewG3CCSummaryForPM(@Valid @ModelAttribute("summaryGSTForm")
     SummaryGSTForm summaryGSTForm, Long reportTypeId, BindingResult bindingResult,Model model){
		  
		 

		  logger.debug("SummaryGSTForm: {}", summaryGSTForm); logger.debug("Result: {}",bindingResult);
			      
			      if (bindingResult.hasErrors()) 
			      { 
			          return "reports/generateReport"; 
			      }
			      
			      if (summaryGSTForm.getScheduledStartDateTime() == null ||
			    	  summaryGSTForm.getScheduledEndDateTime() == null) { 
			          model.addAttribute("nullMessage","No Summary Found");
			          summaryGSTForm.setScheduledStartDateTime(ZonedDateTime.now().minusDays(5).withHour(0).withMinute(0));
			          summaryGSTForm.setScheduledEndDateTime(ZonedDateTime.now()); 
			      }
		 
			      summaryGSTForm = summaryGSTPMService.getSummary(summaryGSTForm.getScheduledStartDateTime(),summaryGSTForm.getScheduledEndDateTime(),summaryGSTForm);
    
	     model.addAttribute("summaryGST", summaryGSTForm);
     
         return "reports/SummaryGSTForPM"; 
     }
	
}
