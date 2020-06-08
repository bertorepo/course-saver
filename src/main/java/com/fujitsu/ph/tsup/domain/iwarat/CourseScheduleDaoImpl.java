package com.fujitsu.ph.tsup.domain.iwarat;

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
public class CourseScheduleDaoImpl implements CourseScheduleDao {
    @Autowired
    private NamedParameterJdbcTemplate template;

    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public void save(CourseSchedule courseSchedule) {
        String sql = "INSERT INTO COURSE_SCHEDULE(course_id, instructor_id, venue_id, min_required, max_allowed, status)"
                + "VALUES(:courseId, :instructorId, :venueId, :minRequired, :maxAllowed, :status)";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseId", courseSchedule.getCourseId())
                .addValue("instructorId", courseSchedule.getInstructorId())
                .addValue("venueId", courseSchedule.getVenueId())
                .addValue("minRequired", courseSchedule.getMinRequired())
                .addValue("maxAllowed", courseSchedule.getMaxAllowed())
                .addValue("status", courseSchedule.getStatus());

        template.update(sql, namedParameters, generatedKeyHolder);
        GeneratedKeyHolderId();
    }

    @Override
    public Long GeneratedKeyHolderId() {
        return (Long) generatedKeyHolder.getKeys().get("id");
    }

    @Override
    public Set<CourseSchedule> findAll() {
        String sql = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSE_SCHEDULE";
        List<CourseSchedule> listCourseSchedule = template.query(sql, new CourseScheduleRowMapper());
        Set<CourseSchedule> setCourseSchedule = new HashSet<CourseSchedule>(listCourseSchedule);

        return setCourseSchedule;
    }

    @Override
    public CourseSchedule findById(Long id) {
        String sql = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSE_SCHEDULE WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, namedParameters, new CourseScheduleRowMapper());
    }

}