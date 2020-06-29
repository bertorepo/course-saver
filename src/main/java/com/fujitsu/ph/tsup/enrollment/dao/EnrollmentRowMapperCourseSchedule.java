package com.fujitsu.ph.tsup.enrollment.dao;

//=================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentRowMapperCourseSchedule.java
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

import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;

public class EnrollmentRowMapperCourseSchedule implements RowMapper<CourseSchedule> {

    @Override
    public CourseSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");

        CourseSchedule courseSchedule = new CourseSchedule.Builder(id).build();
        return courseSchedule;
    }

}
