package com.fujitsu.ph.tsup.dashboard.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructor;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardMember;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardPmo;
import com.fujitsu.ph.tsup.dashboard.service.DashboardInstructorService;
import com.fujitsu.ph.tsup.dashboard.service.DashboardMemberService;
import com.fujitsu.ph.tsup.dashboard.service.DashboardPmoService;

//==================================================================================================
//$Id:PR06$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardController.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//0.02 | 08/24/2020 |  WS) Jm.Deguzman   | Updated
//0.03 | 08/28/2020 |  WS) Jm.Deguzman   | Updated
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
        model.addAttribute("name", user.getFirstName());
        if(user.getRoles().size() == 1) {
            if (user.getRoles().contains("Member")) {
                try {
                    model.addAttribute("memberActiveCourses", dashboardMemberService.getActiveCourses(employeeId));
                    model.addAttribute("memberTrainingsToday", dashboardMemberService.getTrainingsToday(employeeId));
                    List<DashboardMember> setSortedDashboardMember =  
                            dashboardMemberService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardMember> sortedDashboardMember = setSortedDashboardMember
                            .stream().sorted((e1, e2) ->
                    e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("memberCourses", sortedDashboardMember);
                } catch(Exception e) {
                    model.addAttribute("error", e.getMessage());
                }
            } else if (user.getRoles().contains("Instructor")) {
                try {
                    model.addAttribute("instructorCoursesToday", dashboardInstructorService.getCoursesToday(employeeId));
                    List<DashboardInstructor> setSortedDashboardInstructor =  
                            dashboardInstructorService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardInstructor> sortedDashboardInstructor = setSortedDashboardInstructor
                            .stream().sorted((e1, e2) ->
                    e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("instructorCourses", sortedDashboardInstructor);
                } catch(Exception e) {
                    model.addAttribute("error", e.getMessage());
                }
            } else if (user.getRoles().contains("PMO")) {
                try {
                    List<DashboardPmo> setSortedDashboardPmo =  
                            dashboardPmoService.findCourses().stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardPmo> sortedDashboardPmo = setSortedDashboardPmo
                            .stream().sorted((e1, e2) ->
                            e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("pmoCourses", sortedDashboardPmo);
                } catch(Exception e) {
                    model.addAttribute("error", e.getMessage());
                }
            }
        } else if(user.getRoles().size() == 2) {
            if (user.getRoles().contains("Member") && user.getRoles().contains("Instructor")) {
                try {
                    model.addAttribute("memberActiveCourses", dashboardMemberService.getActiveCourses(employeeId));
                    model.addAttribute("memberTrainingsToday", dashboardMemberService.getTrainingsToday(employeeId));
                    List<DashboardMember> setSortedDashboardMember =  
                            dashboardMemberService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardMember> sortedDashboardMember = setSortedDashboardMember
                            .stream().sorted((e1, e2) ->
                    e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("memberCourses", sortedDashboardMember);
                } catch(Exception e) {
                    model.addAttribute("errorMember1", e.getMessage());
                }
                try {
                model.addAttribute("instructorCoursesToday", dashboardInstructorService.getCoursesToday(employeeId));
                List<DashboardInstructor> setSortedDashboardInstructor =  
                        dashboardInstructorService.findCourses(employeeId).stream()
                        .collect(Collectors.toCollection(ArrayList::new));
                List<DashboardInstructor> sortedDashboardInstructor = setSortedDashboardInstructor
                        .stream().sorted((e1, e2) ->
                        e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                model.addAttribute("instructorCourses", sortedDashboardInstructor);
                } catch(Exception e) {
                    model.addAttribute("errorInstructor1", e.getMessage());
                }
                
            } else if (user.getRoles().contains("Instructor") && user.getRoles().contains("PMO")) {
                try {
                    model.addAttribute("instructorCoursesToday", dashboardInstructorService.getCoursesToday(employeeId));
                    List<DashboardInstructor> setSortedDashboardInstructor =  
                            dashboardInstructorService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardInstructor> sortedDashboardInstructor = setSortedDashboardInstructor
                            .stream().sorted((e1, e2) ->
                            e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("instructorCourses", sortedDashboardInstructor);
                } catch(Exception e) {
                    model.addAttribute("errorInstructor2", e.getMessage());
                }
                try {
                    List<DashboardPmo> setSortedDashboardPmo =  
                            dashboardPmoService.findCourses().stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardPmo> sortedDashboardPmo = setSortedDashboardPmo
                            .stream().sorted((e1, e2) ->
                            e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("pmoCourses", sortedDashboardPmo);
                } catch(Exception e) {
                    model.addAttribute("errorPmo1", e.getMessage());
                }
            } else if (user.getRoles().contains("PMO") && user.getRoles().contains("Member")) {
                try {
                    model.addAttribute("memberActiveCourses", dashboardMemberService.getActiveCourses(employeeId));
                    model.addAttribute("memberTrainingsToday", dashboardMemberService.getTrainingsToday(employeeId));
                    List<DashboardMember> setSortedDashboardMember =  
                            dashboardMemberService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardMember> sortedDashboardMember = setSortedDashboardMember
                            .stream().sorted((e1, e2) ->
                    e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("memberCourses", sortedDashboardMember);
                } catch(Exception e) {
                    model.addAttribute("errorMember2", e.getMessage());
                }
                try {
                    List<DashboardPmo> setSortedDashboardPmo =  
                            dashboardPmoService.findCourses().stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardPmo> sortedDashboardPmo = setSortedDashboardPmo
                            .stream().sorted((e1, e2) ->
                            e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("pmoCourses", sortedDashboardPmo);
                } catch(Exception e) {
                    model.addAttribute("errorPmo2", e.getMessage());
                }
            }
        } else if(user.getRoles().size() == 3) {
            if(user.getRoles().contains("Member") && user.getRoles().contains("Instructor") && user.getRoles().contains("PMO")) {
                try {
                    model.addAttribute("memberActiveCourses", dashboardMemberService.getActiveCourses(employeeId));
                    model.addAttribute("memberTrainingsToday", dashboardMemberService.getTrainingsToday(employeeId));
                    List<DashboardMember> setSortedDashboardMember =  
                            dashboardMemberService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardMember> sortedDashboardMember = setSortedDashboardMember
                            .stream().sorted((e1, e2) ->
                    e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("memberCourses", sortedDashboardMember);
                } catch(Exception e) {
                    model.addAttribute("errorMember3", e.getMessage());
                }
                try {
                    model.addAttribute("instructorCoursesToday", dashboardInstructorService.getCoursesToday(employeeId));
                    List<DashboardInstructor> setSortedDashboardInstructor =  
                            dashboardInstructorService.findCourses(employeeId).stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardInstructor> sortedDashboardInstructor = setSortedDashboardInstructor
                            .stream().sorted((e1, e2) ->
                            e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("instructorCourses", sortedDashboardInstructor);
                } catch(Exception e) {
                    model.addAttribute("errorInstructor3", e.getMessage());
                }
                try {
                    List<DashboardPmo> setSortedDashboardPmo =  
                            dashboardPmoService.findCourses().stream()
                            .collect(Collectors.toCollection(ArrayList::new));
                    List<DashboardPmo> sortedDashboardPmo = setSortedDashboardPmo
                            .stream().sorted((e1, e2) ->
                            e1.getStartDateTime().compareTo(e2.getStartDateTime())).collect(Collectors.toList());
                    model.addAttribute("pmoCourses", sortedDashboardPmo);
                } catch(Exception e) {
                    model.addAttribute("errorPmo3", e.getMessage());
                }
            }
        }

        return "dashboard";

    }
}
