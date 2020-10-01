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
//0.02    | 09/07/2020 | WS) J.Yu              | Update
//==================================================================================================

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentDao;
import com.fujitsu.ph.tsup.enrollment.domain.CourseParticipant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseSchedule;
//import com.fujitsu.ph.tsup.enrollment.domain.Participant;
import com.fujitsu.ph.tsup.enrollment.domain.CourseScheduleDetail;
import com.fujitsu.ph.tsup.enrollment.model.SearchForm;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.HashSet;
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
                	 throw new IllegalArgumentException("No Course Schedule Found");
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
         
//        try {
            CourseSchedule courseRecord = enrollmentDao.findCourseScheduleById(courseParticipant.getCourseScheduleId());
            
            if (courseRecord == null){
            	 throw new IllegalArgumentException("This course schedule id " +courseParticipant
                         .getCourseScheduleId()+ " is not existing");
            }

            CourseParticipant participantRecord = enrollmentDao
                    .findCourseParticipantByCourseScheduleIdAndParticipantId
                    (courseParticipant.getCourseScheduleId(), courseParticipant.getParticipantId());
//            System.out.println("PARTICIPANT RECORD IS EMPTY!!!");
            if(participantRecord != null){
                throw new IllegalArgumentException("You are already enrolled in this course.");
            }
            
            enrollmentDao.saveCourseParticipant(courseParticipant);
//        } catch (DataAccessException e) {
//            throw new IllegalArgumentException("Can't Access Course Participant");
//        }
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

       
        CourseParticipant findCourseParticipant = enrollmentDao.findCourseParticipantById(courseParticipant.getId());
             
        CourseParticipant dbCourseParticipant = 
                 new CourseParticipant
                           .Builder(findCourseParticipant.getId())
                           .decline(courseParticipant.getReason())
                           .build();
             
         enrollmentDao.deleteCourseParticipantById(courseParticipant.getId());
         enrollmentDao.saveCourseNonParticipant(dbCourseParticipant);
      
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



	@Override
	public void cancelCourseSchedules(Set<CourseSchedule> courseScheduleSet) {
		// TODO Auto-generated method stub
		try {
			enrollmentDao.cancelCourseSchedulesById(courseScheduleSet);
		}catch(Exception e) {
			throw new IllegalArgumentException("Cant Cancel Course Schedule Set.");
		}
	}



	@Override
	public Set<CourseSchedule> findAllActiveCourseSchedule() {
		// TODO Auto-generated method stub
		return enrollmentDao.findAllActiveCourseSchedule();
	}



	@Override
	public Set<CourseSchedule> findAllCouresScheduleByMonthOrQuarter(String queryBy) {
		// TODO Auto-generated method stub
		Set<CourseSchedule> courseSchedule = new HashSet<CourseSchedule>();
		if(queryBy.equals("month")) {
			courseSchedule = enrollmentDao.findAllCourseScheduleByMonth();
		}else if(queryBy.equals("quarter")) {
			courseSchedule = enrollmentDao.findAllCourseScheduleByQuarter();
		}
		
		//validation
		if(courseSchedule == null || courseSchedule.isEmpty()) {
			throw new IllegalArgumentException("No Course Schedule found!");
		}
		return courseSchedule;
	}



	@Override
	public void rescheduleCourseScheduleById(CourseScheduleDetail courseScheduleDetail) {
		// TODO Auto-generated method stub
		System.out.println("SERVICE");
		try {
			enrollmentDao.reschedule(courseScheduleDetail);
		}catch(Exception e) {
			throw new IllegalArgumentException("Can not reschedule course schedule" + e.getMessage());
		}
	}



	@Override
	public Set<CourseSchedule> findAllCourseScheduleBelowMinimumParticipants() {
		// TODO Auto-generated method stub
		try {
			Set<CourseSchedule> courseScheduleSet = enrollmentDao.findAllCourseScheduleBelowMinimumParticipants();
			return courseScheduleSet;
		}catch(Exception e) {
			throw new IllegalArgumentException("Cant get CourseSchedules below minimum" + e.getMessage());
		}
	}



	@Override
	public Set<CourseParticipant> findAllParticipantByCourseScheduleId(Long courseScheduleId) {
		// TODO Auto-generated method stub
		Set<CourseParticipant> courseParticipantSet = new HashSet<CourseParticipant>();
		try {
			courseParticipantSet = enrollmentDao.findAllParticipantByCourseScheduleId(courseScheduleId);
			if(courseParticipantSet == null || courseParticipantSet.isEmpty()) {
				throw new IllegalArgumentException("No Course Participant Found.");
			}
			return courseParticipantSet;
		}catch(Exception e) {
			throw new IllegalArgumentException("Can not find Course Participant in this Course Schedule");
		}
	}



	@Override
	public Set<CourseParticipant> findAllMemberNotEnrolledByCourseScheduleId(CourseParticipant courseParticipant) {
		// TODO Auto-generated method stub
		try {
			if(courseParticipant == null) {
				throw new IllegalArgumentException("Course Participant Null");
			}
			return enrollmentDao.findAllMemberNotEnrolledByCourseScheduleId(courseParticipant);
		}catch(Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}



	@Override
	public Set<CourseParticipant> findMemberNotEnrolledByCourseScheduleId(SearchForm searchForm) {
		// TODO Auto-generated method stub
		try {
			System.out.println("1");
			Set<CourseParticipant> courseParticipantSet = enrollmentDao.findMemberNotEnrolledByCourseScheduleId(searchForm);
			if(courseParticipantSet == null || courseParticipantSet.isEmpty()) {
				throw new IllegalArgumentException("No Participants Found");
			}
			return courseParticipantSet;
		}catch(Exception e) {
			throw new IllegalArgumentException("Can not find Course Participants");
		}
	}



	@Override
	public Set<CourseSchedule> findCourseScheduleByCourseId(CourseSchedule courseSchedule) {
		// TODO Auto-generated method stub
		return enrollmentDao.findCourseScheduleByCourseId(courseSchedule);
	}



	@Override
	public void updateSchedule(CourseParticipant courseParticipant) {

			CourseSchedule courseRecord = enrollmentDao.findCourseScheduleById(courseParticipant.getCourseScheduleId());
			if (courseRecord == null){
          	 throw new IllegalArgumentException("This course schedule id " +courseParticipant
                       .getCourseScheduleId()+ " is not existing");
			}

	        CourseParticipant participantRecord = enrollmentDao
	                .findCourseParticipantByCourseScheduleIdAndParticipantId
	                (courseParticipant.getCourseScheduleId(), courseParticipant.getParticipantId());

	//        System.out.println("PARTICIPANT RECORD IS EMPTY!!!");
	        if(participantRecord != null){ 
	        	throw new IllegalArgumentException("You are already enrolled to this course.");
	       
	           
	        }else if(participantRecord == null) {
	        	enrollmentDao.updateCourseParticipant(courseParticipant);
	        }
	        
	}
	
	@Override
    public List<TopLearnerForm> findTopLearner(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {      
        try {
            if (Period.between(fromDateTime.toLocalDate(), toDateTime.toLocalDate()).getMonths() >= 4) {
                return enrollmentDao.findTopLearnerByQuarter();
            } else {
                return enrollmentDao.findTopLearnerByMonth();
            }            
        } catch (DataAccessException ex) {
            throw new IllegalArgumentException("No Top Learners");
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