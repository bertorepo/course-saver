package com.fujitsu.ph.tsup.coursemanagement.service;

import com.fujitsu.ph.tsup.coursemanagement.model.Course;


public interface CourseManagementService {

    void deleteCourseById(Long id);

    Course findCourseById(Long id);
    
    /**
     * Author: WS)C.Arias
     * <pre>
     * Finds course by name
     * <pre>
     * 
     * @return Set<Course>
     */
    Set<Course> findCourseByName(String name);
    
    void createCourse(Course course);

}
