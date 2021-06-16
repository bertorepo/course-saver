//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : SummaryGSTDevController
// Class Name   : SummaryGSTDevController.java                                                                                                                                                                             
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/20 | WS)G.Cabiling         | New Creation                                                                                                                                                                          
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.web;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;
import com.fujitsu.ph.tsup.report.summary.service.SummaryGSTDevService;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for Dev
//Class Name   : SummaryGSTDevController.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    |  ---       | WS) g.cabiling        | Initial Version
//0.02    | 2021/06/14 | WS) m.padaca          | Updated
//==================================================================================================

/**
 * <pre>
 * The controller of G3CC standardization training for developers
 * </pre>
 * 
 * @version 0.02
 * @author g.cabiling
 * @author m.padaca
 */
@Controller
@RequestMapping("/report/summary")
public class SummaryGSTDevController {

    @Autowired
    private SummaryGSTDevService summaryGSTDevService;

    /**
     * 
     * @param Model model
     * @return SummaryGSTDev view
     * 
     */

    @GetMapping("/standardization/dev")
    public String loadSummary(SummaryGSTDevForm summaryGSTDev, Model model) {
    	
    	if (summaryGSTDev.getScheduledStartDateTime() == null ||
    		summaryGSTDev.getScheduledEndDateTime() == null) { 
		          summaryGSTDev.setScheduledStartDateTime(ZonedDateTime.now().minusDays(5).withHour(0).withMinute(0));
		          summaryGSTDev.setScheduledEndDateTime(ZonedDateTime.now());
		}
    	
		summaryGSTDev = summaryGSTDevService.getSummary(summaryGSTDev.getScheduledStartDateTime(),
				summaryGSTDev.getScheduledEndDateTime(),summaryGSTDev);
     
        model.addAttribute("summaryGSTDev", summaryGSTDev);
        return "reports/SummaryGSTDev";

    }

}
