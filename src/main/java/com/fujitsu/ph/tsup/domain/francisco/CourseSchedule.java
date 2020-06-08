package com.fujitsu.ph.tsup.domain.francisco;

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

        public Builder(Long id, Long courseId, Long instructorId, Long venueId, int minRequired, int maxAllowed, String status) {
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

        public void validateCourseId(Long courseId) {
            if (courseId == null || courseId.toString().isEmpty()) {
                throw new IllegalArgumentException("course id should not be null or empty!");
            }
        }

        public void validateInstructorId(Long instructorId) {
            if (instructorId == null || instructorId.toString().isEmpty()) {
                throw new IllegalArgumentException("instructor id should not be null or empty!");
            }
        }

        public void validateVenueId(Long venueId) {
            if (venueId == null || venueId.toString().isEmpty()) {
                throw new IllegalArgumentException("venue id should not be null or empty!");
            }
        }

        public void validateMinRequired(int minRequired) {
            if (String.valueOf(minRequired).length() == 0) {
                throw new IllegalArgumentException("min required should not be null or empty!");
            } else if (String.valueOf(minRequired).length() > 5) {
                throw new IllegalArgumentException("min required should not exceed 5 digits!");
            }
        }

        public void validateMaxAllowed(int maxAllowed) {
            if (String.valueOf(maxAllowed).length() == 0) {
                throw new IllegalArgumentException("max allowed should not be null or empty!");
            } else if (String.valueOf(maxAllowed).length() > 5) {
                throw new IllegalArgumentException("max allowed should not exceed 5 digits!");
            }
        }

        public void validateStatus(String status) {
            if (status == null || status.isEmpty()) {
                throw new IllegalArgumentException("status should not be null or empty!");
            } else if (!(status.equalsIgnoreCase("a") || status.equalsIgnoreCase("c"))) {
                throw new IllegalArgumentException("Invalid status!");
            }
        }

    }

}
