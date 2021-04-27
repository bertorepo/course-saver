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

@Controller
@RequestMapping("/report/")
public class SummaryGSTPMController {

	
	@Autowired
	private SummaryGSTPMService summaryGSTPMService ;
	
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(SummaryGSTPMController.class);
	
	
	
	 @GetMapping("/summarygstpm") 
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
		 
		 
		 summaryGSTForm.setTotalNoJDUPMValue(summaryGSTPMService.countTotalNumberOfJDUPM());
		 summaryGSTForm.setTotalNoJDUPMLastWeekValue(summaryGSTPMService.countTotalNoJDUPMLastWeek());
		 summaryGSTForm.setTotalNoOrigMemValue(summaryGSTPMService.countTotalNoOrigMem());
		 summaryGSTForm.setTotalNoNewMemValue(summaryGSTPMService.countTotalNoNewMem());
		 summaryGSTForm.setTotalNoJDUPMFinValue(summaryGSTPMService.countTotalNoJDUPMF());
		 summaryGSTForm.setTotalNoJDUPMLastWkFinValue(summaryGSTPMService.countTotalNoJDUPMLastWkF(summaryGSTForm.getScheduledStartDateTime(), summaryGSTForm.getScheduledEndDateTime()));
		 summaryGSTForm.setPercentageFinTodayValue(summaryGSTPMService.percentageFinishedToday());
		 summaryGSTForm.setPercentageFinLastWkValue(summaryGSTPMService.percentageFinishedLastWeek(summaryGSTForm.getScheduledStartDateTime(), summaryGSTForm.getScheduledEndDateTime()));
    
	     model.addAttribute("summaryGST", summaryGSTForm);
         model.addAttribute("nullMessage", "No Summary Report Found");
     
         return "reports/SummaryGSTForPM"; 
     }
	
}
    
