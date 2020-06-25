package com.fujitsu.ph.tsup.attendance.model;

import java.util.Set;

//==================================================================================================
//$Id:PR03$
//Project Name :Training Sign Up
//System Name  :Attendance process
//Class Name   :CourseSchedule.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01 | 06/23/2020 |  WS) R. Ramos   | New Creation
//==================================================================================================
/**
 * <pre>
 * The Course Schedule Model. This uses a Builder Pattern
 * 
 * <pre>
 * 
 * @version 0.01
 * @author r.ramos
 */
public class CourseSchedule {
    /**
     * Course Schedule Id
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
     * Total Number of Participants currently enrolled
     */
    private int totalParticipants;

    /**
     * Status
     */
    private char status;

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
        this.venueId = builder.venueId;
        this.venueName = builder.venueName;
        this.minRequired = builder.minRequired;
        this.maxAllowed = builder.maxAllowed;
        this.courseScheduleDetail = builder.courseScheduleDetail;
        this.totalParticipants = builder.totalParticipants;
        this.status = builder.status;

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

    public int getMinRequired() {
        return minRequired;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public Set<CourseScheduleDetail> getCourseScheduleDetail() {
        return courseScheduleDetail;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

    public char getStatus() {
        return status;
    }
    
	@Override
	public String toString() {
		return "CourseSchedule [id = " + id + ", courseId = " + courseId + ", courseName = " + courseName
				+ ", instructorId = " + instructorId + ", instructorLastName = " + instructorLastName
				+ ", instructorFirstName = " + instructorFirstName + ", venueId = " + venueId + ", venueName = "
				+ venueName + ", minRequired = " + minRequired + ", maxAllowed = " + maxAllowed
				+ ", courseScheduleDetail = " + courseScheduleDetail + ", totalParticipants = " + totalParticipants
				+ ", status = " + status + "]";
	}

    /**
     * <pre>
     * The builder class of the course schedule. The builder is a public static
     * member class of CourseSchedule
     * 
     * <pre>
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
         * Total Number of Participants currently enrolled
         */
        private int totalParticipants;

        /**
         * Status
         */
        private char status;

        /**
         * <pre>
         * Creates a new instance of course schedule Builder. Validates and sets the
         * argument into the Builder instance variables. This method is used for setting
         * the data from the database.
         * 
         * <pre>
         * 
         * @param id
         * @param courseId
         * @param instuctorId
         * @param venueId
         * @param minRequired
         * @param courseName
         * @param instructorLastName
         * @param instructorFirstName
         * @param venueName
         * @param maxAllowed
         */
        public Builder(Long id, Long courseId, String courseName, Long instructorId, String instructorLastName,
                String instructorFirstName, Long venueId, String venueName, int minRequired, int maxAllowed,
                int totalParticipants, char status) {

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

            this.id = id;
            this.courseId = courseId;
            this.instructorId = instructorId;
            this.venueId = venueId;
            this.minRequired = minRequired;
            this.courseName = courseName;
            this.instructorLastName = instructorLastName;
            this.instructorFirstName = instructorFirstName;
            this.venueName = venueName;
            this.maxAllowed = maxAllowed;

        }

        /**
         * <pre>
         * Creates a new instance of Builder for creating a course schedule. It
         * validates and sets the argument into the Builder instance variables. This
         * method is used for canceling the course schedule.
         * 
         * <pre>
         * 
         * @param id
         */
        public Builder(Long id) {

            validateId(id);

            this.courseId = id;
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
        public CourseSchedule maxAllowed(int maxAllowed) {

            validateMaxAllowed(maxAllowed);

            this.maxAllowed = maxAllowed;

            CourseSchedule builder = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
                    instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed,
                    totalParticipants, status).build();

            return builder;

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
        public CourseSchedule addDetail(Set<CourseScheduleDetail> courseScheduleDetail) {

            validateCourseScheduleDetail(courseScheduleDetail);

            this.courseScheduleDetail = courseScheduleDetail;

            CourseSchedule builder = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
                    instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed,
                    totalParticipants, status).build();

            return builder;
        }

        /**
         * <pre>
         * Set the status to Cancel
         * 
         * <pre>
         * 
         * @return builder
         */
        public CourseSchedule cancel() {

            this.status = 'C';

            CourseSchedule builder = new CourseSchedule.Builder(id, courseId, courseName, instructorId,
                    instructorLastName, instructorFirstName, venueId, venueName, minRequired, maxAllowed,
                    totalParticipants, status).build();

            return builder;
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
        private void validateCourseScheduleDetail(Set<CourseScheduleDetail> courseScheduleDetail) {
            if (courseScheduleDetail.isEmpty() || courseScheduleDetail == null) {
                throw new IllegalArgumentException("The schedule should have at least 1 record");
            }
        }
    }

}
