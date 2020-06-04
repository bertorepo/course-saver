package com.fujitsu.ph.tsup.domain.ramos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class CourseServiceImpl {
private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private CourseDao dao;

	public Course getCourseId(Long courseId) {
		
		Course c = dao.findById(courseId);
		if (c.getId() == courseId) {
			throw new IllegalArgumentException("Course not found");
		}
		
		return c;
		
	}

}

