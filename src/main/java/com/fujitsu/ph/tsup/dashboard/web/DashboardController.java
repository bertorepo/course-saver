package com.fujitsu.ph.tsup.dashboard.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.dashboard.service.DashboardInstructorService;
import com.fujitsu.ph.tsup.dashboard.service.DashboardMemberService;
import com.fujitsu.ph.tsup.dashboard.service.DashboardPmoService;

//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardController.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
 * <pre>
 * The controller for the dashboard
 * 
 * <pre>
 * 
 * @version 0.01
 * @author Jm.Deguzman
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    /*
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(DashboardController.class);
    /*
     * DashboardMemberService
     */
    @Autowired
    private DashboardMemberService dashboardMemberService;
    /*
     * DashboardInstructorService
     */
    @Autowired
    private DashboardInstructorService dashboardInstructorService;
    /*
     * DashboardPmoService
     */
    @Autowired
    private DashboardPmoService dashboardPmoService;

    /**
     * Shows the data in the screen extracted from the database
     * 
     * @param employeeId
     * @param model
     * @return dashboard (file URL)
     */
    @GetMapping("")
    public String show(Long employeeId, Model model) {
        logger.debug("Model:{}", model);
        FpiUser user = (FpiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeId = user.getId();
        try {
            if (user.getRoles().contains("Member")) {
                model.addAttribute("memberActiveCourses", dashboardMemberService.getActiveCourses(employeeId));
                model.addAttribute("memberTrainingsToday", dashboardMemberService.getTrainingsToday(employeeId));
                model.addAttribute("memberCourses", dashboardMemberService.findCourses(employeeId));
            } else if (user.getRoles().contains("Instructor")) {
                model.addAttribute("instructorCoursesToday", dashboardInstructorService.getCoursesToday(employeeId));
                model.addAttribute("instructorCourses", dashboardInstructorService.findCourses(employeeId));
            } else if (user.getRoles().contains("PMO")) {
                model.addAttribute("pmoCourses", dashboardPmoService.findCourses());
            }
        } catch (Exception e) {
            model.addAttribute("Errors", e.getMessage());
        }

        return "dashboard";

    }
}
