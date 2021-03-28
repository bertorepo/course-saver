//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : CoursesConductedDao																																								
// Class Name   : CoursesConductedDao.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/02/22 | WS)N.DeJesus          | New Creation																																											
//==================================================================================================																																																																																						
package com.fujitsu.ph.tsup.course.dao;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;

/**
 * <pre>
 * The controller for the CoursesConductedDao
 * </pre>
 * 
 * @author n.dejesus
 * @version 1.0.0
 */

public interface CoursesConductedDao {
	
	/**
	 * <pre>
     * Finds the courses conducted starting from today onwards
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime 
     */
	Set<CoursesConducted> findAllCoursesConducted(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime);
	


}
