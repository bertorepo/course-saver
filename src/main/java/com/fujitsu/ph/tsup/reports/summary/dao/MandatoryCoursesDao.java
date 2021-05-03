//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesDao                                                                                                                                                               
// Class Name   : MandatoryCoursesDao.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/21 | WS)J.Barbadillo       | New Creation                                                                                                                                                                          
//==================================================================================================                                                                                                                                                                                                                                                                                                                                                        
package com.fujitsu.ph.tsup.reports.summary.dao; 

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Set;


import com.fujitsu.ph.tsup.reports.summary.dao.*;
import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;

/**
* <pre>
*   The Dao for the MandatoryCoursesController
* </pre>
* 
* @author j.barbadillo
* @version 1.0.0
*/

public interface MandatoryCoursesDao {
    
    /**
     * <pre>
     *  Find the total number of  JDU based on the given date range
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     */
    Set<MandatoryCourses> findMandatoryCourses(LocalDateTime selectedStartDateTime,
            LocalDateTime selectedEndDateTime);
    
    
    /**
     * <pre>
     *  Count the total number of JDU
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     */
    int findTotalNumberOfJdu(ZonedDateTime selectedStartDateTime,
            ZonedDateTime selectedEndDateTime);
    
    /**
     * <pre>
     *  Find the total number of  JDU who finished training  based on the given date range  
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     */
    int findTotalNumberOfJduWhoFinishedTraining(ZonedDateTime selectedStartDateTime,
            ZonedDateTime selectedEndDateTime);
    
    
    /**
     * <pre>
     *  Find the total number of  JDU who finished training lastweek  based on the given date range  
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     */
    int findTotalNumberOfJduWhoFinishedTrainingLastWeek(ZonedDateTime selectedStartDateTime,
            ZonedDateTime selectedEndDateTime);
    
}
