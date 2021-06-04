package com.fujitsu.ph.tsup.scheduling.domain;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseSchedule.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) J.Macabugao | New Creation
//0.02    | 06/04/2021 | WS) J. Atendido | Bug fix and enhancements
//
//=======================================================

/**
* <pre>
* The Course Schedule Model.
* This uses a Builder Pattern
* <pre>
* @version 0.01
* @author j.macabugao
*
*/

import java.util.Set;

public class CourseSchedule {
    
    /**
     * Id
     */
    private Long id;
    
    /**
     * Course Id
     */
    private Long courseId;
    
    /**
     * Course Name
     */
    private String courseName;
    
    /**
     * Instructor Id
     */
    private Long instructorId;
    
    /**
     * Instructor Last Name
     */
    private String instructorLastName;
    
    /**
     * Instructor First Name
     */
    private String instructorFirstName;
    
    /**
     * Venue Id
     */
    private Long venueId;
    
    /**
     * Venue Name
     */
    private String venueName;

    /**
     * Venue venueOverlap boolean
     */
    private boolean venueOverlap;
    
    /**
     * Minimum number of participants
     */
    private int minRequired;
    
    /**
     * Maximum number of participants
     */
    private int maxAllowed;
    
    /**
     * The course schedule detail
     */
    private Set<CourseScheduleDetail> courseScheduleDetail;
    
    /**
     * Status
     */
    private char status;
    
    /**
     * Total Participants
     */
    private int totalParticipants;
    
    /**
     * Course Details
     */
    private String courseDetails;

    /**
     * <pre>
     * Creates an instance of the CourseSchedule using the given builder class.
     * <pre>
     * 
     * @param builder
     */
    private CourseSchedule(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.courseName = builder.courseName;
        this.instructorId = builder.instructorId;
        this.instructorLastName = builder.instructorLastName;
        this.instructorFirstName = builder.instructorFirstName;
        this.venueId = builder.venueId;
        this.venueName = builder.venueName;
        this.venueOverlap = builder.venueOverlap;
        this.minRequired = builder.minRequired;
        this.maxAllowed = builder.maxAllowed;
        this.courseScheduleDetail = builder.courseScheduleDetail;
        this.status = builder.status;
        this.totalParticipants = builder.totalParticipants;
        this.courseDetails = builder.courseDetails;
    }
    
    
    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getInstructorId() {
        return instructorId;
    }


    public String getInstructorLastName() {
        return instructorLastName;
    }


    public String getInstructorFirstName() {
        return instructorFirstName;
    }


    public Long getVenueId() {
        return venueId;
    }


    public String getVenueName() {
        return venueName;
    }

    public boolean isVenueOverlap() {
		return venueOverlap;
	}


	public void setVenueOverlap(boolean venueOverlap) {
		this.venueOverlap = venueOverlap;
	}


    public int getMinRequired() {
        return minRequired;
    }

	public int getMaxAllowed() {
        return maxAllowed;
    }


    public Set<CourseScheduleDetail> getCourseScheduleDetail() {
        return courseScheduleDetail;
    }

    public char getStatus() {
        return status;
    }

    public int getTotalParticipants() {
		return totalParticipants;
	}


	public String getCourseDetails() {
		return courseDetails;
	}


	/**
     * <pre>
     * The builder class of the course schedule. 
     * The builder is a public static member class of CourseSchedule
     * <pre>
     * 
     * @author j.macabugao
     *
     */
    public static class Builder {

		/**
         * Id
         */
        private Long id;
        
        
        /**
         * Course Id
         */
        private Long courseId;
        
        /**
         * Course Name
         */
        private String courseName;
        
        
        /**
         * Instructor Id
         */
        private Long instructorId;
        
        /**
         * Instructor Last Name
         */
        private String instructorLastName;
        
        /**
         * Instructor First Name
         */
        private String instructorFirstName;
        
        /**
         * Venue Id
         */
        private Long venueId;
        
        /**
         * Venue Name
         */
        private String venueName;

        /**
         * Venue venueOverlap boolean
         */
        private boolean venueOverlap;
        
        /**
         * Minimum number of participants
         */
        private int minRequired;
        
        /**
         * Maximum number of participants
         */
        private int maxAllowed;
        
        /**
         * The course schedule detail
         */
        private Set<CourseScheduleDetail> courseScheduleDetail;
        
        /**
         * Status
         */
        private char status;
        
        /**
         * Total Participants
         */
        private int totalParticipants;
        
