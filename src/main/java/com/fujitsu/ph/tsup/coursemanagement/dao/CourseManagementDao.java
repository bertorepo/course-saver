package com.fujitsu.ph.tsup.coursemanagement.dao;

import com.fujitsu.ph.tsup.coursemanagement.model.Course;

public interface CourseManagementDao {

    Course findCourseById(Long id);

    void deleteCourseById(Long id);
    
    /**
     * Finds course by name
     * 
     */
    Set<Course> findCourseByName(String name);
    
    void createCourse(Course course);
}
