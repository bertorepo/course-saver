package com.fujitsu.ph.tsup.scheduling.dao;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public class ScheduleDaoImpl implements ScheduleDao{
    
    @Autowired
    private NamedParameterJdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime scheduledStartDateTime,
            ZonedDateTime scheduledEndDateTime) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<CourseForm> findAllCourses() {
        String query = "SELECT * FROM COURSE";
        
        List<CourseForm> courseList = template.query(query, new CourseRowMapper());
        Set<CourseForm> courses = new HashSet<>(courseList);
        
        return courses;
    }

    @Override
    public Set<InstructorForm> findAllInstructors() {
    	String query = "SELECT * FROM INSTRUCTOR";

  	   	List<InstructorForm> instructorList = template.query(query, new InstructorRowMapper());
  	   	Set<InstructorForm> instructors = new HashSet<>(instructorList);
       
        return instructors;
    }

    @Override
    public Set<VenueForm> findAllVenues() {
    	String query = "SELECT * FROM VENUE";
        
    	List<VenueForm> venueList = template.query(query, new VenueRowMapper());
        Set<VenueForm> venues = new HashSet<>(venueList);
           
        return venues;
    }

    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule) {
        String courseScheduleSql = "INSERT INTO COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID) "+
                "VALUES (:id, :name)";
        
    }

}
