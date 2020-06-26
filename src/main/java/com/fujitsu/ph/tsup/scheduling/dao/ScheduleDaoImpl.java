package com.fujitsu.ph.tsup.scheduling.dao;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.model.CourseForm;
import com.fujitsu.ph.tsup.scheduling.model.InstructorForm;
import com.fujitsu.ph.tsup.scheduling.model.VenueForm;

public class ScheduleDaoImpl implements ScheduleDao{
    
    @Autowired
    private NamedParameterJdbcTemplate namedParametertemplate;
    private JdbcTemplate jdbcTemplate;
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
        
        Set<CourseForm> courses = new HashSet<>();
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        
        for (Map<String,Object> rowMap : rows) {
            CourseForm courseForm = new CourseForm();
            
            courseForm.setId((Long) rowMap.get("ID"));
            courseForm.setName((String) rowMap.get("NAME"));
            courses.add(courseForm);
        }
        
        return courses;
    }

    @Override
    public Set<InstructorForm> findAllInstructors() {
    	 String query = "SELECT * FROM INSTRUCTOR";
         
         Set<InstructorForm> instructors = new HashSet<>();
         
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
         
         for (Map<String,Object> rowMap : rows) {
             InstructorForm instructorForm = new InstructorForm();
             
             instructorForm.setId((Long) rowMap.get("ID"));
             instructorForm.setName((String) rowMap.get("NAME"));
             instructors.add(instructorForm);
         }
         
         return instructors;
    }

    @Override
    public Set<VenueForm> findAllVenues() {
    	 String query = "SELECT * FROM VENUE";
         
         Set<VenueForm> venues = new HashSet<>();
         
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
         
         for (Map<String,Object> rowMap : rows) {
             VenueForm venueForm = new VenueForm();
             
             venueForm.setId((Long) rowMap.get("ID"));
             venueForm.setName((String) rowMap.get("NAME"));
             venues.add(venueForm);
         }
         
         return venues;
    }

    @Override
    public void saveCourseSchedule(CourseSchedule courseSchedule) {
        String courseScheduleSql = "INSERT INTO COURSE_SCHEDULE(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID) "+
                "VALUES (:id, :name)";
        
    }

}
