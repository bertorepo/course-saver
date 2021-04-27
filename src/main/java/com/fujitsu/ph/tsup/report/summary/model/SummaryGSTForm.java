/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.report.summary.model;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author D.Escala
 * @version Creation: 0.01 Date: 2021-04-21
 *
 */
public class SummaryGSTForm {
	
private long TotalNoJDUPMValue;						
private long TotalNoJDUPMLastWeekValue;							
private long TotalNoOrigMemValue;							
private long TotalNoNewMemValue;							
private long TotalNoJDUPMFinValue;							
private long TotalNoJDUPMLastWkFinValue;							
private double PercentageFinTodayValue;							
private double PercentageFinLastWkValue;
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
private ZonedDateTime scheduledStartDateTime;

/**
 *	Scheduled End Date and Time
 */
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
private ZonedDateTime scheduledEndDateTime;

/**
 * @return TotalNoJDUPMValue
 */
public long getTotalNoJDUPMValue() {
	return TotalNoJDUPMValue;
}

/**
 * @param totalNoJDUPMValue the total Number of JDU PM Value to set
 */
public void setTotalNoJDUPMValue(long totalNoJDUPMValue) {
	TotalNoJDUPMValue = totalNoJDUPMValue;
}

public ZonedDateTime getScheduledStartDateTime() {
	return scheduledStartDateTime;
}

public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
	this.scheduledStartDateTime = scheduledStartDateTime;
}

public ZonedDateTime getScheduledEndDateTime() {
	return scheduledEndDateTime;
}

public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
	this.scheduledEndDateTime = scheduledEndDateTime;
}

/**
 * @return TotalNoJDUPMLastWeekValue
 */
public long getTotalNoJDUPMLastWeekValue() {
	return TotalNoJDUPMLastWeekValue;
}

/**
 * @param totalNoJDUPMLastWeekValue the total Number of JDU PM Last week Value to set
 */
public void setTotalNoJDUPMLastWeekValue(long totalNoJDUPMLastWeekValue) {
	TotalNoJDUPMLastWeekValue = totalNoJDUPMLastWeekValue;
}

/**
 * @return TotalNoOrigMemValue
 */
public long getTotalNoOrigMemValue() {
	return TotalNoOrigMemValue;
}

/**
 * @param totalNoOrigMemValue the total Number of Original Member Value to set
 */
public void setTotalNoOrigMemValue(long totalNoOrigMemValue) {
	TotalNoOrigMemValue = totalNoOrigMemValue;
}

/**
 * @return TotalNoNewMemValue
 */
public long getTotalNoNewMemValue() {
	return TotalNoNewMemValue;
}

/**
 * @param totalNoOrigMemValue the total Number of New Member Value to set
 */
public void setTotalNoNewMemValue(long totalNoNewMemValue) {
	TotalNoNewMemValue = totalNoNewMemValue;
}

/**
 * @return TotalNoJDUPMFinValue
 */
public long getTotalNoJDUPMFinValue() {
	return TotalNoJDUPMFinValue;
}

/**
 * @param totalNoJDUPMFinValue the total Number of JDU PM finish Value to set
 */
public void setTotalNoJDUPMFinValue(long totalNoJDUPMFinValue) {
	TotalNoJDUPMFinValue = totalNoJDUPMFinValue;
}

/**
 * @return TotalNoJDUPMLastWkFinValue
 */
public long getTotalNoJDUPMLastWkFinValue() {
	return TotalNoJDUPMLastWkFinValue;
}
/**
 * @param totalNoJDUPMFinValue the total Number of JDU PM finish Last Week Value to set
 */
public void setTotalNoJDUPMLastWkFinValue(long totalNoJDUPMLastWkFinValue) {
	TotalNoJDUPMLastWkFinValue = totalNoJDUPMLastWkFinValue;
}

/**
 * @return PercentageFinTodayValue
 */
public double getPercentageFinTodayValue() {
	return PercentageFinTodayValue;
}

/**
 * @param percentageFinTodayValue the Percentage of JDU PM finish as of Today Value to set
 */
public void setPercentageFinTodayValue(double percentageFinTodayValue) {
	PercentageFinTodayValue = percentageFinTodayValue;
}

/**
 * @return PercentageFinLastWkValue
 */
public double getPercentageFinLastWkValue() {
	return PercentageFinLastWkValue;
}

/**
 * @param percentageFinLastWkValue the Percentage of JDU PM finish Last Week Value to set
 */
public void setPercentageFinLastWkValue(double percentageFinLastWkValue) {
	PercentageFinLastWkValue = percentageFinLastWkValue;
}							

}
