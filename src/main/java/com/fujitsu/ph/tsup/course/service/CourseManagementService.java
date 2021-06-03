/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fujitsu.ph.tsup.course.model.Course;

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
    Page<Course> findAllCourses(Pageable pageable);
    
    Set<Course> findCoursesByName(String name);
    
    void createCourse(Course course);
    
    // Method for updating a course
    void updateCourse(Course course);
    
    // Method for loading all Course in Course View
    Set<Course> loadAllCourse();
    
    // Method for searching if Role is already existing
    boolean findIfCourseNameExists(String name, Long id);

}
