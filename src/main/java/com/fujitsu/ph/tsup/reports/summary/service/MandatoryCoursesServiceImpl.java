/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

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
    private MandatoryCoursesDaoImpl mandatoryCoursesDao = new MandatoryCoursesDaoImpl();
    private MandatoryCourses mandatoryCourses = new MandatoryCourses();
    private MandatoryCoursesForm mandatoryCoursesForm = new MandatoryCoursesForm();
    // private int totalNumberOfJduMembers;
    // private int totalNumberOfCompletion;
    // private int totalNumberOfCompletionLastWeek;

    /**
     * Finds all mandatory courses based on the given date
     * 
     * @param selectedStartDateTime
     * @param selectedEndDateTime
     * @return mandatoryCourses
     *
     */
    @Override
    public Set<MandatoryCourses> getMandatoryCourses(LocalDateTime selectedStartDateTime,
            LocalDateTime selectedEndDateTime) {

        Set<MandatoryCourses> mandatoryCourses = mandatoryCoursesDao
                .findMandatoryCourses(selectedStartDateTime, selectedEndDateTime);

        if (mandatoryCourses.isEmpty()) {
            System.out.println("****************** mandatoryCourses.isEmpty() ******************");
            return null;

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

        mandatoryCoursesForm.setTotalNoOfJDUMem((long) mandatoryCoursesDao.findTotalNumberOfJdu());

        // TO BE CHANGED/CONFIRMED
        if (mandatoryCoursesForm.getTotalNoOfJDUMem() <= 0) {
            // ********* DELETE *********
            System.out.println("%%%SERVICE getTotalNoOfJDUMem less than or equal to 0:" + mandatoryCoursesForm.getTotalNoOfJDUMem());
            // ********* DELETE *********
            
            throw new IllegalArgumentException();
        } else {
            // ********* DELETE *********
            System.out.println("%%%SERVICE getTotalNoOfJDUMem more than 0:" + mandatoryCoursesForm.getTotalNoOfJDUMem());
            // ********* DELETE *********
            
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
    public long getTotalNumberOfCompletion(String mandatoryCourse) {

        // String courseName = mandatoryCourse.getName();

        // To add course name on the method call
        mandatoryCoursesForm.setTotalNoOfJDUMemFin(
                (long) mandatoryCoursesDao.findTotalNumberOfJduWhoFinishedTraining(mandatoryCourse));

        // TO BE CHANGED
        if (mandatoryCoursesForm.getTotalNoOfJDUMemFin() < 0) {
            throw new IllegalArgumentException();
        } else {
            return mandatoryCoursesForm.getTotalNoOfJDUMemFin();
        }
    }

    /**
     * Acquires the total number of completion for the specified course within last week.
     * 
     * @param mandatoryCourse
     * @return long
     */
    @Override
    public long getTotalNumberOfCompletionLastWeek(String mandatoryCourse) {

        ZonedDateTime currentDateAndTime = ZonedDateTime.now();
        ZonedDateTime startDateAndTime = currentDateAndTime.minusDays(7);

        //String courseName = mandatoryCourse.getName();

        // to add course name on the method call
        mandatoryCoursesForm.setTotalNoOfJDUMemFinLastWk((long) mandatoryCoursesDao
                .findTotalNumberOfJduWhoFinishedTrainingLastWeek(mandatoryCourse));

        // TO BE CHANGED/CONFIRMED
        if (mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk() <= 0) {
            return 0;
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
    public double getPercentageCompletion() {
        
        System.out.println("SERVICE getTotalNoOfJDUMemFin: " + mandatoryCoursesForm.getTotalNoOfJDUMemFin());
        System.out.println("SERVICE getTotalNoOfJDUMem: " + mandatoryCoursesForm.getTotalNoOfJDUMem());
        double membersFinished = mandatoryCoursesForm.getTotalNoOfJDUMemFin();
        double members = mandatoryCoursesForm.getTotalNoOfJDUMem();
                
        double percentageCompletion = round(((membersFinished/members)*100), 2);
        
        System.out.println("$$$$ SERVICE percentageCompletion: " + percentageCompletion);
        try {
            System.out.println("$$$$ SERVICE percentageCompletion: " + percentageCompletion);
            return percentageCompletion;

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Calculates the percentage completion per course within last week
     * 
     * @return int
     */
    @Override
    public double getPercentageCompletionLastWeek() {
        
        System.out.println("SERVICE getTotalNoOfJDUMemFin: " + mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk());
        System.out.println("SERVICE getTotalNoOfJDUMem: " + mandatoryCoursesForm.getTotalNoOfJDUMem());
        double membersFinishedLastWk = mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk();
        double members = mandatoryCoursesForm.getTotalNoOfJDUMem();
                
        double percentageCompletionLastWk = round(((membersFinishedLastWk/members)*100), 2);
        
        System.out.println("$$$$ SERVICE percentageCompletionLastWk: " + percentageCompletionLastWk);
        try {
            System.out.println("$$$$ SERVICE percentageCompletionLastWk: " + percentageCompletionLastWk);
            return percentageCompletionLastWk;

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
