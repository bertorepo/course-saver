//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : Summary of G3CC Standardization Training for Dev                                                                                                                                                             
// Class Name   : SummaryGSTDevDao.java                                                                                                                                                                            
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.01   | 2021/04/21 | WS)R.Rivero            | New Creation
// 0.02   | 2021/04/29 | WS)G.Cabiling          | Update 
//==================================================================================================

package com.fujitsu.ph.tsup.report.summary.dao;

import java.time.ZonedDateTime;
import java.util.Set;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for Dev
//Class Name   : SummaryGSTDevDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    |    ---     | WS) r.rivero          | Initial Version
//0.01    |    ---     | WS) g.cabiling        | Initial Version
//0.02    | 2021/06/14 | WS) m.padaca          | Updated
//==================================================================================================
/**
 * <pre>
 * The interface of G3CC standardization training for dev dao
 * </pre>
 * 
 * @version 0.01
 * @author r.rivero
 * @author g.cabiling
 * @version 0.02
 * @author m.padaca
 */

public interface SummaryGSTDevDao {
	
	// Find All Courses By Category ID
    Set<Long> findAllCoursesByCategoryId();
   
    // Find All JDU Dev
    Set<Long> findAllJDUDev();
    
    // Find All JDU Dev Last Week
    int findAllJDUDevLastWeek();
    
    // Find All JDU Existing Members
    int findAllJDUExisitingMembers();
    
    // Find All JDU New Members
    int findAllJDUNewMembers();

    // Find Total Course of JDU Dev Finished
    int findTotalCoursePerEmployee(Set<Long> course_id, Long participant_id, ZonedDateTime EndDate);

    // Find Total Course of JDU Dev Finished Last Week
    int findTotalCoursePerEmployeeLastWeek(ZonedDateTime startDate, ZonedDateTime EndDate, Set<Long> course_id, Long participant_id);

}
