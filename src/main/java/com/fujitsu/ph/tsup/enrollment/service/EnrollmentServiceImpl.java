package com.fujitsu.ph.tsup.enrollment.service;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enrollment Course
//Class Name   :EnrollmentServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/25/2020 | WS) T.Oviedo          | New Creation
//0.01    | 07/08/2020 | WS) K.Freo            | Update
//0.01    | 07/08/2020 | WS) M.lumontad        | Update
//==================================================================================================

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
//import com.fujitsu.ph.tsup.enrollment.domain.Participant;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
* <pre>
* JavaBean for EnrollmentServiceImpl
* <pre>
* 
* @version 0.01
* @author t.oviedo                    
*/
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentDao enrollmentDao;
    
    /** Finds the scheduled courses starting from today onwards 
     * 
     * @param fromDateTime
     * @param toDateTime
     * @author J.Yu
     * 
     * */
    @Override
    public Set<CourseSchedule> findAllScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {  
        try {
               Set<CourseSchedule> courseScheduleSet = enrollmentDao
                       .findAllScheduledCourses(fromDateTime, toDateTime);
                 if (courseScheduleSet == null || courseScheduleSet.isEmpty()){
                        throw new IllegalArgumentException("No schedules found");
                  }
                return courseScheduleSet;
           } catch(DataAccessException ex) {
               throw new IllegalArgumentException("Can't Access From Datetime and To Datetime");
           }   
    }

    
    
    @Override
	public Set<CourseSchedule> findAllMemberScheduledCourses(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
        try {
            Set<CourseSchedule> courseScheduleSet = enrollmentDao
                    .findAllScheduledCourses(fromDateTime, toDateTime);
             return courseScheduleSet;
        } catch(DataAccessException ex) {
            throw new IllegalArgumentException("Can't Access From Datetime and To Datetime");
        }   
	}



	/** Finds the course schedule by id */
    @Override
    public CourseSchedule findCourseScheduleById(Long id) {
    	return enrollmentDao.findCourseScheduleById(id);
//         try {
//                
//            } catch(DataAccessException ex) {
//                throw new IllegalArgumentException("Can't Access Id.");
//            }
        
    }
    
    /** enroll using the courseParticipant */
    @Override
    public void enroll(CourseParticipant courseParticipant) {
    	  System.out.println("MY COURSE ID 2: " +courseParticipant.getCourseScheduleId());
          System.out.println("FPI USER ID 2: "+courseParticipant.getParticipantId());
         
        try {
            CourseSchedule courseRecord = enrollmentDao.findCourseScheduleById(courseParticipant.getCourseScheduleId());
            
            if (courseRecord == null){
//                throw new IllegalArgumentException("This course " +courseParticipant
//                        .getCourseName()+ " is not existing");
            	 throw new IllegalArgumentException("This course schedule id " +courseParticipant
                         .getCourseScheduleId()+ " is not existing");
            }
//            System.out.println("COURSE NAME: "+courseRecord.getCourseName());
            CourseParticipant participantRecord = enrollmentDao
                    .findCourseParticipantByCourseScheduleIdAndParticipantId
                    (courseParticipant.getCourseScheduleId(), courseParticipant.getParticipantId());
//            System.out.println("PARTICIPANT RECORD IS EMPTY!!!");
            if(participantRecord != null){
                throw new IllegalArgumentException("You are already enrolled in this course.");
            }

            enrollmentDao.saveCourseParticipant(courseParticipant);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Can't Access Course Participant");
        }
    }

    /** 
     * 
     * Call enrollmentDao.findAllEnrolledCoursesByParticipantId using ID
     * Finds All Enrolled Courses by Participant ID
     * 
     * @author m.lumontad
     * 
     *  */
    @Override
    public Set<CourseParticipant> findAllEnrolledCoursesByParticipantId(Long participantId, 
            ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
        
          try {
              Set<CourseParticipant> courseParticipant = enrollmentDao.findAllEnrolledCoursesByParticipantId
                      (participantId, fromDateTime, toDateTime);              
              if (courseParticipant == null || courseParticipant.isEmpty()) {
                  throw new IllegalArgumentException("No Course Schedule Found");
              }
                return courseParticipant;
            } catch(DataAccessException ex) {
                throw new IllegalArgumentException("Can't Access Id");
            }
        } 
    
    /** 
     * 
     * Call enrollmentDao.findCourseParticipantById using the given id 
     * Finds course participant by id
     * 
     * @author k.freo
     * 
     *  
     *  */
    @Override
    public CourseParticipant findCourseParticipantById(Long id) {
        try {
             CourseParticipant courseParticipant = enrollmentDao.findCourseParticipantById(id); 
                             if(courseParticipant == null ) {
                                 throw new IllegalArgumentException("Participant not Found");
                             } 
                                 return courseParticipant;   
                         } catch (DataAccessException ex) {
                             throw new IllegalArgumentException(" Participant not Found ");  
             }
    }
    
    /**
     *  Decline the course which the participant was previously enrolled
     *  Call enrollmentDao.deleteCourseParticipantById using the given courseParticipant.id
     *  Call enrollmentDao.saveCourseNonParticipant using the given dbCourseParticipant
     * 
     *  @author k.freo
     *  
     **/
    @Override
    public void declineCourse(CourseParticipant courseParticipant) {

        try {
        CourseParticipant findCourseParticipant = enrollmentDao.findCourseParticipantById(courseParticipant.getId());
             
        CourseParticipant dbCourseParticipant = 
                 new CourseParticipant
                           .Builder(findCourseParticipant.getId())
                           .decline(courseParticipant.getReason())
                           .build();
             
         enrollmentDao.deleteCourseParticipantById(courseParticipant.getId());
         enrollmentDao.saveCourseNonParticipant(dbCourseParticipant);
         } catch (DataAccessException ex) {
             throw new IllegalArgumentException(" Can't decline Course.");       
                 }     
    } 
    
    @Override
    public void cancel(Long id) {

           try {
                CourseSchedule courseSchedule = enrollmentDao.findCourseScheduleById(id);
                
                if(courseSchedule.getId() == null) {
                    throw new IllegalArgumentException("This course{"+id+"} is not existing");
                }
                CourseSchedule courseScheduleInstance = new CourseSchedule.Builder(id).cancel().build();    
                enrollmentDao.changeCourseScheduleStatus(courseScheduleInstance);
                
            } catch(DataAccessException ex) {
                throw new IllegalArgumentException(" Can't cancel Course.");
            }
        }       
    
    /** Finds the participant of course by Id */
//    @Override
//    public List<Participant> findEnrolledMembersById(Long id) {
//         return enrollmentDao.viewEnrolledMembers(id);
//    }
    
    /** Add the participant of course by Id */
//    @Override
//    public Integer addEnrolledMembersById(Participant participant) {
//         return enrollmentDao.addEnrolledMembersById(participant);
//    }
    
}