package com.fujitsu.ph.tsup.reports.summary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.reports.summary.service.MandatoryCoursesService;

@Controller
@RequestMapping("mandatoryCourses")
public class MandatoryCoursesController {
    @Autowired
    private MandatoryCoursesService mandatoryCoursesService;
    
    @GetMapping("load")
    public String loadGenerateReport(Model model) {
        return "reports/summaryMandatoryCourses";
    }

    @GetMapping("view")
    public String viewMandatoryCoursesReports(MandatoryCourses form, Model model, 
            BindingResult bindingResult) {
        MandatoryCourses courseDetails = new MandatoryCourses.Builder(form.getId(),form.getName()).build();
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfJduMembers());
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfCompletion(courseDetails));
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfCompletionLastWeek(courseDetails));
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getPercentageCompletion());
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getPercentageCompletionLastWeek());
        return "reports/summaryMandatoryCourses";
    }
    
    @GetMapping("export")
    public String exportMandatoryCoursesReports(MandatoryCourses form, Model model, 
            BindingResult bindingResult) {
        MandatoryCourses courseDetails = new MandatoryCourses.Builder(form.getId(),form.getName()).build();
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfJduMembers());
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfCompletion(courseDetails));
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfCompletionLastWeek(courseDetails));
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getPercentageCompletion());
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getPercentageCompletionLastWeek());
        return "reports/summaryMandatoryCourses";
    }
}
