/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.dao;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.course.model.Course;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseManagementDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten       | Initial Version
//0.02    | 2021/04/20 | WS) i.fajardo       | Updated
//0.03    | 2021/05/10 | WS) D.Escala        | Updated
//0.04    | 2021/05/26 | WS) mi.aguinaldo    | Implemented update course function and update findCoursesByName and findAllCourses
//==================================================================================================
@Repository
public class CourseManagementDaoImpl implements CourseManagementDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Method for finding Course by Id
     */
    @Override
    public Course findCourseById(Long id) {

    	String query = "SELECT id,name,detail,mandatory,deadline,course_category_id FROM COURSE WHERE ID =" + id;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        return template.queryForObject(query, sqlParameterSource,
                new CourseRowMapper());
    }

    /**
     * Method for deleting Course by Id
     */
    @Override
    public void deleteCourseById(Long id) {

        String query = "DELETE FROM COURSE WHERE ID =" + id;

        SqlParameterSource namedParameters = new MapSqlParameterSource("id",
                id);
        template.update(query, namedParameters);

    }

    @Override
    public Set<Course> findAllCourses() {
	String query = "SELECT * " + 
		       "FROM course CE " +
		       "LEFT JOIN course_category CC " +
		       "ON CE.course_category_id = CC.id ";

        List<Course> courseList = template.query(query, new CourseRowMapper());
        Set<Course> courses = new LinkedHashSet<>(courseList);

        return courses;
    }
    
    
    
    @Override
    public Set<Course> findAllCourses(Pageable pageable) {
	Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Order.asc("CC.category");
	String query = "SELECT * " + 
		       "FROM course CE " +
		       "LEFT JOIN course_category CC " +
		       "ON CE.course_category_id = CC.id " +
		       "ORDER BY " + order.getProperty() + " " + order.getDirection() + " " +
		       "LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();

	List<Course> courseList = template.query(query, new CourseRowMapper());
	
	return courseList.isEmpty() ? Collections.emptySet() : new LinkedHashSet<>(courseList);
    }
    
    

    @Override
    public int countCourse() {
	return jdbcTemplate.queryForObject("SELECT count(*) FROM course", Integer.class);
    }

    @Override
    public Set<Course> findCoursesByName(String name) {
    	
	String query = "SELECT * " + 
		       "FROM course CE " +
		       "LEFT JOIN course_category CC " +
		       "ON CE.course_category_id = CC.id "+
		       "WHERE LOWER(name) LIKE LOWER('%" + name +"%')";
    	
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("name", name);
    	
    	List<Course> courseList = template.query(query, sqlParameterSource, new CourseRowMapper());
        Set<Course> courses = new LinkedHashSet<>(courseList);
    	
    	return courses;
    }
    
    @Override
    public void createCourse(Course course) {
    	
    	String query = "INSERT INTO course"
    			+ " (name, detail,mandatory,deadline,course_category_id)"
    			+ " VALUES(:name, :detail, :mandatory, :deadline,:course_category_id)";
    	
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("name", course.getName())
    			.addValue("detail", course.getDetail())
    			.addValue("mandatory", course.getIsMandatory())
    			.addValue("deadline", course.getDeadline())
    			.addValue("course_category_id", course.getCourseCategoryId());
    	
    	template.update(query, sqlParameterSource);
    	
    }
    
    /**
     * Find if course name is already existing
     * Author: WS)I.Fajardo
     * @param name Course name
     * @param id Course id
     * @return course
     */
    @Override
    public Set<Course> findIfCourseNameExists(String name, Long id) {
        String query = "SELECT * FROM COURSE WHERE LOWER(name) LIKE LOWER('" + name
                + "') AND id NOT IN (" + id + ")";
		
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("name", name);
        List<Course> courseList = template.query(query, sqlParameterSource, new CourseRowMapper());
        Set<Course> course = new LinkedHashSet<>(courseList);
        return course;
    }
    
    /**
     * Load all course
     * Author: WS)I.Fajardo
     * @return courses
     */
    @Override
    public Set<Course> loadAllCourse() {
        String query = "SELECT * FROM COURSE";

        List<Course> courseList = template.query(query, new CourseRowMapper());
        Set<Course> course = new LinkedHashSet<>(courseList);

        return course;
    }

    @Override
    public void updateCourse(Course course) {
	String query = "INSERT INTO course"
			+ " (id,name, detail,mandatory,deadline,course_category_id)"
			+ " VALUES(:id, :name, :detail, :mandatory, :deadline,:course_category_id)"
			+ " ON CONFLICT (id)"
			+ " DO UPDATE"
			+ " SET name = EXCLUDED.name, detail = EXCLUDED.detail,"
			+ " mandatory = EXCLUDED.mandatory, deadline = EXCLUDED.deadline,"
			+ " course_category_id = EXCLUDED.course_category_id";
	
	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
			.addValue("id", course.getId())
			.addValue("name", course.getName())
			.addValue("detail", course.getDetail())
			.addValue("mandatory", course.getIsMandatory())
			.addValue("deadline", course.getDeadline())
			.addValue("course_category_id", course.getCourseCategoryId());
	
	template.update(query, sqlParameterSource);
	
    }

}
