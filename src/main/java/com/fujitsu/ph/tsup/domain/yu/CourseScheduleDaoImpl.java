package com.fujitsu.ph.tsup.domain.yu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

public class CourseScheduleDaoImpl implements CourseScheduleDao {
    
    @Autowired
    private NamedParameterJdbcTemplate template;
    
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(CourseSchedule id) {
        String query = "INSERT INTO COURSESCHEDULE(course_id, instructor_id, venue_id, min_required, max_allowed, status)"
                + "VALUES(:courseId, :instructorId, :venueId, :minRequired, :maxAllowed, :status)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseId", id.getCourseId())
                .addValue("instructorId", id.getInstructorId())
                .addValue("venueId", id.getVenueId())
                .addValue("minRequired", id.getMinRequired())
                .addValue("maxAllowed", id.getMaxAllowed())
                .addValue("status", id.getStatus());
        template.update(query, namedParameters, generatedKeyHolder);
        generatedKey();    
    }
    
    public Long generatedKey() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<CourseSchedule> findAll() {
        String query = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSESCHEDULE";
        List<CourseSchedule> courseScheduleList = template.query(query, new CourseScheduleRowMapper());
        Set<CourseSchedule> courseScheduleSet = new HashSet<CourseSchedule>(courseScheduleList);
        return courseScheduleSet;
    }

    @Override
    public CourseSchedule findById(Long id) {
        String query = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSESCHEDULE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, namedParameters, new CourseScheduleRowMapper());
    }

}
