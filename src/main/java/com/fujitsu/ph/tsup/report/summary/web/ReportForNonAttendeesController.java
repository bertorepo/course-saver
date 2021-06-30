package com.fujitsu.ph.tsup.report.summary.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.report.summary.model.AttendeeForm;
import com.fujitsu.ph.tsup.report.summary.service.ReportForAttendeesService;

/**
 * The controller of JDU/GDC Mandatory courses report
 * 
 * @author l.celoso
 */
@Controller
@RequestMapping("/report/summary")
public class ReportForNonAttendeesController {
	
	private final static String jduMandatory = "JDU";
	
	private final static String gdcMandatory = "GDC";
	
	@Autowired
    private ReportForAttendeesService reportForNonAttendeesService;

    @GetMapping("/jduMandatoryCourses")
    public String loadSummaryJDU(Model model) {
    	
    	Set<AttendeeForm> attendeeForm = reportForNonAttendeesService.getSummary(jduMandatory);
        List<AttendeeForm> attendance = attendeeForm.stream().collect(Collectors.toList());
     
        model.addAttribute("summaryMandatoryAttendance", attendance);
        return "reports/summaryJDUMandatory";

    }
    
    @GetMapping("/gdcMandatoryCourses")
    public String loadSummaryGDC(Model model) {
    	
    	Set<AttendeeForm> attendeeForm = reportForNonAttendeesService.getSummary(gdcMandatory);
        List<AttendeeForm> attendance = attendeeForm.stream().collect(Collectors.toList());
     
        model.addAttribute("summaryMandatoryAttendance", attendance);
        return "reports/summaryGDCMandatory";

    }
}
