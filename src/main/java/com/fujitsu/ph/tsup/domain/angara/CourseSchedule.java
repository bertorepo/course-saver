package com.fujitsu.ph.tsup.domain.angara;

public class CourseSchedule {
    private Long id;
    private Long instructorId;
    private Long venueId;
    private Long courseId;
    private int minRequired;
    private int maxRequired;
    private String status;

    protected CourseSchedule() {

    }

    private CourseSchedule(Builder builder) {
        this.id = builder.id;
        this.instructorId = builder.instructorId;
        this.courseId = builder.courseId;
        this.venueId = builder.venueId;
        this.minRequired = builder.minRequired;
        this.maxRequired = builder.maxRequired;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public Long getInstructionId() {
        return instructorId;
    }

    public Long getVenueId() {
        return venueId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public int getMinRequired() {
        return minRequired;
    }

    public int getMaxRequired() {
        return maxRequired;
    }

    private String getStatus() {
        return status;
    }

    public static class Builder {
        private String status;
        private int maxRequired;
        private int minRequired;
        private Long venueId;
        private Long courseId;
        private Long instructorId;
        private Long id;

        public Builder(Long courseId, Long instructorId, Long venueId, int minRequired, int maxRequired,
                String status) {
            ValidateCourseId(courseId);
            ValidateInstructorId(instructorId);
            ValidateVenueId(venueId);
            ValidateMinRequired(minRequired);
            ValidateMaxRequired(maxRequired);
            ValidateStatus(status);

            this.courseId = courseId;
            this.instructorId = instructorId;
            this.venueId = venueId;
            this.minRequired = minRequired;
            this.maxRequired = maxRequired;
            this.status = status;
        }
        
        public CourseSchedule build() {
            return new CourseSchedule(this);
        }

        private void ValidateCourseId(Long courseId) {
            if (courseId == null || courseId == 0) {
                throw new IllegalArgumentException("Provide appropriate Course Id.");
            }
        }

        private void ValidateInstructorId(Long instructorId) {
            if (instructorId == 0 || instructorId == null) {
                throw new IllegalArgumentException("Provide appropriate Instructor Id.");
            }
        }

        private void ValidateVenueId(Long venueId) {
            if (venueId == null) {
                throw new IllegalArgumentException("Provide appropriate Venue Id.");
            }
        }

        private void ValidateMinRequired(int minRequired) {
            if (minRequired == 0) {
                throw new IllegalArgumentException("Provide Minimum Required of Participants.");
            }
        }

        private void ValidateMaxRequired(int maxRequired) {
            if (maxRequired <= 100 || maxRequired == 0) {
                throw new IllegalArgumentException("Provide Maximum Required of Participants.");
            }
        }

        private void ValidateStatus(String status) {
            if (status.isEmpty() || status == null || status.length() < 1) {
                throw new IllegalArgumentException("Provide Status");
            }
        }

    }

}
