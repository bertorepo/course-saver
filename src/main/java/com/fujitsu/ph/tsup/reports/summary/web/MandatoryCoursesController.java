/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.reports.summary.service.MandatoryCoursesService;

/**
 * The controller of mandatory courses
 * 
 * @author c.fuerzas (New Creation by: j.zamora)
 * @version Revision: 0.01 Date: 2021-04-21
 */
@Controller
@RequestMapping("mandatoryCourses")
public class MandatoryCoursesController {
    @Autowired
    private MandatoryCoursesService mandatoryCoursesService;

     /**
     * loads the summaryMandatoryCourses view
     * 
     * @param model
     * @return string
     */
    @GetMapping("load")
    public String loadGenerateReport(Model model) {
        return "reports/summaryMandatoryCourses";
    }

     /**
     * Passes the data to summaryMandatoryCourses when view button is pressed
     * 
     * @param form
     * @param model
     * @param bindingResult
     * @return string
     */
    @GetMapping("view")
    public String viewMandatoryCoursesReports(MandatoryCourses form, Model model, 
            BindingResult bindingResult) {
        MandatoryCourses courseDetails = new MandatoryCourses.Builder(form.getId(),form.getName()).build();
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfJduMembers());
        //model.addAttribute("getTotalNumberOfCompletion", mandatoryCoursesService.getTotalNumberOfCompletion(courseDetails));
        //model.addAttribute("getTotalNumberOfCompletionLastWeek", mandatoryCoursesService.getTotalNumberOfCompletionLastWeek(courseDetails));
        model.addAttribute("getPercentageCompletion", mandatoryCoursesService.getPercentageCompletion());
        model.addAttribute("getPercentageCompletionLastWeek", mandatoryCoursesService.getPercentageCompletionLastWeek());
        return "reports/summaryMandatoryCourses";
    }

    /**
    * Passes the data to summaryMandatoryCourses when export button is pressed
    * 
    * @param form
    * @param model
    * @param bindingResult
    * @return string
    */
    @GetMapping("export")
    public String exportMandatoryCoursesReports(MandatoryCourses form, Model model, 
            BindingResult bindingResult) {
        MandatoryCourses courseDetails = new MandatoryCourses.Builder(form.getId(),form.getName()).build();
        model.addAttribute("getTotalNumberOfJduMembers", mandatoryCoursesService.getTotalNumberOfJduMembers());
        //model.addAttribute("getTotalNumberOfCompletion", mandatoryCoursesService.getTotalNumberOfCompletion(courseDetails));
        //model.addAttribute("getTotalNumberOfCompletionLastWeek", mandatoryCoursesService.getTotalNumberOfCompletionLastWeek(courseDetails));
        model.addAttribute("getPercentageCompletion", mandatoryCoursesService.getPercentageCompletion());
        model.addAttribute("getPercentageCompletionLastWeek", mandatoryCoursesService.getPercentageCompletionLastWeek());
        return "reports/summaryMandatoryCourses";
    }
}
