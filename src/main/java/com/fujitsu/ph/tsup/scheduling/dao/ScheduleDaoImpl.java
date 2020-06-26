package com.fujitsu.ph.tsup.scheduling.dao;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.fujitsu.ph.tsup.scheduling.domain.CourseSchedule;
import com.fujitsu.ph.tsup.scheduling.domain.CourseScheduleDetail;
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
        String courseScheduleSql = "INSERT INTO COURSE_SCHEDULE"
                + "(ID, COURSE_ID, INSTRUCTOR_ID, VENUE_ID, MIN_REQUIRED, MAX_ALLOWED, STATUS) "+
                "VALUES (:id, :course_id, :instructor_id, :venue_id, :min_required, :max_allowed, :status)";
        
        SqlParameterSource courseSchedParameters = new MapSqlParameterSource()
                    .addValue("id", courseSchedule.getId())
                    .addValue("course_id", courseSchedule.getCourseId())
                    .addValue("instructor_id", courseSchedule.getInstructorId())
                    .addValue("venue_id", courseSchedule.getVenueId())
                    .addValue("min_required", courseSchedule.getMinRequired())
                    .addValue("max_allowed", courseSchedule.getMaxAllowed())
                    .addValue("status", courseSchedule.getStatus());
        template.update(courseScheduleSql, courseSchedParameters);
        
        String courseScheduleDetailSql = "INSERT INTO COURSE_SCHEDULE_DETAIL" + 
                "(ID, COURSE_SCHEDULE_ID, SCHEDULED_START_DATETIME, SCHEDULED_END_DATETIME, DURATION)"+
                "VALUES (:id, :course_schedule_id, :scheduled_start_datetime, :scheduled_end_datetime, "
                + ":duration";
        
        Set<CourseScheduleDetail> courseScheduleDetail = courseSchedule.getCourseScheduleDetail();
        
        for (CourseScheduleDetail courseSchedDetail : courseScheduleDetail) {
            SqlParameterSource courseSchedDetailParameters = new MapSqlParameterSource()
                    .addValue("id", courseSchedDetail.getId())
                    .addValue("course_schedule_id", courseSchedDetail.getCourseScheduleId())
                    .addValue("scheduled_start_datetime", courseSchedDetail.getScheduledStartDateTime())
                    .addValue("scheduled_end_datetime", courseSchedDetail.getScheduledEndDateTime())
                    .addValue("duration", courseSchedDetail.getDuration());
            template.update(courseScheduleDetailSql, courseSchedDetailParameters);
        }
    }
}
