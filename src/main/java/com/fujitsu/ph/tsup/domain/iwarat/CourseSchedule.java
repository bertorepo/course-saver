package com.fujitsu.ph.tsup.domain.iwarat;

public class CourseSchedule {
    private Long id;
    private Long courseId;
    private Long instructorId;
    private Long venueId;
    private int minRecquired;
    private int maxRecquired;
    private String status;

    protected CourseSchedule() {

    }

    public CourseSchedule(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.instructorId = builder.instructorId;
        this.venueId = builder.venueId;
        this.minRecquired = builder.minRecquired;
        this.maxRecquired = builder.maxRecquired;
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

    public int getMinRecquired() {
        return minRecquired;
    }

    public int getMaxRecquired() {
        return maxRecquired;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private Long id;
        private Long courseId;
        private Long instructorId;
        private Long venueId;
        private int minRecquired;
        private int maxRecquired;
        private String status;

        public Builder(Long id, Long courseId, Long instructorId, Long venueId, int minRecquired, int maxRecquired,
                String status) {
            validateId(id);
            validateCourseId(courseId);
            validateInstructorId(instructorId);
            validateVenueId(venueId);
            validateMinRecquired(minRecquired);
            validateMaxRecquired(maxRecquired);
            validateStatus(status);

            this.id = id;
            this.courseId = courseId;
            this.instructorId = instructorId;
            this.venueId = venueId;
            this.minRecquired = minRecquired;
            this.maxRecquired = maxRecquired;
            this.status = status;
        }

        public CourseSchedule build() {
            return new CourseSchedule(this);
        }

        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Id should not be null");
            }
        }

        private void validateCourseId(Long courseId) {
            if (courseId == null) {
                throw new IllegalArgumentException("Course id should not be null");
            }
        }

        private void validateInstructorId(Long instructorId) {
            if (instructorId == null) {
                throw new IllegalArgumentException("Instructor id should not be null");
            }
        }

        private void validateVenueId(Long venueId) {
            if (venueId == null) {
                throw new IllegalArgumentException("Venue id should not be null");
            }
        }

        private void validateMinRecquired(int minRecquired) {
            if (minRecquired < 1) {
                throw new IllegalArgumentException("Min recquired should not be empty or null");
            } else if (minRecquired > 99999) {
                throw new IllegalArgumentException("Min recquired should not be empty or null");
            }
        }

        private void validateMaxRecquired(int maxRecquired) {
            if (maxRecquired < 1) {
                throw new IllegalArgumentException("Max recquired should not be empty or null");
            } else if (maxRecquired > 99999) {
                throw new IllegalArgumentException("Max recquired should not be empty or null");
            }
        }

        private void validateStatus(String status) {
            if (status == null || status.isEmpty()) {
                throw new IllegalArgumentException("Status should not be empty");
            }
        }

    }

}