        /**
         * Course Details
         */
        private String courseDetails;
        
        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule.
         * It validates and sets the argument into the Builder instance variables. 
         * This method is used for creating a course schedule.
         * <pre>
         * 
         * @param courseId
         * @param instuctorId
         * @param venueId
         * @param minRequired
         * @param courseSchedulDetails
         */
    
        public Builder(Long courseId, Long instructorId, Long venueId, int minRequired, 
            Set<CourseScheduleDetail> courseScheduleDetail) {
        
        validateCourseId(courseId);
        validateInstructorId(instructorId);
        validateVenueId(venueId);
        validateMinRequired(minRequired);
        validateCourseScheduleDetail(courseScheduleDetail);
            
        this.status = 'A';  
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.venueId = venueId;
        this.minRequired = minRequired;
        this.courseScheduleDetail = courseScheduleDetail;
        
        }
        
        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule.
         * It validates and sets the argument into the Builder instance variables. 
         * This method is used for updating the course schedule.
         * <pre>
         * 
         * @param Id
         * @param courseId
         * @param instuctorId
         * @param venueId
         * @param minRequired
         * @param courseSchedulDetails
         */
    
        public Builder(Long id, Long courseId, Long instructorId, Long venueId, int minRequired, 
            Set<CourseScheduleDetail> courseScheduleDetail) {
        
        validateId(id);
        validateCourseId(courseId);
        validateInstructorId(instructorId);
        validateVenueId(venueId);
        validateMinRequired(minRequired);
        validateCourseScheduleDetail(courseScheduleDetail);
        
        this.id = id;
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.venueId = venueId;
        this.minRequired = minRequired;
        this.courseScheduleDetail = courseScheduleDetail;
        
        }
        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule.
         * It validates and sets the argument into the Builder instance variables. 
         * This method is used for setting the data from the database.
         * <pre>
         * 
         * @param Id
         * @param courseId
         * @param courseName
         * @param instuctorId
         * @param instuctorLastName
         * @param instuctorFirstName
         * @param venueId
         * @param venueName
         * @param venueOverlap 
         * @param minRequired
         * @param maxAllowed
         * @param status
         */
    
        public Builder(Long id, Long courseId,  String courseName,Long instructorId, String instructorLastName, 
            String instructorFirstName, Long venueId, String venueName, boolean venueOverlap, int minRequired, int maxAllowed, char status, int totalParticipants) {
        
        validateId(id);
        validateCourseId(courseId);
        validateInstructorId(instructorId);
        validateVenueId(venueId);
        validateMinRequired(minRequired);
        validateCourseName(courseName);
        validateInstructorLastName(instructorLastName);
        validateInstructorFirstName(instructorFirstName);
        validateVenueName(venueName);
        validateMaxAllowed(maxAllowed);
        validateStatus(status);
        validateTotalParticipants(totalParticipants);
        
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.instructorLastName = instructorLastName;
        this.instructorFirstName = instructorFirstName;
        this.venueId = venueId;
        this.venueName = venueName;
        this.venueOverlap = venueOverlap;
        this.minRequired = minRequired;
        this.maxAllowed = maxAllowed;
        this.status = status;
        this.totalParticipants = totalParticipants;
        
        }
        
        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule.
         * It validates and sets the argument into the Builder instance variables. 
         * This method is used for changing status of course schedule
         * <pre>
         * 
         * @param Id
         * @param courseId
         */
    
        public Builder(Long id, Long courseId) {
        
            validateId(id);
            validateCourseId(courseId);
            
            this.id = id;
            this.courseId = courseId;
        
        }

        /**
         * <pre>
         * Validates and sets the argument into the Builder instance variables 
         * <pre>
         * 
         * @param maxAllowed
         * @return builder
         */
        public Builder maxAllowed(int maxAllowed) {
        
        validateMaxAllowed(maxAllowed);
        this.maxAllowed = maxAllowed;
        
        return this;
        
        }
        
        public Builder done() {
            
            this.status = 'D';
            return this;
        }
        
        public Builder ongoing() {
            
            this.status = 'O';
            return this;
        }
        
        public Builder active() {
            
            this.status = 'A';
            return this;
        }
        
        public Builder cancelled() {
            
            this.status = 'C';
            return this;
        }
        
        /**
         * <pre>
         * Validates and sets the argument into the Builder instance variables 
         * <pre>
         * 
         * @param courseScheduleDetail
         * @return builder
         */
        public Builder addDetail(Set<CourseScheduleDetail> courseScheduleDetail) {
        
            validateCourseScheduleDetail(courseScheduleDetail);
            this.courseScheduleDetail = courseScheduleDetail;
    
            return this;    
        }
        
