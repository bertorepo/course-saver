//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : CoursesConductedService																																								
// Class Name   : CoursesConductedService.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation																																											
//==================================================================================================	
package com.fujitsu.ph.tsup.course.service;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.course.model.CoursesConducted;

/**
 * <pre>
 * The controller for the CoursesConductedService
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */

public interface CoursesConductedService {
	
	 /**
     * <pre>
     * Finds all courses conducted based on the given date range
     * </pre>
     * @param selectedStartDateTime
     * @param selectedEndDateTime
     * @return Set<CoursesConducted>
     */
	Set<CoursesConducted> findAllCoursesConducted(ZonedDateTime selectedStartDateTime, 
			ZonedDateTime  selectedEndDateTime);

}
