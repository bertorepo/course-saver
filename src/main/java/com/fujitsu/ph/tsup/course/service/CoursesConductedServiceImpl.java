package com.fujitsu.ph.tsup.course.service;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.course.dao.CoursesConductedDao;
import com.fujitsu.ph.tsup.course.model.CoursesConducted;

@Service
public class CoursesConductedServiceImpl implements CoursesConductedService {

	/**
	 * CourseConductedDao dao
	 */
	@Autowired
	private CoursesConductedDao coursesConductedDao;

	/**
	 * <pre>
	 * Finds all courses conducted based on the given date range Call
	 * coursesConductedDao.findAllCoursesConducted using the given selectedStartDateTime,
	 * selectedEndDateTime and return the result
	 * 
	 * <pre>
	 * 
	 * @param selectedStartDateTime
	 * @param selectedEndDateTime
	 */

	@Override
	public Set<CoursesConducted> findAllCoursesConducted(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {
																								
		try {
			return coursesConductedDao.findAllCoursesConducted(selectedStartDateTime, selectedEndDateTime);
		} catch (DataAccessException ex) {
			throw new IllegalArgumentException("Can't access selectedStartDateTime and selectedEndDateTime.");
		}
	}
	

}
