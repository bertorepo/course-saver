package com.fujitsu.ph.tsup.scheduling.dao;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) JC.Jimenez  | New Creation
//
//
//=======================================================

/**
* <pre>
* Custom RowMapper Class for findAllCourses()
* <pre>
* @version 0.01
* @author jc.jimenez
*
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.scheduling.model.CourseForm;

public class CourseRowMapper implements RowMapper<CourseForm>{

    @Override
    public CourseForm mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseForm courseForm = new CourseForm();
        
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        courseForm.setId(id);
        courseForm.setName(name);
        
        return courseForm;
    }

}
