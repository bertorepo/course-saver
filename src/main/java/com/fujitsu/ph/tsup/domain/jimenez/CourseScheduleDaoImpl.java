package com.fujitsu.ph.tsup.domain.jimenez;

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


@Repository
public class CourseScheduleDaoImpl implements CourseScheduleDao{
    
    @Autowired
    private NamedParameterJdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(CourseSchedule CourseSchedule) {
        String sql = "INSERT INTO course_schedule(course_id, instructor_id, venue_id, min_required, max_allowed, status)"
                     + "VALUES(:course_id, :instructor_id, :venue_id, :min_required, :max_allowed, :status)";
        SqlParameterSource namedParams = new MapSqlParameterSource()
                .addValue("course_id", CourseSchedule.getCourseId())
                .addValue("instructor_id", CourseSchedule.getInstructorId())
                .addValue("venue_id", CourseSchedule.getVenueId())
                .addValue("min_required", CourseSchedule.getMinRequired())
                .addValue("max_allowed", CourseSchedule.getMaxAllowed())
                .addValue("status", String.valueOf(CourseSchedule.getStatus()));
        template.update(sql, namedParams, generatedKeyHolder);
        returnGeneratedKey();
    }

    @Override
    public Set<CourseSchedule> findAll() {
        String query = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM course_schedule";
        List<CourseSchedule> vnu = template.query(query, new CourseScheduleRowMapper());
        Set<CourseSchedule> CourseSchedule = new HashSet<CourseSchedule>(vnu);
        
        return CourseSchedule;
    }

    @Override
    public CourseSchedule findById(Long id) {
        String query = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM course_schedule WHERE id = :id";
        SqlParameterSource namedParams = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, namedParams, new CourseScheduleRowMapper());
    }
    
    @Override
    public Long returnGeneratedKey() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }
    
}