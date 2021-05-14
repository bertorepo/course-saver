//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : MandatoryCoursesServiceImpl                                                                                                                                                               
// Class Name   : MandatoryCoursesServiceImpl.java                                                                                                                                                                          
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/21 | WS)C.Fuerzas          | New Creation      
// 1.0.1   | 2021/05/05 | WS)C.Fuerzas          | Updated
//==================================================================================================
package com.fujitsu.ph.tsup.report.summary.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.report.summary.dao.MandatoryCoursesDao;
import com.fujitsu.ph.tsup.report.summary.model.MandatoryCourses;
import com.fujitsu.ph.tsup.report.summary.model.MandatoryCoursesForm;

/**
 * <pre>
 * The implementation of mandatory courses services
 * </pre>
 * @author c.fuerzas 
 * @version 1.0.1
 */
@Service
public class MandatoryCoursesServiceImpl implements MandatoryCoursesService {

    @Autowired
    private MandatoryCoursesDao mandatoryCoursesDao;
    private MandatoryCoursesForm mandatoryCoursesForm = new MandatoryCoursesForm();

    /**
     * Finds all mandatory courses based on the given date
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
            return null;
        } else {
            return mandatoryCourses;

        }
    }

    /**
     * Acquires the total number of JDU members
     * @return long
     */
    @Override
    public long getTotalNumberOfJduMembers() {

        mandatoryCoursesForm.setTotalNoOfJDUMem((long) mandatoryCoursesDao.findTotalNumberOfJdu());

        if (mandatoryCoursesForm.getTotalNoOfJDUMem() <= 0) {
            return 0;
        } else {
            return mandatoryCoursesForm.getTotalNoOfJDUMem();
        }
    }

    /**
     * Acquires the total number of completion for the specified course.
     * @param mandatoryCourse
     * @return long
     */
    @Override
    public long getTotalNumberOfCompletion(String mandatoryCourse) {

        mandatoryCoursesForm.setTotalNoOfJDUMemFin(
                (long) mandatoryCoursesDao.findTotalNumberOfJduWhoFinishedTraining(mandatoryCourse));

        if (mandatoryCoursesForm.getTotalNoOfJDUMemFin() < 0) {
            return 0;
        } else {
            return mandatoryCoursesForm.getTotalNoOfJDUMemFin();
        }
    }

    /**
     * Acquires the total number of completion for the specified course within last week.
     * @param mandatoryCourse
     * @return long
     */
    @Override
    public long getTotalNumberOfCompletionLastWeek(String mandatoryCourse) {

        mandatoryCoursesForm.setTotalNoOfJDUMemFinLastWk((long) mandatoryCoursesDao
                .findTotalNumberOfJduWhoFinishedTrainingLastWeek(mandatoryCourse));

        if (mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk() <= 0) {
            return 0;
        } else {
            return mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk();
        }
    }

    /**
     * Calculates the percentage completion per course
     * @return double
     */
    @Override
    public double getPercentageCompletion() {
        double membersFinished = mandatoryCoursesForm.getTotalNoOfJDUMemFin();
        double members = mandatoryCoursesForm.getTotalNoOfJDUMem();

        double percentageCompletion = round(((membersFinished/members)*100), 2);
        return percentageCompletion;
    }

    /**
     * Calculates the percentage completion per course within last week
     * @return double
     */
    @Override
    public double getPercentageCompletionLastWeek() {
        double membersFinishedLastWk = mandatoryCoursesForm.getTotalNoOfJDUMemFinLastWk();
        double members = mandatoryCoursesForm.getTotalNoOfJDUMem();
        double percentageCompletionLastWk = round(((membersFinishedLastWk/members)*100), 2);

        return percentageCompletionLastWk;
    }
    
    /**
     * Round up the course within last week
     * @return double
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
