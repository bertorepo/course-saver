/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.roletype.domain.RoleType;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseManagementDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten       | Initial Version
//0.02    | 2021/04/20 | WS) i.fajardo       | Updated
//==================================================================================================
public interface CourseManagementDao {

    // Method for finding Course by Id
    Course findCourseById(Long id);

    // Method for deleting Course by Id
    void deleteCourseById(Long id);
    
    // Method for loading all course in Course View
    Set<Course> findAllCourses();
    
    // Method for searching Courses by Name
    Set<Course> findCoursesByName(String name);
    
    // Method for searching if Course already exists
    Set<Course> findIfCourseNameExists(String name, Long id);
    
    //Method for creating courses
    void createCourse(Course course);
    
	 // Method for loading all Course in Course View
    Set<Course> loadAllCourse();
}
