package com.fujitsu.ph.tsup.domain.macabugao;

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
		generatedKey();
	}

	@Override
	public Set<CourseSchedule> findAll() {
		String query = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSE_SCHEDULE";
		List<CourseSchedule> courseScheduleList = template.query(query, new CourseScheduleRowMapper());
		Set<CourseSchedule> CourseSchedule = new HashSet<CourseSchedule>(courseScheduleList);

		return CourseSchedule;
	}

	@Override
	public CourseSchedule findById(Long id) {
		String query = "SELECT id, course_id, instructor_id, venue_id, min_required, max_allowed, status FROM COURSE_SCHEDULE WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(query, namedParameters, new CourseScheduleRowMapper());

	}

	@Override
	public Long generatedKey() {
		return (Long) generatedKeyHolder.getKeys().get("id");

	}

}
