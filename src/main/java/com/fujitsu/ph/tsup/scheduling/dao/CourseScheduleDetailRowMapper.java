package com.fujitsu.ph.tsup.scheduling.dao;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseScheduleDetailRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/26/2020 | WS) J.Balanon   | New Creation
//
//
//=======================================================

/**
* <pre>
* Custom RowMapper Class for findAllScheduledCourses()
* <pre>
* @version 0.01
* @author j.balanon
*
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fujitsu.ph.tsup.scheduling.model.CourseScheduleDetailForm;
//import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;

import org.springframework.jdbc.core.RowMapper;
/**
* <pre>
* Custom RowMapper Class for findAllScheduledCourses()
* <pre>
* @version 0.01
* @author j.balanon
*
*/
public class CourseScheduleDetailRowMapper implements RowMapper <CourseScheduleDetailForm>{
	
	 /**
     * <pre>
     * Maps the Rows returned by ResultSet
     * <pre>
     * @param ResultSet csd
     * @param int rowNum
     * @throws SQLException
     */
    @Override
    public  CourseScheduleDetailForm mapRow(ResultSet csd, int rowNum) throws SQLException {
        
        CourseScheduleDetailForm courseScheduleDetail = new CourseScheduleDetailForm();

        Long id = csd.getLong("id");
        ZonedDateTime scheduledStartDateTime = 
                ZonedDateTime.ofInstant(csd.getTimestamp("scheduled_start_datetime").toInstant(),
                        ZoneId.of("UTC"));
        ZonedDateTime scheduledEndDateTime = 
                ZonedDateTime.ofInstant(csd.getTimestamp("scheduled_end_datetime").toInstant(),
                        ZoneId.of("UTC"));
        float duration = csd.getFloat("duration");
        
        courseScheduleDetail.setId(id);
        courseScheduleDetail.setScheduledEndDateTime(scheduledEndDateTime);
        courseScheduleDetail.setScheduledStartDateTime(scheduledStartDateTime);
        courseScheduleDetail.setDuration(duration);

        return courseScheduleDetail;
}
}