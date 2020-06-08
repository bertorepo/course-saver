package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;


public class CourseServiceImp implements CourseService {
	 @Autowired
	    private CourseDao cdao;

	    @Override
	    public void save(Course course) {
	    	 if (course.getId() != 0) {
	             cdao.save(course);
	         } else if (course.getId() <= 0) {
	             throw new CourseException("Course Id should not be zero or less than zero.");
	         }  
	     }
	     
	    @Override
	    public Set<Course> findAll() {
	    	 Set<Course> CourseList = cdao.findAll();
	         try {
	             if(CourseList.isEmpty() || CourseList == null) {
	                 throw new CourseException("No Course Details found match");
	             } else {
	                 return CourseList;
	             }    
	         } catch (DataAccessException e) {
	             throw new CourseException("No Course Details found match.");
	         } 
	     }

		@Override
		public Course findById(Long id) {
			try {
	            return cdao.findById(id);
	        } catch (DataAccessException vn) {
	            throw new CourseException("No Course ID match!", vn);
	        }
		}
}
