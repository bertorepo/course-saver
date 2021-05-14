/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.report.summary.web;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fujitsu.ph.tsup.report.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.report.summary.service.MandatoryCoursesService;

/**
 * The controller of mandatory courses
 * 
 * @author j.zamora (New Creation by: j.zamora)
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
     * @return string
     */
    @GetMapping("load")
    public String loadGenerateReport() {
        return "reports/summaryMandatoryCourses";
    }
    /**
     * Passes the data to summaryMandatoryCourses
     * 
     * @param dateRange startDateTime, endDateTime
     * @param model
     * @return string
     */
    @GetMapping("/load/getMandatoryCourses")
    public String generateMandatoryCourses(@RequestParam Map<String, String> dateRange, Model model) {
                
        LocalDateTime start = LocalDateTime.parse(dateRange.get("startDateTime"));
        LocalDateTime end = LocalDateTime.parse(dateRange.get("endDateTime"));
                
        Set<MandatoryCourses> mandatoryCourses = mandatoryCoursesService
                .getMandatoryCourses(start, end);
        model.addAttribute("mandatoryCourses", mandatoryCourses);
        
        return "reports/summaryMandatoryCourses";
        
        
    }

    /**
     * Passes the data to summaryMandatoryCourses when view button is pressed
     * 
     * @param dateRange starDateTime, endDateTime
     * @param course inputCourse
     * @param model
     * @return string
     */
    @GetMapping("/load/summary")
    public String viewMandatoryCoursesReports(@RequestParam Map<String, String> dateRange,
            @RequestParam(value = "hCourse") String course, Model model) {

        LocalDateTime start = LocalDateTime.parse(dateRange.get("hStartDateTime"));
        LocalDateTime end = LocalDateTime.parse(dateRange.get("hEndDateTime"));
        Set<MandatoryCourses> mandatoryCourses = mandatoryCoursesService
                .getMandatoryCourses(start, end);
        model.addAttribute("mandatoryCourses", mandatoryCourses);

        model.addAttribute("totalNumberOfJduMembers",
                mandatoryCoursesService.getTotalNumberOfJduMembers());
        model.addAttribute("totalNumberOfCompletion",
                mandatoryCoursesService.getTotalNumberOfCompletion(course));
        model.addAttribute("totalNumberOfCompletionLastWeek",
                mandatoryCoursesService.getTotalNumberOfCompletionLastWeek(course));
        model.addAttribute("percentageCompletion",
                mandatoryCoursesService.getPercentageCompletion());
        model.addAttribute("percentageCompletionLastWeek",
                mandatoryCoursesService.getPercentageCompletionLastWeek());

        return "reports/summaryMandatoryCourses";
    }
}
