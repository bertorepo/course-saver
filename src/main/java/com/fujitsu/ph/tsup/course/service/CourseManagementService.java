/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.service;

import java.util.Set;

import com.fujitsu.ph.tsup.course.model.Course;

/**
 * CourseManagementService Class
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 *
 */
public interface CourseManagementService {

    void deleteCourseById(Long id);

    Course findCourseById(Long id);
    
    Set<Course> findAllCourses();
    
    Set<Course> findCoursesByName(String name);
    
    void createCourse(Course course);

}
