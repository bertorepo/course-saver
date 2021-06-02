/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Page<Course> findAllCourses(Pageable pageable) {
	
	List<Course> courses = courseManagementDao.findAllCourses(pageable)
						  .stream()
						  .collect(Collectors.toList());
	
	int countCourse = courseManagementDao.countCourse();
	
	return new PageImpl<>(courses,pageable,countCourse);
    }
    

    @Override
    public Set<Course> findCoursesByName(String name) {

    	Set<Course> courseFormList = courseManagementDao.findCoursesByName(name);
    	
    	try {
    		
        	if(courseFormList == null || courseFormList.isEmpty()) {
        		
        		return null;
        		
        	} else {
        		
        		return courseFormList;
        		
        	}
        	
    	} catch(Exception ex) {
    		
    		ex.printStackTrace();
    		
    	}
    	
    	 return courseFormList;
    	
    
    }
    
    /**
     * Author: WS)I.Fajardo
     * Find if Course name already exists
     * @param name Course name
     * @param id Course id
     * @return isCourseExists
     */
    @Override
    public boolean findIfCourseNameExists(String name, Long id) {
        Set<Course> courseList = courseManagementDao.findIfCourseNameExists(name, id);
        boolean isCourseExists = false;
        try {
            if (!courseList.isEmpty()) {
            	isCourseExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCourseExists;
    }
    
    /**
     * Author: WS)I.Fajardo
     * Creates course.
     */
    public void createCourse(Course course) {
    	
    	try {
    		courseManagementDao.createCourse(course);
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("Can't create new course");
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
	try {
	    courseManagementDao.updateCourse(course);
	} catch (DataAccessException ex) {
	    LOGGER.error(ex.getMessage(), ex);
	    throw new IllegalArgumentException("Can't update course");
	}

    }
    
}
