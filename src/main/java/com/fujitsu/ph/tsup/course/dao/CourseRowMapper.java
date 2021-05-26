/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.course.model.Course.Builder;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseRowMapper.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten         | Initial Version
//0.02    | 2021/04/19 | WS) st.diaz           | Updated
//0.03    | 2021/05/10 | WS) D.Escala          | Updated
//==================================================================================================

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String detail = rs.getString("detail");
        String isMandatory = rs.getString("mandatory");
        String deadline = rs.getString("deadline");
        Long courseCategoryId = rs.getLong("course_category_id");
        
	Builder coursBuilder = Course.builder()
				     .withId(id)
				     .withName(name)
				     .withDetail(detail)
				     .withIsMandatory(isMandatory)
				     .withDeadline(deadline)
				     .withCourseCategoryId(courseCategoryId);
	
	if (hasColumn(rs, "category")) {
	    String category = rs.getString("category");
	    CourseCategory courseCategory = new CourseCategory.Builder(courseCategoryId, category).build();
	    return coursBuilder
			 .withCourseCategory(courseCategory)
			 .build();
	}
	return coursBuilder.build();
    }

    private static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	ResultSetMetaData rsmd = rs.getMetaData();
	int columns = rsmd.getColumnCount();
	for (int x = 1; x <= columns; x++) {
	    if (columnName.equals(rsmd.getColumnName(x))) {
		return true;
	    }
	}
	return false;
    }

}
