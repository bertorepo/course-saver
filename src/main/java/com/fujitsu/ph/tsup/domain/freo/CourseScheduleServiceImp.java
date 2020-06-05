package com.fujitsu.ph.tsup.domain.freo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleServiceImp implements CourseScheduleService {

	@Autowired
	private CourseScheduleDao csdao;
	
	@Override
	public void save(CourseSchedule courseSchedule) {
		 csdao.save(courseSchedule);
		 if(courseSchedule.getStatus() == "Open") {
	            throw new IllegalArgumentException("Course Schedule was not save");
	        }
	}
	
	@Override
	public Set<CourseSchedule> findAll() {
		  Set<CourseSchedule> cs= csdao.findAll();
	        
	        if (cs.isEmpty() || cs== null) {
	            throw new IllegalArgumentException("No records found");
	        } 
	        return cs;
	    }	        

	@Override
	public  CourseSchedule findById(Long Id) {
		 CourseSchedule cs = csdao.findById(Id);
	        if (cs.getStatus() == "C") {
	            throw new IllegalArgumentException("Course Schedule is closed");
	        }
	        return cs;        
	}
}