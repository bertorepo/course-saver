package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CourseScheduleServiceImp implements CourseScheduleService {

	@Autowired
	private CourseScheduleDao csdao;

	@Override
	public void save(CourseSchedule courseSchedule) {
		if (courseSchedule.getId() != 0) {
            csdao.save(courseSchedule);
        } else if (courseSchedule.getId() <= 0) {
            throw new CourseScheduleException("Course Schedule Id should not be zero or less than zero.");
        }
		
	}
	
	@Override
	public Set<CourseSchedule> findAll() {
		 Set<CourseSchedule> CourseScheduleList = csdao.findAll();
		 try {
	            if(CourseScheduleList.isEmpty() || CourseScheduleList == null) {
	                throw new CourseScheduleException("Can't find Course Schedule Details");
	            } else {
	                return CourseScheduleList;
	            }    
	        } catch (DataAccessException ex) {
	            throw new CourseScheduleException("Can't access Course Schedule Details.");
	        }
	        
	}
	
	@Override
	public CourseSchedule findById(Long Id) {
		 try {
	            return csdao.findById(Id);
	        } catch (DataAccessException ex) {
	            throw new CourseScheduleException("Course Schedule not found!", ex);
	        }
	   }
	
}