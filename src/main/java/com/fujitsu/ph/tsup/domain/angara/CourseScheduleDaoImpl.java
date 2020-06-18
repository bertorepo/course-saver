package com.fujitsu.ph.tsup.domain.angara;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseScheduleDaoImpl implements CourseScheduleDao {

    @Autowired
    private JdbcTemplate template;
    KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

    @Override
    public Set<CourseSchedule> findAll() {
        String sql = "SELECT id, course_id, instructor_id, venue_id, max_required, min_required, status)";
        List<CourseSchedule> courseSchedule = template.query(sql, new CourseScheduleRowMapper());
        Set<CourseSchedule> cs = new HashSet<CourseSchedule>(courseSchedule);
        return cs;
    }

    @Override
    public Long saveLong1(CourseSchedule courseSchedule) {
        String sql = "INSERT INTO COURSE_SCHEDULE(course_id, instructor_id, venue_id, max_required, min_required, status"
                + "VALUES(:courseId, :instructorId, :venueId, :maxRequired, :minRequired, :status)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("courseId", courseSchedule.getCourseId())
                .addValue("instructorId", courseSchedule.getInstructorId())
                .addValue("venueId", courseSchedule.getVenueId())
                .addValue("maxRequired", courseSchedule.getMaxRequired())
                .addValue("minRequired", courseSchedule.getMinRequired())
                .addValue("status", courseSchedule.getStatus());

        template.update(sql, namedParameters, generatedKeyHolder);
        return null;

    }

    @Override
    public CourseSchedule findById(Long id) {
        String sql = "SELECT id, course_id, instructor_id, venue_id, max_required, min_required, status)";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return (CourseSchedule) template.queryForMap(sql, namedParameters, new CourseScheduleRowMapper());
    }

    @Override
    public Long save(CourseSchedule courseSchedule) {
        return null;

    }

}
