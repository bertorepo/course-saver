/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.service;

import java.time.ZonedDateTime;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.reports.summary.dao.MandatoryCoursesDao;
import com.fujitsu.ph.tsup.reports.summary.dao.MandatoryCoursesDaoImpl;
import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCoursesForm;

/**
 * The implementation of mandatory courses services
 * 
 * @author c.fuerzas (New Creation by: c.fuerzas)
 * @version Revision: 0.01 Date: 2021-04-21
 */
@Service
public class MandatoryCoursesServiceImpl implements MandatoryCoursesService {

	@Autowired
	private MandatoryCoursesDao mandatoryCourseDao;

	private long totalNumberOfJduMembers;
	private long totalNumberOfCompletion;
	private long totalNumberOfCompletionLastWeek;

	/**
	 * Finds all mandatory courses based on the given date
	 * 
	 * @param selectedStartDateTime
	 * @param selectedEndDateTime
	 * @return mandatoryCourses
	 *
	 */
	@Override
	public Set<MandatoryCourses> getMandatoryCourses(ZonedDateTime selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {

		Set<MandatoryCourses> mandatoryCourses = mandatoryCourseDao.findMandatoryCourses();

		if (mandatoryCourses.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			return mandatoryCourses;
		}
	}

	/**
	 * Acquires the total number of JDU members
	 * 
	 * @return long
	 */
	@Override
	public long getTotalNumberOfJduMembers() {
		totalNumberOfJduMembers = mandatoryCourseDao.findTotalNumberOfJdu();

		if (totalNumberOfJduMembers < 0) {
			throw new IllegalArgumentException();
		} else {
			return totalNumberOfJduMembers;
		}
	}

	/**
	 * Acquires the total number of completion for the specified course.
	 * 
	 * @param mandatoryCourse
	 * @return long
	 */
	@Override
	public long getTotalNumberOfCompletion(MandatoryCourses mandatoryCourse) {

		// String courseName = mandatoryCourse.getName();

		// To add course name on the method call
		totalNumberOfCompletion = mandatoryCourseDao.findTotalNumberOfJduWhoFinishedTraining(ZonedDateTime.now(),
				ZonedDateTime.now());

		if (totalNumberOfCompletion < 0) {
			throw new IllegalArgumentException();
		} else {
			return totalNumberOfCompletion;
		}
	}

	/**
	 * Acquires the total number of completion for the specified course within last
	 * week.
	 * 
	 * @param mandatoryCourse
	 * @return long
	 */
	@Override
	public long getTotalNumberOfCompletionLastWeek(MandatoryCourses mandatoryCourse) {

		ZonedDateTime currentDateAndTime = ZonedDateTime.now();
		ZonedDateTime startDateAndTime = currentDateAndTime.minusDays(7);

		// String courseName = mandatoryCourse.getName();

		// to add course name on the method call
		totalNumberOfCompletionLastWeek = mandatoryCourseDao
				.findTotalNumberOfJduWhoFinishedTrainingLastweek(startDateAndTime, currentDateAndTime);

		if (totalNumberOfCompletionLastWeek < 0) {
			throw new IllegalArgumentException();
		} else {
			return totalNumberOfCompletionLastWeek;
		}
	}

	/**
	 * Calculates the percentage completion per course
	 * 
	 * @return int
	 */
	@Override
	public int getPercentageCompletion() {

		long percentageCompletion = 0;

		try {
			percentageCompletion = (totalNumberOfCompletion / totalNumberOfJduMembers) * 100;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return (int) percentageCompletion;
	}

	/**
	 * Calculates the percentage completion per course within last week
	 * 
	 * @return int
	 */
	@Override
	public int getPercentageCompletionLastWeek() {

		long percentageCompletionLastWeek = 0;

		try {
			percentageCompletionLastWeek = (totalNumberOfCompletionLastWeek / totalNumberOfJduMembers) * 100;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return (int) percentageCompletionLastWeek;
	}

}
