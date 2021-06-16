package com.fujitsu.ph.tsup.enrollment.domain;

//==================================================================================================
//$Id:PR01$
//Project Name :Training Sign Up
//System Name  :Enroll Course
//Class Name   :CourseSchedule.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 06/23/2020 | WS) K.Freo        | New Creation
//0.02	  | 06/16/2021 | WS) K.Sevilla	   | Update
//==================================================================================================

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

    
    private String mandatory; // Added
    
    private String deadline; // Added
    
    
    /**
     * Venue Id
     */
    private Long venueId;

    /**
     * Venue Name
     */
    private String venueName;
    
    //new
    /** Attendance Status **/
	private String courseStatus;

    /**
     * Minimum number of participants
     */
    private int minRequired;

    /**
     * Maximum number of participants
     */
    private int maxAllowed;

    /**
     * The course schedule details
     */
    private CourseScheduleDetail courseScheduleDetail;

    /**
     * Total number of participants
     */
    private int totalParticipants;

    /**
     * Status
     */
    private char status;
    
    private String courseDetails;

    protected CourseSchedule() {

    }

    /**
     * <pre>
     * Creates an instance of the CourseSchedule using the given builder class.
     * 
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
        this.mandatory = builder.mandatory; // Added
        this.deadline = builder.deadline; // Added
        this.venueId = builder.venueId;
        this.venueName = builder.venueName;
        this.minRequired = builder.minRequired;
        this.maxAllowed = builder.maxAllowed;
        this.courseScheduleDetail = builder.courseScheduleDetail;
        this.totalParticipants = builder.totalParticipants;
        this.status = builder.status;
        this.courseDetails = builder.courseDetails;
    }

    public CourseSchedule(CourseSchedule courseSchedule) {
        // TODO Auto-generated constructor stub
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
    
    public String getMandatory() { // Added
        return mandatory;
    }
    
    public String getDeadline() { // Added
        return deadline;
    }
    
    public Long getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }
    
    //new
    public String getCourseStatus() {
        return courseStatus;
    }

    public int getMinRequired() {
        return minRequired;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public CourseScheduleDetail getCourseScheduleDetail() {
        return courseScheduleDetail;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public char getStatus() {
        return status;
    }

    public String getCourseDetails() {
        return courseDetails;
    }
    /**
     * <pre>
     * The builder class of the course schedule. The builder is a public static
     * member class of CourseSchedule
     * 
     * <pre>
     * 
     * @author g.cabiling
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
        
        private String mandatory; // added
        
        private String deadline; // added
        
        /**
         * Venue Id
         */
        private Long venueId;

        /**
         * Venue Name
         */
        private String venueName;

        /**
         * Minimum number of participants
         */
        private int minRequired;

        /**
         * Maximum number of participants
         */
        private int maxAllowed;

        /**
         * The course schedule details
         */
        private CourseScheduleDetail courseScheduleDetail;

        /**
         * Total number of participants
         */
        private int totalParticipants;

        /**
         * Status
         */
        private char status;
        
        private String courseDetails;

        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule. It
         * validates and sets the argument into the Builder instance variables. This
         * method is used for creating a course schedule.
         * 
         * <pre>
         * 
         * @param id
         * @param courseId
         * @param courseName
         * @param instuctorId
         * @param instructorLastName
         * @param instructorFirstName
         * @param mandatory
         * @param deadline
         * @param venueId
         * @param venueName
         * @param minRequired
         * @param maxAllowed
         * @param totalParticipant
         */
        public Builder(Long id, Long courseId, String courseName, Long instructorId, String instructorLastName,
                String instructorFirstName, String mandatory, String deadline, Long venueId, String venueName, int minRequired, int maxAllowed,
                int totalParticipants, char status) { // added String mandatory & String deadline
            
            validateId(id);
            validateCourseId(courseId);
            validateCourseName(courseName);
            validateInstructorId(instructorId);
            validateInstructorLastName(instructorLastName);
            validateInstructorFirstName(instructorFirstName);
            validateVenueId(venueId);
            validateVenueName(venueName);
            validateMinRequired(minRequired);
            validateMaxAllowed(maxAllowed);
            validateMandatory(mandatory);
            validateDeadline(deadline);
            
            
            this.id = id;
            this.courseId = courseId;
            this.instructorId = instructorId;
            this.venueId = venueId;
            this.minRequired = minRequired;
            this.courseName = courseName;
            this.instructorLastName = instructorLastName;
            this.instructorFirstName = instructorFirstName;
            this.mandatory = mandatory; // added
            this.deadline = deadline; // added
            this.venueName = venueName;
            this.maxAllowed = maxAllowed;
            this.status = status;
            this.totalParticipants = totalParticipants;

        }
        //TEMPORARY
        public Builder(Long id, Long courseId, String courseName, Long instructorId, String instructorLastName,
                String instructorFirstName, String mandatory, String deadline, Long venueId, String venueName, int minRequired, int maxAllowed,
                 char status) {/*int totalParticipants,*/ // added String mandatory & String deadline
            
            validateId(id);
            validateCourseId(courseId);
            validateCourseName(courseName);
            validateInstructorId(instructorId);
            validateInstructorLastName(instructorLastName);
            validateInstructorFirstName(instructorFirstName);
            validateMandatory(mandatory); // added
            validateDeadline(deadline); // added
            validateVenueId(venueId);
            validateVenueName(venueName);
            validateMinRequired(minRequired);
            validateMaxAllowed(maxAllowed);

            
            this.id = id;
            this.courseId = courseId;
            this.instructorId = instructorId;
            this.venueId = venueId;
            this.minRequired = minRequired;
            this.courseName = courseName;
            this.instructorLastName = instructorLastName;
            this.instructorFirstName = instructorFirstName;
            this.mandatory = mandatory; // added
            this.deadline = deadline; // added
            this.venueName = venueName;
            this.maxAllowed = maxAllowed;
            this.status = status;

        }
        
        
        /**
         * <pre>
         * Validates and sets the argument into the Builder instance variables
         * 
         * <pre>
         * 
         * @param id
         */
        public Builder(Long id) {

            validateId(id);
            this.id = id;
        }

        /**
         * <pre>
         * Validates and sets the argument into the Builder instance variables
         * 
         * <pre>
         * 
         * @param maxAllowed
         * @return builder
         */
        public Builder maxAllowed(int maxAllowed) {

            validateMaxAllowed(maxAllowed);
            this.maxAllowed = maxAllowed;

//          CourseSchedule builder = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
//                  instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed,
//                  totalParticipants, status).build();

            return this;
        }

        /**
         * <pre>
         * Validates and sets the argument into the Builder instance variables
         * 
         * <pre>
         * 
         * @param courseScheduleDetail
         * @return builder
         */
        public Builder addDetail(CourseScheduleDetail courseScheduleDetail) {

            validateCourseScheduleDetail(courseScheduleDetail);
            this.courseScheduleDetail = courseScheduleDetail;

//          CourseSchedule builder = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
//                  instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed,
//                  totalParticipants, status).build();

            return this;
        }
        
        public Builder addCourseDetail(String courseDetails) {
            validateCourseDetails(courseDetails);
            this.courseDetails = courseDetails;
            
            return this;
        }

        /**
         * <pre>
         * Validates and sets the argument into the Builder instance variables
         * 
         * <pre>
         * 
         * @param cancel
         * @return builder
         */
        public Builder cancel() {

            this.status = 'C';

//          CourseSchedule builder = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
//                  instructorLastName, instructorFirstName, venueId, venueName, maxAllowed, maxAllowed,
//                  totalParticipants, status).build();
//
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
         * Validate the id based on the condition below. If it is invalid then throw an
         * IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param id
         */
        private void validateId(Long id) {
            if (id == null || id == 0L) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the course id based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param courseId
         */
        private void validateCourseId(Long courseId) {
            if (courseId == null || courseId == 0L) {
                throw new IllegalArgumentException("Course should not be empty");
            }

        }

        /**
         * <pre>
         * Validate the course name based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param courseName
         */
        private void validateCourseName(String courseName) {
            if (courseName == null || courseName.isEmpty()) {
                throw new IllegalArgumentException("Course name should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the instructor id based on the condition below. If it is invalid
         * then throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param instructorId
         */
        private void validateInstructorId(Long instructorId) {
            if (instructorId == null || instructorId == 0L) {
                throw new IllegalArgumentException("Instructor should not be empty");
            }

        }

        /**
         * <pre>
         * Validate the instructor last name based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param instructorLastName
         */
        private void validateInstructorLastName(String instructorLastName) {
            if (instructorLastName == null || instructorLastName.isEmpty()) {
                throw new IllegalArgumentException("Instructor Name should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the instructor first name based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param instructorFirstName
         */
        private void validateInstructorFirstName(String instructorFirstName) {
            if (instructorFirstName == null || instructorFirstName.isEmpty()) {
                throw new IllegalArgumentException("Instructor Name should not be empty");
            }
        }

        //Added
        /**
         * <pre>
         * Validate the mandatory based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param instructorFirstName
         */
        private void validateMandatory(String mandatory) {
            if (mandatory == null || mandatory.isEmpty()) {
                throw new IllegalArgumentException("Mandatory should not be empty");
            }
        }
        
        //Added
        /**
         * <pre>
         * Validate the Deadline based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param instructorFirstName
         */
        private void validateDeadline(String deadline) {
            if (deadline == null || deadline.isEmpty()) {
                throw new IllegalArgumentException("Deadline should not be empty");
            }
        }
        
        /**
         * <pre>
         * Validate the venue id based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param venueId
         */
        private void validateVenueId(Long venueId) {
            if (venueId == null || venueId == 0L) {
                throw new IllegalArgumentException("Venue should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the venue name based on the condition below. If it is invalid then
         * throw an IllegalArgumentException with the corresponding message.
         * 
         * <pre>
         * 
         * @param venueName
         */
        private void validateVenueName(String venueName) {
            if (venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("Venue should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the minimum number of participants based on the condition below. If
         * it is invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param minRequired
         */
        private void validateMinRequired(int minRequired) {
            if (minRequired <= 0) {
                throw new IllegalArgumentException("Mininum No. of Participants should be greater than 0");
            }
        }

        /**
         * <pre>
         * Validate the maximum allowed participants based on the condition below. If it
         * is invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param maxAllowed
         */
        private void validateMaxAllowed(int maxAllowed) {
            if (maxAllowed < 0) {
                throw new IllegalArgumentException("Maximum No. of Participants should not be less than 0");
            }
        }

        /**
         * <pre>
         * Validate the course schedule details based on the condition below. If it is
         * invalid then throw an IllegalArgumentException with the corresponding
         * message.
         * 
         * <pre>
         * 
         * @param courseScheduleDetail
         */
        private void validateCourseScheduleDetail(CourseScheduleDetail courseScheduleDetail) {
            if (courseScheduleDetail.getId() == 0 || courseScheduleDetail == null) {
                throw new IllegalArgumentException("The schedule should have at least 1 record");
            }
        }
        
        private void validateCourseDetails(String courseDetails) {
            if (courseDetails.isEmpty() || courseDetails == null) {
                throw new IllegalArgumentException("There is no Detail in this Course.");
            }
        }

    }

    @Override
    public String toString() {
        return "CourseSchedule [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName + ", instructorId="
                + instructorId + ", instructorLastName=" + instructorLastName + ", instructorFirstName="
                + instructorFirstName + ", mandatory=" + mandatory + ", deadline=" + deadline +
                ", venueId=" + venueId + ", venueName=" + venueName +  ", courseStatus=" + courseStatus +  ", minRequired="
                + minRequired + ", maxAllowed=" + maxAllowed + ", courseScheduleDetail=" + courseScheduleDetail
                + ", totalParticipants=" + totalParticipants + ", status=" + status + "]";
    }

    /**
     * Creates a new instance of the course schedule.
     * 
     * @return new CourseSchedule(this)
     */
//    public CourseSchedule build() {
//        return new CourseSchedule(this);
//    }
    
    

}