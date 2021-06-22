/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fujitsu.ph.tsup.course.model.Course;
import com.fujitsu.ph.tsup.search.CourseSearchFilter;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Course Management
//Class Name   : CourseManagementService.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2020/08/28 | WS) c.lepiten       | Initial Version
//0.02    | 2021/04/16 | WS) i.fajardo       | Updated
//0.03    | 2021/05/27 | WS) mi.aguinaldo    | Added updateCourse
//==================================================================================================

public interface CourseManagementService {

    void deleteCourseById(Long id);

    Course findCourseById(Long id);
    
    Set<Course> findAllCourses();
   
    // Method for finding all courses base on Pageable object provided
    Page<Course> findAllCourses(Pageable pagable);
    
    Course findCoursesByName(String name);
    
    Set<Course> findCoursesByCourseSearchFilter(CourseSearchFilter courseSearchFilter);
    
    void createCourse(Course course);
    
    // Method for updating a course
    void updateCourse(Course course);
    
    // Method for loading all Course in Course View
    Set<Course> loadAllCourse();
    
    // Method for checking if course name already exists 
    boolean courseNameExists(String name, Long id);
    
    boolean courseNameExists(String name);

}
