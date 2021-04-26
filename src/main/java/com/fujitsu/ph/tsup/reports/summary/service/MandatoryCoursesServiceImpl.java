/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.service;

import java.time.ZonedDateTime;
import java.util.Set;

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

	private MandatoryCoursesDaoImpl mandatoryCourseDao = new MandatoryCoursesDaoImpl();
	private MandatoryCoursesForm mandatoryCoursesForm= new MandatoryCoursesForm();
//	private int totalNumberOfJduMembers;
//	private int totalNumberOfCompletion;
//	private int totalNumberOfCompletionLastWeek;

	/**
	 * Finds all mandatory courses based on the given date
	 * 
	 * @param selectedStartDateTime
	 * @param selectedEndDateTime
	 * @return mandatoryCourses
	 *
	 */
	@Override
	public Set<MandatoryCourses> getMandatoryCourses(ZonedDateTime
			selectedStartDateTime,
			ZonedDateTime selectedEndDateTime) {

		Set<MandatoryCourses> mandatoryCourses =
				mandatoryCourseDao.findMandatoryCourses(selectedStartDateTime,
						selectedEndDateTime);

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

		mandatoryCoursesForm.setTotalNoOfJDUMem((long) mandatoryCourseDao.findTotalNumberOfJdu(ZonedDateTime.now(), ZonedDateTime.now()));

		// TO BE CHANGED/CONFIRMED
		if (mandatoryCoursesForm.getTotalNoOfJDUMem() <= 0) {
			throw new IllegalArgumentException();
		} else {
			return mandatoryCoursesForm.getTotalNoOfJDUMem();
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
		
		String courseName = mandatoryCourse.getName();

		// To add course name on the method call
		mandatoryCoursesForm.setTotalNoOfJDUMemFin((long) mandatoryCourseDao.findTotalNumberOfJduWhoFinishedTraining(ZonedDateTime.now(),
				ZonedDateTime.now()));

		// TO BE CHANGED
		if (mandatoryCoursesForm.getTotalNoOfJDUMemFin() < 0) {
			throw new IllegalArgumentException();
		} else {
			return mandatoryCoursesForm.getTotalNoOfJDUMemFin();
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
		
		String courseName = mandatoryCourse.getName();

		// to add course name on the method call
		mandatoryCoursesForm.setTotalNoOfJDUMemFinLastWk((long) mandatoryCourseDao
				.findTotalNumberOfJduWhoFinishedTrainingLastweek(startDateAndTime, currentDateAndTime));

		// TO BE CHANGED/CONFIRMED
		if (mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk() <= 0) {
			throw new IllegalArgumentException();
		} else {
			return mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk();
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
			percentageCompletion = (mandatoryCoursesForm.getTotalNoOfJDUMemFin() / mandatoryCoursesForm.getTotalNoOfJDUMem()) * 100;
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
			percentageCompletionLastWeek = (mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk() / mandatoryCoursesForm.getTotalNoOfJDUMem()) * 100;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		return (int) percentageCompletionLastWeek;
	}

}
