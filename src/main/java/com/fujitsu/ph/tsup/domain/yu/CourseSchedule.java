package com.fujitsu.ph.tsup.domain.yu;

public class CourseSchedule {
    private Long id;
    private Long courseId;
    private Long instructorId;
    private Long venueId;
    private int minRequired;
    private int maxAllowed;
    private String status;

    protected CourseSchedule() {

    }

    private CourseSchedule(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.instructorId = builder.instructorId;
        this.venueId = builder.venueId;
        this.minRequired = builder.minRequired;
        this.maxAllowed = builder.maxAllowed;
        this.status = builder.status;

    }
    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public Long getVenueId() {
        return venueId;
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
        private Long id;
        private Long courseId;
        private Long instructorId;
        private Long venueId;
        private int minRequired;
        private int maxAllowed;
        private String status;

        public Builder(Long id, Long courseId, Long instructorId, Long venueId,
                int minRequired, int maxAllowed, String status) {
            validateCourseId(courseId);
            validateInstructorId(instructorId);
            validateVenueId(venueId);
            validateMinRequired(minRequired);
            validateMaxAllowed(maxAllowed);
            validateStatus(status);
            
            this.id = id;
            this.courseId = courseId;
            this.instructorId = instructorId;
            this.venueId = venueId;
            this.minRequired = minRequired;
            this.maxAllowed = maxAllowed;
            this.status = status;
        }

        public CourseSchedule build() {
            return new CourseSchedule(this);
        }

        private void validateCourseId(Long courseId) {
            if (courseId == null) {
                throw new IllegalArgumentException(
                        "Course ID should not be empty");
            }

        }

        private void validateInstructorId(Long instructorId) {
            if (instructorId == null) {
                throw new IllegalArgumentException(
                        "Instructor ID should not be empty");
            }
        }

        private void validateVenueId(Long venueId) {
            if (venueId == null) {
                throw new IllegalArgumentException(
                        "Venue ID should not be empty");
            }
        }

        private void validateMinRequired(int minRequired) {
            if (minRequired < 1 || minRequired > 99999) {
                throw new IllegalArgumentException(
                        "Minimum Required should not be empty and greater than 5 digits");
            }

        }

        private void validateMaxAllowed(int maxAllowed) {
            if (maxAllowed < 1 || maxAllowed > 99999) {
                throw new IllegalArgumentException(
                        "Maximum Required should not be empty and greater than 5 digits");
            }
        }

        private void validateStatus(String status) {
            if (status == null || status.isEmpty() || status.length() < 1
                    || status.length() > 1) {
                throw new IllegalArgumentException(
                        "Status should not be empty");
            }
        }
    }

}
