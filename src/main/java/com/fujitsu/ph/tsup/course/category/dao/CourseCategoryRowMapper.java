package com.fujitsu.ph.tsup.course.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca (New Creation by: a.batongbaca)
 * @version Revision: 0.01 Date: 2021-01-18
 *
 */
public class CourseCategoryRowMapper implements RowMapper<CourseCategory> {

    @Override
    public CourseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String category = rs.getString("category");
        String detail = rs.getString("detail");

        CourseCategory courseCategory = new CourseCategory.Builder(id, category).detail(detail).build();

        return courseCategory;
    }

}