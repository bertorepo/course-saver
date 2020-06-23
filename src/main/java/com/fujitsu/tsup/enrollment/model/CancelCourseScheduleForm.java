package com.fujitsu.tsup.enrollment.model;

//========================================================================================================================
//$id:PR03$
//Project Name :Training Sign Up
//System Name  :Cancel Course Schedule Form
//Class Name   :CancelCourseScheduleForm.java
//
//<<Modification History>>
//Version | Date       | Updated By      | Content
//--------+------------+-----------------+--------------------------------------------------------------------------------
//0.01    | 06/23/2020 | WS) T.Oviedo    | New Creation
//========================================================================================================================
/**
* <pre>
* JavaBean for CancelCourseScheduleForm
* In this class, Instance 
* <pre>
* 
* @version 0.01
* @author t.oviedo
* 
*/
public class CancelCourseScheduleForm { 
  /* Course Schedule Id */
  private Long id;
  
  /* Course Id */
  private Long courseId;
  
  /* Course Name */
  private String courseName;
  
  /* Instructor Id */
  private Long instructorId;
  
  /* Instructor Name(LASTNAME, FIRSTNAME) */
  private String instructorName;
  
  /* Venue Id */
  private Long venueId;
  
  /* Venue Name */
  private String venueName;
  
  /* Set of course schedule details */
  private CourseScheduleDetailForm courseScheduleDetailForm;
  
  /* Minimum number of participants */
  private int minRequired;
  
  /* Maximum number of participants */
  private int maxAllowed;
  
  /* Total Number of Participants currently enrolled */
  private int totalParticipants;
  
  /**
   * JavaBean for CourseEnrollmentCancelForm
   */
  public CancelCourseScheduleForm(Long id, Long courseId, String courseName, Long instructorId, String instructorName,
          Long venueId, String venueName, CourseScheduleDetailForm courseScheduleDetailForm, int minRequired,
          int maxAllowed, int totalParticipants) {
      super();
      this.id = id;
      this.courseId = courseId;
      this.courseName = courseName;
      this.instructorId = instructorId;
      this.instructorName = instructorName;
      this.venueId = venueId;
      this.venueName = venueName;
      this.courseScheduleDetailForm = courseScheduleDetailForm;
      this.minRequired = minRequired;
      this.maxAllowed = maxAllowed;
      this.totalParticipants = totalParticipants;
  }
  
  /**
   * get Course Schedule id 
   * @return id
   */
  public Long getId() {
      return id;
  }
  
  /** 
   * set id
   * @param id
   */
  public void setId(Long id) {
      this.id = id;
  }
  
  /**
   * get Course id
   * @return courseId
   */
  public Long getCourseId() {
      return courseId;
  }
  
  /**
   * set Course id
   * @param courseId
   */
  public void setCourseId(Long courseId) {
      this.courseId = courseId;
  }
  
  /**
   * get Course Name
   * @return getCourseName
   */
  public String getCourseName() {
      return courseName;
  }
  
  /**
   * set Course Name
   * @param courseName
   */
  public void setCourseName(String courseName) {
      this.courseName = courseName;
  }
  
  /**
   * get Instructor Id
   * @return
   */
  public Long getInstructorId() {
      return instructorId;
  }
  
  /**
   * set Instructor Id
   * @param instructorId
   */
  public void setInstructorId(Long instructorId) {
      this.instructorId = instructorId;
  }
  
  /**
   * get Instructor Name
   * @return
   */
  public String getInstructorName() {
      return instructorName;
  }

  /**
   * set Instructor Name
   * @param instructorName
   */
  public void setInstructorName(String instructorName) {
      this.instructorName = instructorName;
  }

  /**
   * get Venue Id
   * @return
   */
  public Long getVenueId() {
      return venueId;
  }
  
  /** 
   * set Venue Id
   * @param venueId
   */
  public void setVenueId(Long venueId) {
      this.venueId = venueId;
  }

  /**  
   * get Venue Name
   * @return
   */
  public String getVenueName() {
      return venueName;
  }
  
  /** 
   * set Venue Name
   * @param venueName
   */
  public void setVenueName(String venueName) {
      this.venueName = venueName;
  }

  /** 
   * get Course Schedule Detail Form
   * @return
   */
  public CourseScheduleDetailForm getCourseScheduleDetail() {
      return courseScheduleDetailForm;
  }
  
  /** 
   * set Course Schedule Detail Form
   * @param courseScheduleDetails
   */
  public void setCourseScheduleDetail(CourseScheduleDetailForm courseScheduleDetailForm) {
      this.courseScheduleDetailForm = courseScheduleDetailForm;
  }
  
  /** 
   * get Minimum Required
   * @return
   */
  public int getMinRequired() {
      return minRequired;
  }

  /** 
   * set Minimum Required
   * @param minRequired
   */
  public void setMinRequired(int minRequired) {
      this.minRequired = minRequired;
  }

  /** 
   * get Maximum Required
   * @return
   */
  public int getMaxAllowed() {
      return maxAllowed;
  }

  /**
   * set Maximum Required
   * @param maxAllowed
   */
  public void setMaxAllowed(int maxAllowed) {
      this.maxAllowed = maxAllowed;
  }

  /** 
   * get Total Participants
   * @return
   */
  public int getTotalParticipants() {
      return totalParticipants;
  }

  /** 
   * set Total Participants
   * @param totalParticipants
   */
  public void setTotalParticipants(int totalParticipants) {
      this.totalParticipants = totalParticipants;
  }

//@Override
//public String toString() {
//    
//            
//    String toReturn = "CancelCourseSchedule [Course ID="+courseId+", Course Name="
//            + courseName + ", Instructor ID=" + instructorId + ", Instructor Name=" + instructorName + ", Venue ID="
//            + venueId + ", Venue Name=" + venueName + ", Course Schedule Details=" + courseScheduleDetails + ", Minimum Required" + minRequired + ", Maximum Allowed="
//            + maxAllowed + ", Total Participants=" + totalParticipants + "]";
//    
//    return toReturn;
//}
  
}
