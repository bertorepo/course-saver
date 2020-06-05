package com.fujitsu.ph.tsup.domain.deguzman;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseScheduleDaoImpl implements CourseScheduleDao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Set<CourseSchedule> findAll() {
        String sql = "SELECT id, course_Id, instructor_Id, venue_Id, min_Required, max_Allowed, status FROM COURSESCHEDULE";
        Set<CourseSchedule> courseSchedule = (Set<CourseSchedule>) template.query(sql, new CourseScheduleRowMapper());
        return courseSchedule;
    }

    @Override
    public CourseSchedule findById(Long id) {
        String sql = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSESCHEDULE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new CourseScheduleRowMapper());
    }

    @Override
    public Long saveLong(CourseSchedule courseSchedule) {
        String sql = "INSERT INTO COURSESCHEDULE(course_id, instructor_id, venue_id, min_required, max_allowed, status)"
                + "VALUES(:courseId, :instructorId, :venueId, :minRequired, :maxAllowed, :status)";
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseId",courseSchedule.getCourseId())
                .addValue("instructorId", courseSchedule.getInstructorId())
                .addValue("venueId", courseSchedule.getVenueId())
                .addValue("minRequired", courseSchedule.getMinRequired())
                .addValue("maxAllowed", courseSchedule.getMaxAllowed())
                .addValue("status", courseSchedule.getStatus());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(sql, namedParameters, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public void save(CourseSchedule courseSchedule) {
        // TODO Auto-generated method stub
        return;
    }


}
