package com.fujitsu.ph.tsup.enrollment.dao;

//=================================================================================================
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :EnrollmentRowMapperCourseScheduleDetail.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+--------------------------------------------------
//0.01    | 06/29/2020 | WS) M.Lumontad        | New Creation
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

import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;

public class EnrollmentRowMapperCourseScheduleDetail implements RowMapper<CourseScheduleDetail> {

    @Override
    public CourseScheduleDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
