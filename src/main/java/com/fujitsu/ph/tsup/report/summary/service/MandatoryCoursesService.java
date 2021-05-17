//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesService                                                                                                                                                               
// Class Name   : MandatoryCoursesService.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/21 | WS)C.Fuerzas          | New Creation      
// 1.0.1   | 2021/05/05 | WS)C.Fuerzas          | Updated
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.service;

import java.time.LocalDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.report.summary.model.MandatoryCourses;

/**
 * <pre>
 * Service for the MandatoryCoursesController
 * </pre>
 * @author c.fuerzas 
 * @version 1.0.1
 */
public interface MandatoryCoursesService {

	/**
	 * <pre>
     * Finds all the mandatory courses for all members based on the given date range
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime
     * @return Set of MandatoryCourses
     */
    public Set<MandatoryCourses> getMandatoryCourses(LocalDateTime
            selectedStartDateTime, LocalDateTime selectedEndDateTime);

    /**
     * Acquires the total number of JDU members.
     * @return long
     */
    public long getTotalNumberOfJduMembers();

    /**
     * Acquires the total number of completion for the specified course.
     * @param mandatoryCourse
     * @return long
     */
    public long getTotalNumberOfCompletion(String mandatoryCourse);

    /**
     * Acquires the total number of completion for the specified course within last
     * week
     * @param mandatoryCourse
     * @return long
     */
    public long getTotalNumberOfCompletionLastWeek(String mandatoryCourse);

    /**
     * Calculates the percentage completion per course
     * @return double
     */
    public double getPercentageCompletion();

    /**
     * Calculates the percentage completion per course within last week
     * @return double
     */
    public double getPercentageCompletionLastWeek();
}
