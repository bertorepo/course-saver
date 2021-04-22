/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.service;

import java.time.ZonedDateTime;
import java.util.Set;

import com.fujitsu.ph.tsup.reports.summary.model.MandatoryCoursesForm;

/**
 * Service for the MandatoryCoursesController
 * 
 * @author c.fuerzas (New Creation by: c.fuerzas)
 * @version Revision: 0.01 Date: 2021-04-21
 */
public interface MandatoryCoursesService {

	/**
	 * Finds all the mandatory courses for all members based on the given date range
	 * 
	 * @param selectedStartDateTime
	 * @param selectedEndDateTime
	 * @return Set of MandatoryCoursesForm
	 */
	// public Set<MandatoryCoursesForm> getMandatoryCourses(ZonedDateTime
	// selectedStartDateTime, ZonedDateTime selectedEndDateTime);

	/**
	 * Acquires the total number of JDU members.
	 * 
	 * @return int
	 */
	public int getTotalNumberOfJduMembers();

	/**
	 * Acquires the total number of completion for the specified course.
	 * 
	 * @param courseName
	 * @return int
	 */
	public int getTotalNumberOfCompletion(String courseName);

	/**
	 * Acquires the total number of completion for the specified course within last
	 * week
	 * 
	 * @param courseName
	 * @return int
	 */
	public int getTotalNumberOfCompletionLastWeek(String courseName);

	/**
	 * Calculates the percentage completion per course
	 * 
	 * @return int
	 */
	public int getPercentageCompletion();

	/**
	 * Calculates the percentage completion per course within last week
	 * 
	 * @return int
	 */
	public int getPercentageCompletionLastWeek();
}
