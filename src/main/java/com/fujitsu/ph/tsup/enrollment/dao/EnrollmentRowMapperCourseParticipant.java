package com.fujitsu.ph.tsup.enrollment.dao;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentRowMapperCourseParticipant.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.02    | 06/24/2020 | WS) M.Lumontad        | New Creation
//=================================================================================================
/**
* <pre>
* Interface for EnrollmentDaoImpl.java
* <pre>
* 
* @version 0.01
* @author m.lumontad                      
*/
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;

public class EnrollmentRowMapperCourseParticipant implements RowMapper<CourseParticipant> {

    @Override
    public CourseParticipant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long courseScheduleId = rs.getLong("courseScheduleId");
        Long participantId = rs.getLong("participantId");
        CourseParticipant courseParticipant = new CourseParticipant.Builder(courseScheduleId, participantId).build();
        return courseParticipant;
    }

}
