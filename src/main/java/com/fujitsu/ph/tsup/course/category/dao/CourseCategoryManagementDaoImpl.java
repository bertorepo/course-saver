/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * CourseManagementDao class
 * 
 * 
 * 
 */
@Repository
public class CourseCategoryManagementDaoImpl implements CourseCategoryManagementDao {

	// Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Method for finding Course by Id
     */
    @Override
    public CourseCategory findCourseCategoryById(Long id) {

        String query = "SELECT * FROM COURSE_CATEGORY WHERE ID =" + id;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        return template.queryForObject(query, sqlParameterSource,
                new CourseCategoryRowMapper());
    }
    
    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        String query = "SELECT * FROM COURSE_CATEGORY";

        List<CourseCategory> courseCategoryList = template.query(query, new CourseCategoryRowMapper());
        Set<CourseCategory> courseCategory = new LinkedHashSet<>(courseCategoryList);

        return courseCategory;
    }
    
    @Override
    public Set<CourseCategory> findCourseCategoryByName(String category) {
    	
    	String query = "SELECT * FROM COURSE_CATEGORY  WHERE LOWER(category) LIKE LOWER('%"+ category +"%')";
    	
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category", category);
    	
    	List<CourseCategory> courseCategoryList = template.query(query, sqlParameterSource, new CourseCategoryRowMapper());
        Set<CourseCategory> courseCategory = new LinkedHashSet<>(courseCategoryList);
    	
    	return courseCategory;
    }

}
