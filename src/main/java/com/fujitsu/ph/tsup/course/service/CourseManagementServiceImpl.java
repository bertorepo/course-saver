/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.dao.CourseManagementDao;
import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.exception.TsupException;
import com.fujitsu.ph.tsup.search.CourseSearchFilter;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseManagementServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten       | Initial Version
//0.02    | 2021/04/20 | WS) i.fajardo       | Updated
//0.03	  | 2021/05/27 | WS) mi.aguinaldo    | Implemented update function
//0.04	  | 2021/07/2  | WS) mi.aguinaldo    | Implemented courseNameExists function
//==================================================================================================

@Service
public class CourseManagementServiceImpl implements CourseManagementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseManagementServiceImpl.class);

    @Autowired
    CourseManagementDao courseManagementDao;

    @Override
    public void deleteCourseById(Long id) {

        courseManagementDao.deleteCourseById(id);

    }

    @Override
    public Course findCourseById(Long id) {

        Course courseResult = courseManagementDao.findCourseById(id);

        return courseResult;
    }

    @Override
    public Set<Course> findAllCourses() {

        return courseManagementDao.findAllCourses();
    }
    
    @Override
    public Page<Course> findAllCourses(Pageable pagable) {
	
	List<Course> courses = courseManagementDao.findAllCourses(pagable)
						  .stream()
						  .collect(Collectors.toList());
	
	int countCourse = courseManagementDao.countCourse();
	
	return new PageImpl<>(courses,pagable,countCourse);
    }
    
    @Override
    public Set<Course> findCoursesByCourseSearchFilter(CourseSearchFilter searchCriteria) {
	Set<Course> courses = courseManagementDao.findCoursesByCourseSearchFilter(searchCriteria);
	return  courses.isEmpty() ? Collections.emptySet() : courses;
    }

    @Override
    public Course findCoursesByName(String name) {
    	Optional<Course> course = courseManagementDao.findCoursesByName(name);
    	
    	return course.orElse(null);
    }
    
    @Override
    public boolean courseNameExists(String name) {
	return courseManagementDao.findCoursesByName(name).isPresent();
    }

    /**
     * Author: WS)I.Fajardo
     * Find if Course name already exists
     * @param name Course name
     * @param id Course id
     * @return isCourseExists
     */
    @Override
    public boolean courseNameExists(String name, Long id) {
	Predicate<Course> notSameId = course -> !Objects.equals(course.getId(), id);
	Predicate<Course> sameCourseName = course -> Objects.equals(course.getName(), name);

	return courseManagementDao.findCoursesByName(name)
				  .filter(notSameId.and(sameCourseName))
				  .isPresent();
    }
    
    /**
     * Author: WS)I.Fajardo
     * Creates course.
     */
    public void createCourse(Course course) {

	try {
	    courseManagementDao.createCourse(course);
	} catch (DataAccessException ex) {
	    throw new TsupException("Can't create new course", ex.getCause());
	}
    }
    
    /**
     * Loads all course
     * Author: WS)I.Fajardo
     * 
     * @return courseManagementDao.loadAllCourse
     */
    @Override
    public Set<Course> loadAllCourse() {
        return courseManagementDao.loadAllCourse();
    }

    /**
     * Update a course
     *@param course; Course object to be update
     */
    @Override
    public void updateCourse(Course course) {
	String courseName = StringUtils.trim(course.getName());
	if (courseNameExists(courseName, course.getId())) {
	    throw new TsupException(courseName + ", Course Name Already Exists");
	}
	
	try {
	    courseManagementDao.updateCourse(course);
	} catch (DataAccessException ex) {
	    throw new TsupException("Can't create new course", ex.getCause());
	}

    }

}
