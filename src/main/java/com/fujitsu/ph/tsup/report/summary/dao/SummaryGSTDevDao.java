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

import java.util.Set;

/**
 * <pre>
 * The interface of G3CC standardization training for dev dao
 * </pre>
 * 
 * @version 0.01
 * @author r.rivero
 * @author g.cabiling
 *
 */

public interface SummaryGSTDevDao {
	
    Set<Long> findAllCoursesByCategoryId();

    Set<Long> findAllJDUDev();

    int findAllJDUDevLastWeek();

    int findAllJDUExisitingMembers();

    int findAllJDUNewMembers();

    int findTotalCoursePerEmployee(Set<Long> course_id, Long participant_id);

    int findTotalCoursePerEmployeeLastWeek(Set<Long> course_id, Long participant_id);

}
