package com.fujitsu.ph.tsup.coursemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.coursemanagement.dao.CourseManagementDao;
import com.fujitsu.ph.tsup.coursemanagement.model.Course;

@Service
public class CourseManagementServiceImpl implements CourseManagementService {

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
    
    /**
     * Author: WS)C.Arias
     * Finds course by name.
     * If empty or null, call the createCourse method.
     */
    @Override
    public Set<Course> findCourseByName(String name) {
    	
    	Set<Course> courseFormList = courseManagementDao.findCourseByName(name);
    	
    	try {
    		
        	if(courseFormList == null || courseFormList.isEmpty()) {
        		
        		//call createCourse method
        		System.out.println("todo #2: call the createCourse method");
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
     * Author: WS)C.Arias
     * Creates course.
     */
    public void createCourse(Course course) {
    	
    	//call the dao method
    	courseManagementDao.createCourse(course);
    	
    }

}
