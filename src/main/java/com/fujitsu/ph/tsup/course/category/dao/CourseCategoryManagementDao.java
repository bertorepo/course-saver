/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * CourseManagementDao class
 * 
 * 
 *
 */
public interface CourseCategoryManagementDao {

	   // Method for finding Course Category by Id
    CourseCategory findCourseCategoryById(Long id);
    
    // Method for loading all course category in Course Category View
    Set<CourseCategory> findAllCourseCategory();
    
    // Method for searching Course Category by Name
    Set<CourseCategory> findCourseCategoryByName(String name);
}
