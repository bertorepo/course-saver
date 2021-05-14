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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.report.summary.model.SummaryGSTDevForm;
import com.fujitsu.ph.tsup.report.summary.service.SummaryGSTDevService;

/**
 * <pre>
 * The controller of G3CC standardization training for developers
 * </pre>
 * 
 * @author g.cabiling
 * @version 1.0.0
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
    public String loadSummary(Model model) {

        SummaryGSTDevForm summaryGSTDev = summaryGSTDevService.getSummary();
        model.addAttribute("TotalNoJDUDevValue", summaryGSTDev.getTotalNoJDUDevValue());
        model.addAttribute("TotalNoJDUDevLastWeekValue", summaryGSTDev.getTotalNoJDUDevLastWeekValue());
        model.addAttribute("TotalNoExistingMemValue", summaryGSTDev.getTotalNoExistingMemValue());
        model.addAttribute("TotalNoNewMemValue", summaryGSTDev.getTotalNoNewMemValue());
        model.addAttribute("TotalNoJDUDevFinValue", summaryGSTDev.getTotalNoJDUDevFinValue());
        model.addAttribute("TotalNoJDUDevLastWkFinValue", summaryGSTDev.getTotalNoJDUDevLastWkFinValue());
        model.addAttribute("PercentageFinTodayValue", summaryGSTDev.getPercentageFinTodayValue());
        model.addAttribute("PercentageFinLastWkValue", summaryGSTDev.getPercentageFinLastWkValue());
        model.addAttribute("summaryGSTDev", summaryGSTDev);
        return "reports/SummaryGSTDev";

    }

}
