/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.service;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.reports.summary.dao.MandatoryCoursesDao;
import com.fujitsu.ph.tsup.reports.summary.dao.MandatoryCoursesDaoImpl;
import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCoursesForm;

/**
 * The implementation of mandatory courses services
 * 
 * @author c.fuerzas (New Creation by: c.fuerzas)
 * @version Revision: 0.01 Date: 2021-04-21
 */
public class MandatoryCoursesServiceImpl implements MandatoryCoursesService {

	private MandatoryCoursesDao mandatoryCourseDao = new MandatoryCoursesDaoImpl();
	private int totalNumberOfJduMembers;
	private int totalNumberOfCompletion;
	private int totalNumberOfCompletionLastWeek;

	/**
	 * Finds all mandatory courses based on the given date
	 * 
	 * @param selectedStartDateTime
	 * @param selectedEndDateTime
	 * @return mandatoryCourses
	 *
	 */
	// @Override
	// public Set<MandatoryCoursesForm> getMandatoryCourses(ZonedDateTime
	// selectedStartDateTime,
	// ZonedDateTime selectedEndDateTime) {
	//
	// Set<MandatoryCoursesForm> mandatoryCourses =
	// mandatoryCourseDao.findMandatoryCourses(selectedStartDateTime,
	// selectedEndDateTime));
	//
	// if (mandatoryCourses.isEmpty()) {
	// throw new IllegalArgumentException();
	// } else {
	// return mandatoryCourses;
	// }
	// }

	/**
	 * Acquires the total number of JDU members
	 * 
	 * @return int
	 */
	@Override
	public int getTotalNumberOfJduMembers() {

		totalNumberOfJduMembers = mandatoryCourseDao.findTotalNumberOfJdu(ZonedDateTime.now(), ZonedDateTime.now());

		// TO BE CHANGED/CONFIRMED
		if (totalNumberOfJduMembers <= 0) {
			throw new IllegalArgumentException();
		} else {
			return totalNumberOfJduMembers;
		}
	}

	/**
	 * Acquires the total number of completion for the specified course.
	 * 
	 * @param courseName
	 * @return int
	 */
	@Override
	public int getTotalNumberOfCompletion(String courseName) {

		// To add course name on the method call
		totalNumberOfCompletion = mandatoryCourseDao.findTotalNumberOfJduWhoFinishedTraining(ZonedDateTime.now(),
				ZonedDateTime.now());

		// TO BE CHANGED
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
	 * @param courseName
	 * @return int
	 */
	@Override
	public int getTotalNumberOfCompletionLastWeek(String courseName) {

		ZonedDateTime currentDateAndTime = ZonedDateTime.now();
		ZonedDateTime startDateAndTime = currentDateAndTime.minusDays(7);

		// to add course name on the method call
		totalNumberOfCompletionLastWeek = mandatoryCourseDao
				.findTotalNumberOfJduWhoFinishedTrainingLastWeek(startDateAndTime, currentDateAndTime);

		// TO BE CHANGED/CONFIRMED
		if (totalNumberOfCompletionLastWeek <= 0) {
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

		int percentageCompletion = 0;

		try {
			percentageCompletion = (totalNumberOfCompletion / totalNumberOfJduMembers) * 100;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return percentageCompletion;
	}

	/**
	 * Calculates the percentage completion per course within last week
	 * 
	 * @return int
	 */
	@Override
	public int getPercentageCompletionLastWeek() {

		int percentageCompletionLastWeek = 0;

		try {
			percentageCompletionLastWeek = (totalNumberOfCompletionLastWeek / totalNumberOfJduMembers) * 100;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return percentageCompletionLastWeek;
	}

}
