//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Reports                                                                                                                                                             
// Class Name   : ReportForNonAttendeesDao.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.1   | 2021/06/25 | WS)L.Celoso           | New Creation   
//==================================================================================================     
package com.fujitsu.ph.tsup.report.summary.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.report.summary.model.Attendee;

public interface ReportForAttendeesDao {
	
	 /**
     *  Find all employees and their incompleted mandatory courses
     * @param Mandatory Type
     * @param Employee ID 
     * @return Set of non-attendees
     */
    Set<Attendee> findAllNonAttendees(String mandatoryType, Long jdu_id);
    
	 /**
     *  Find all mandatory courses
     * @param Mandatory Type
     * @param Employee ID 
     * @return Set of courses
     */
    Set<Attendee> findMandatoryAllCourses(String mandatoryType, Long jdu_id);
	 
    /**
     *  Find all employees
     * @param Mandatory Type
     * @param Employee ID 
     * @return Set of employees
     */
    Set<Attendee> findEmployees(String mandatoryType, Long jdu_id);
    
    /**
     *  Get the JDU Type of the login user
     * @return JDU Type ID
     */
    Long getJDUType();
    
}
