package com.fujitsu.ph.tsup.dashboard.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.dashboard.dao.DashboardMemberDao;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardMemberForm;

//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardMemberServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) Jm.Deguzman   | New Creation
//==================================================================================================
/**
* <pre>
* The service implementation of the dashboard, calls the Dao to get the data
* <pre>
* 
* @version 0.01
* @author Jm.Deguzman
*/
@Service
public class DashboardMemberServiceImpl implements DashboardMemberService{
    
    /*
     * DashboardMemberDao
     */
    @Autowired
    private DashboardMemberDao dao;
    
    /**
     * Finds the courses that the member haven't enrolled to yet
     * @param employeeId
     * @return Sat<DashboardMemberForm>
     */
    @Override
    public Set<DashboardMemberForm> findCourses(Long employeeId){
        Set<DashboardMemberForm> dashboardMember = dao.findCourses(employeeId);
        if (dashboardMember.isEmpty() || dashboardMember == null) {
            throw new IllegalArgumentException("No records found");
        } 
        return dashboardMember;
    }

    /**
     * Gets the number of trainings that the member has on this day
     * 
     * @param employeeId
     * @return integer
     */
    @Override
    public int getTrainingsToday(Long employeeId) {
        int trainingsToday = dao.getTrainingsToday(employeeId);
        return trainingsToday;
    }
    /**
     * Gets the number of active courses that the member has enrolled to
     * 
     * @param employeeId
     * @return integer
     */
    @Override
    public int getActiveCourses(Long employeeId) {
        int activeCourses = dao.getActiveCourses(employeeId);

        return activeCourses;
    }
}
