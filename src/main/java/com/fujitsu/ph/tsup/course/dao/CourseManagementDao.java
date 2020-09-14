/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.course.model.Course;

/**
 * CourseManagementDao class
 * 
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 */
public interface CourseManagementDao {

    // Method for finding Course by Id
    Course findCourseById(Long id);

    // Method for deleting Course by Id
    void deleteCourseById(Long id);
    
    // Method for loading all course in Course View
    Set<Course> findAllCourses();
    
    // Method for searching Courses by Name
    Set<Course> findCoursesByName(String name);
}
