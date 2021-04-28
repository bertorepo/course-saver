/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.web;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.tsup.course.model.CoursesConductedForm;
import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCoursesForm;
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
    public String loadGenerateReport(MandatoryCourses mandatoryCourses, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) 
        { 
            return "reports/generateReport"; 
        }
        
        if (mandatoryCourses.getStartDateTime() == null || mandatoryCourses.getEndDateTime() == null) {
            mandatoryCourses.setStartDateTime(ZonedDateTime.now().minusDays(5).withHour(0).withMinute(0));
            mandatoryCourses.setEndDateTime(ZonedDateTime.now());

            // ************************** REMOVE THIS **************************
            System.out.print("****** mandatoryCourses.getStartDateTime():  ");
            System.out.println(mandatoryCourses.getStartDateTime() + " ******");
            // ************************** REMOVE THIS **************************
        }
        
//        Set<CoursesConducted> courseConducted = coursesConductedService.findAllCoursesConducted(
//                coursesConductedListForm.getScheduledStartDateTime(),
//                coursesConductedListForm.getScheduledEndDateTime());
//
//        Set<CoursesConductedForm> coursesConductedFormSet = new HashSet<>();

//        coursesConductedListForm.setCoursesConductedSet(coursesConductedFormSet);
//        Comparator<CoursesConductedForm> comparator = Comparator.comparing(CoursesConductedForm::getCourseName)
//                .thenComparing(CoursesConductedForm::getScheduledStartDateTime);
//        List<CoursesConductedForm> listOfCoursesConductedFormSet = coursesConductedFormSet.stream()
//                .sorted(comparator).collect(Collectors.toList());
//
        model.addAttribute("mandatoryCourses", mandatoryCourses);
//        model.addAttribute("coursesConducted", listOfCoursesConductedFormSet);
        model.addAttribute("nullMessage", "No Summary Courses Conducted Found");

        
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
    
//    @GetMapping("/course") 
//    public String viewMandatoryCourses(@Valid @ModelAttribute("mandatoryCourses")
//    CoursesConductedListForm coursesConductedListForm, Long reportTypeId, BindingResult bindingResult,Model model){
//    
//    logger.debug("CourseConductedListForm: {}", coursesConductedListForm); logger.debug("Result: {}",
//    bindingResult);
//    
//    
//    if (bindingResult.hasErrors()) 
//    { 
//        return "reports/generateReport"; 
//    }
//    
//    if (coursesConductedListForm.getScheduledStartDateTime() == null ||
//    coursesConductedListForm.getScheduledEndDateTime() == null) { 
//        model.addAttribute("nullMessage","No Course Schedule Found");
//        coursesConductedListForm.setScheduledStartDateTime(ZonedDateTime.now().minusDays(5).withHour(0).withMinute(0));
//        coursesConductedListForm.setScheduledEndDateTime(ZonedDateTime.now()); 
//    }
//    
//    System.out.println(reportTypeId);
//    
//    Set<CoursesConducted> courseConducted =
//    coursesConductedService.findAllCoursesConducted(coursesConductedListForm.getScheduledStartDateTime(),
//    coursesConductedListForm.getScheduledEndDateTime());
//    
//    Set<CoursesConductedForm> coursesConductedFormSet = new HashSet<>();
//    
//    for(CoursesConducted coursesConducted : courseConducted) 
//    { 
//        CoursesConductedForm coursesConductedForm = new CoursesConductedForm();
//        
//        coursesConductedForm.setId(coursesConducted.getId());
//        coursesConductedForm.setCourseName(coursesConducted.getCourseName());
//        coursesConductedForm.setScheduledStartDateTime(coursesConducted.getScheduledStartDateTime());
//        coursesConductedForm.setScheduledEndDateTime(coursesConducted.getScheduledEndDateTime());
//        coursesConductedForm.setRescheduledStartDateTime(coursesConducted.getRescheduledStartDateTime());
//        coursesConductedForm.setRescheduledEndDateTime(coursesConducted.getRescheduledEndDateTime());
//    
//        coursesConductedFormSet.add(coursesConductedForm); 
//    }
//        
//        coursesConductedListForm.setCoursesConductedSet(coursesConductedFormSet);
//        Comparator<CoursesConductedForm> comparator = Comparator
//        .comparing(CoursesConductedForm::getCourseName)
//        .thenComparing(CoursesConductedForm::getScheduledStartDateTime); List<CoursesConductedForm>
//        listOfCoursesConductedFormSet = coursesConductedFormSet.stream() .sorted(comparator)
//        .collect(Collectors.toList());
//    
//        model.addAttribute("coursesConductedForm", coursesConductedListForm);
//        model.addAttribute("coursesConducted", listOfCoursesConductedFormSet);
//        model.addAttribute("nullMessage", "No Summary Courses Conducted Found");
//    
//        return "reports/summaryCourses"; 
//    }
}
