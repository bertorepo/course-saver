package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImp implements CourseService {
	 @Autowired
	    private CourseDao cdao;

	    @Override
	    public void save(Course course) {
	        cdao.save(course);
	        if (course.getId() == 0) {
	            throw new IllegalArgumentException("Course was not saved");
	        }
	    }

	    @Override
	    public Set<Course> findAll() {
	        Set<Course> cc = cdao.findAll();
	        if (cc.isEmpty() || cc == null) {
	            throw new IllegalArgumentException("No Course Found");
	        }
	        return cc;
	    }

		@Override
		public Course findById(Long id) {
			 Course c = cdao.findById(id);
		        if (c.getId() == 0) {
		            throw new IllegalArgumentException("Invalid Course");
		        }
		        return c;
			
		}
	  }
