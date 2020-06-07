package com.fujitsu.ph.tsup.domain.oviedo;

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

	@Override
	public void save(CourseSchedule cs) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long saveCourseSchedule(CourseSchedule cs) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO COURSESCHEDULE(id,CourseId, VenueId, InstructorId,MinReq,MaxAllowed,Status)"
				+ "VALUES(:id, :cId,:vId,:iId,:min,:max,:status)";

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", cs.getId())
				.addValue("cId", cs.getCourseId())
				.addValue("vId", cs.getVenueId())
				.addValue("iId", cs.getInstructorId())
				.addValue("min", cs.getMinReq())
				.addValue("max", cs.getMaxAllowed())
				.addValue("max", cs.getMaxAllowed());

		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		template.update(sql, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKeys().get("id");
	}

	@Override
	public Set<CourseSchedule> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT id, CourseId, VenueId, InstructorId, MinRequired, MaxRequired, status FROM COURSESCHEDULE";
		List<CourseSchedule> courseSchedule = template.query(sql, new CourseScheduleRowMapper());
		Set<CourseSchedule> result = new HashSet<CourseSchedule>(courseSchedule);
		return result;
	}

	@Override
	public CourseSchedule findById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id, CourseId, VenueId, InstructorId, MinRequired, MaxRequired, status FROM COURSESCHEDULE WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, namedParameters, new CourseScheduleRowMapper());
	}

}
