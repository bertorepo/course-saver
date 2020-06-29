package com.fujitsu.ph.tsup.dashboard.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.dashboard.dao.DashboardInstructorDao;
import com.fujitsu.ph.tsup.dashboard.domain.DashboardInstructorForm;

//==================================================================================================
//$Id:$
//Project Name :Training Sign Up
//System Name  :Dashboard
//Class Name   :DashboardInstructorServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/24/2020 |  WS) Jm.Deguzman   | New Creation
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
public class DashboardInstructorServiceImpl implements DashboardInstructorService{
    
    /*
     * DashboardInstructorDao
     */
    @Autowired
    private DashboardInstructorDao dao;
    
    /**
     * Finds the courses that the instructor have
     * @param employeeId
     * @return Set<DashboardInstructorForm>
     */
    @Override
    public Set<DashboardInstructorForm> findCourses(Long employeeId){
        Set<DashboardInstructorForm> dashboardInstructor = dao.findCourses(employeeId);
        return dashboardInstructor;
    }

    /**
     * Gets the number of courses that the instructor has for the day
     * @param employeeId
     * @return integer
     */
    @Override
    public int getCoursesToday(Long employeeId) {
        return dao.getCoursesToday(employeeId);
    }
}