        /**
         * <pre>
         * Validates and add the argument into the Builder instance variables. T
         * This method is used in displaying Course Details in Course Schedule Delete Form.
         * <pre>
         * 
         * @param courseDetails
         * @return builder
         */
        public Builder addCourseDetail(String courseDetails) {
            
            validateCourseDetails(courseDetails);
            this.courseDetails = courseDetails;
        
            return this;    
        }
        
        
         /**
         * Creates a new instance of the course schedule.
         * 
         * @return new CourseSchedule(this)
         */
        public CourseSchedule build() {
        
            return new CourseSchedule(this);
        }
        /**
         * <pre>
         * Validate the id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param id
         */
        private void validateId(Long id) {
            if(id == null || id == 0L) {
             throw new IllegalArgumentException("Id should not be empty");
            }
        }
        
        /**
         * <pre>
         * Validate the course id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param courseId
         */
        private void validateCourseId(Long courseId) {  
            if(courseId == null || courseId == 0L) {
             throw new IllegalArgumentException("Course should not be empty");
            }
        }
        /**
         * <pre>
         * Validate the instructor id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param instructorId
         */
        private void validateInstructorId(Long instructorId) {
            if(instructorId == null || instructorId == 0L) {
                throw new IllegalArgumentException("Instructor should not be empty");
            }
        }
    
        /**
         * <pre>
         * Validate the venue id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param venueId
         */
        private void validateVenueId(Long venueId) {
            if(venueId == null || venueId == 0L) {
                throw new IllegalArgumentException("Venue should not be empty");
            }
        }
        /**
         * <pre>
         * Validate the minimum number of participants based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param minRequired
         */
        private void validateMinRequired(int minRequired) {
            if(minRequired <= 0 ) {
                throw new IllegalArgumentException("Mininum No. of Participants should be greater than 0");
            }
        }
        /**
         * <pre>
         * Validate the course name based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param courseName
         */
        private void validateCourseName(String courseName) {
            if(courseName == null || courseName.isEmpty()) {
                throw new IllegalArgumentException("Course name should not be empty");
            }
        }
            
        /**
         * <pre>
         * Validate the instructor last name based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param instructorLastName
         */
        private void validateInstructorLastName(String instructorLastName) {
            if(instructorLastName == null || instructorLastName.isEmpty()) {
                throw new IllegalArgumentException("Instructor Name should not be empty");
            }
        }
        /**
         * <pre>
         * Validate the instructor first name based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param instructorFirstName
         */
        private void validateInstructorFirstName(String instructorFirstName) {
            if(instructorFirstName == null || instructorFirstName.isEmpty()) {
                throw new IllegalArgumentException("Instructor Name should not be empty");
            }
        }
        /**
         * <pre>
         * Validate the venue name based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param venueName
         */
        private void validateVenueName(String venueName) {
            if(venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("Venue should not be empty");
            }
        }
    
        /**
         * <pre>
         * Validate the maximum allowed participants  based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param maxAllowed
         */
        private void validateMaxAllowed(int maxAllowed) {
            if(maxAllowed < 0) {
                throw new IllegalArgumentException("Maximum No. of Participants should not be less than 0");
            }
        }
        
        /**
         * <pre>
         * Validate the status based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param status
         */
        private void validateStatus(char status) {
            if(status != 'O' && status != 'D' && status != 'A' && status != 'C') {
                throw new IllegalArgumentException("Status should be 'A','C','O' or 'D' only");
        }
    }
    
        /**
         * <pre>
         * Validate the course schedule details based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * <pre>
         * 
         * @param courseScheduleDetail
         */
        private void validateCourseScheduleDetail(Set<CourseScheduleDetail> courseScheduleDetail) {
            if(courseScheduleDetail.isEmpty() || courseScheduleDetail == null) {
                throw new IllegalArgumentException("The schedule should have at least 1 record");
            }
        }
        private void validateTotalParticipants(int totalParticipants) {
        	 if(totalParticipants < 0 ) {
                 throw new IllegalArgumentException("Total Participants value should be numeric");
        	 }
        }
        
        private void validateCourseDetails(String courseDetails) {
       	 if(venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("Course Details should not be empty");
       	     }
        }
        
        @Override
        public String toString() {
            return "Builder [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName + ", instructorId="
                    + instructorId + ", instructorLastName=" + instructorLastName + ", instructorFirstName="
                    + instructorFirstName + ", venueId=" + venueId + ", venueName=" + venueName + ", venueOverlap=" + venueOverlap
                    + ", minRequired=" + minRequired + ", maxAllowed=" + maxAllowed + ", courseScheduleDetail=" + courseScheduleDetail
                    + ", status=" + status + "]";
        }
    
    }
}

