package com.fujitsu.ph.tsup.domain.lumontad;

public class CourseSchedule {
    private Long courseScheduleID;
    private Long courseId;
    private Long employeeID;
    private Long venueID;
    private int minRequired;
    private int maxAllowed;
    private String status;

    protected CourseSchedule() {
    }

    private CourseSchedule(Builder builder) {
        this.courseScheduleID = builder.courseScheduleID;
        this.courseId = builder.courseId;
        this.employeeID = builder.employeeID;
        this.venueID = builder.venueID;
        this.minRequired = builder.minRequired;
        this.maxAllowed = builder.maxAllowed;
        this.status = builder.status;
    }

    public Long getId() {
        return courseScheduleID;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getInstructorId() {
        return employeeID;
    }

    public long getVenueId() {
        return venueID;
    }

    public int getMinRequired() {
        return minRequired;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private Long courseScheduleID;
        private Long courseId;
        private Long employeeID;
        private long venueID;
        private int minRequired;
        private int maxAllowed;
        private String status;

        public Builder(Long courseId, Long employeeID, Long venueID, int minRequired, int maxAllowed, String status) {
            validateCourseId(courseId);
            validateInstructorId(employeeID);
            validateVenueId(venueID);
            validateMinRequired(minRequired);
            validateMaxAllowed(maxAllowed);
            validateStatus(status);

            this.courseId = courseId;
            this.employeeID = employeeID;
            this.venueID = venueID;
            this.minRequired = minRequired;
            this.maxAllowed = maxAllowed;
            this.status = status;
        }

        public CourseSchedule build() {
            return new CourseSchedule(this);
        }

        private void validateCourseId(Long courseId) {
            if (courseId == null) {
                throw new IllegalArgumentException("Course ID cannot not be empty");
            }
        }

        private void validateInstructorId(Long venueID) {
            if (venueID == null) {
                throw new IllegalArgumentException("Venue ID cannot not be empty");
            }
        }

        private void validateVenueId(Long employeeID) {
            if (employeeID == null) {
                throw new IllegalArgumentException("Instructor's ID cannot be empty");
            }
        }

        private void validateMinRequired(int minRequired) {
            if (minRequired < 1 || minRequired > 1000) {
                throw new IllegalArgumentException("Minimum Participants cannot be Empty");
            }
        }

        private void validateMaxAllowed(int maxAllowed) {
            if (maxAllowed > 1000 || maxAllowed < 1) {
                throw new IllegalArgumentException("Maximum Participants cannot be Empty");
            }
        }

        private void validateStatus(String status) {
            if (status == null || status.isEmpty()) {
                throw new IllegalArgumentException("Status cannot be Empty");
            }
        }
    }
}