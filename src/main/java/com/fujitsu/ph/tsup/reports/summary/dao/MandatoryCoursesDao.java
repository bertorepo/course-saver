//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesDao                                                                                                                                                               
// Class Name   : MandatoryCoursesDao.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/21 | WS)J.Barbadillo       | New Creation        
// 1.0.1   | 2021/05/05 | WS)J.Barbadillo       | Updated
//==================================================================================================                                                                                                                                                                                                                                                                                                                                                        
package com.fujitsu.ph.tsup.reports.summary.dao; 

import java.time.LocalDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;

/**
* <pre>
*   The Dao for the MandatoryCoursesController
* </pre>
* 
* @author j.barbadillo
* @version 1.0.1
*/

public interface MandatoryCoursesDao {
    
    /**
     *  Find the total number of  JDU based on the given date range
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     * @return mandatoryCourses
     */
    Set<MandatoryCourses> findMandatoryCourses(LocalDateTime selectedStartDateTime,
            LocalDateTime selectedEndDateTime);
    
    
    /**
     *  Count the total number of JDU
     * @return int
     */
    int findTotalNumberOfJdu();
    
    /**
     *  Find the total number of  JDU who finished training  based on the courses  
     * @param mandatoryCourses
     * @return int
     */
    int findTotalNumberOfJduWhoFinishedTraining(String mandatoryCourses);
    
    /**
     *  Find the total number of  JDU who finished training lastweek  based on course
     * @param mandatoryCourses
     * @return int
     */
    int findTotalNumberOfJduWhoFinishedTrainingLastWeek(String mandatoryCourses);
    
}
