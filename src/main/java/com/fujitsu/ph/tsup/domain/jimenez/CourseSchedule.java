package com.fujitsu.ph.tsup.domain.jimenez;

public class CourseSchedule {
    private Long id;
    private Long CourseId;
    private Long instructorId;
    private Long venueId;
    private Integer minRequired;
    private Integer maxAllowed;
    private char status;
    
    protected CourseSchedule() {
    }
    
    private CourseSchedule(Builder build) {
        this.id = build.id;
        this.CourseId = build.CourseId;
        this.instructorId = build.instructorId;
        this.venueId = build.venueId;
        this.minRequired = build.minRequired;
        this.maxAllowed = build.maxAllowed;
        this.status = build.status;
        
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getCourseId() {
        return CourseId;
    }
    
    public Long getInstructorId() {
        return instructorId;
    }
    
    public Long getVenueId() {
        return venueId;
    }
    
    public Integer getMinRequired() {
        return minRequired;
    }
    
    public Integer getmaxAllowed() {
        return maxAllowed;
    }
    
    public char getStatus() {
        return status;
    }
    
    public static class Builder {
        private Long id;
        private Long CourseId;
        private Long instructorId;
        private Long venueId;
        private Integer minRequired;
        private Integer maxAllowed;
        private char status;
        
        public Builder(Long id, Long CourseId, Long InstructorId, Long VenueId, Integer MinRequired, Integer MaxAllowed, char Status) {
            validateId(id);
            validateCourseId(CourseId);
            validateInstructorId(InstructorId);
            validateVenueId(VenueId);
            validateMinRequired(MinRequired);
            validateMaxAllowed(MaxAllowed);
            validateStatus(Status);
            
            this.id = id;
            this.CourseId = CourseId;
            this.instructorId = InstructorId;
            this.venueId = VenueId;
            this.minRequired = MinRequired;
            this.maxAllowed = MaxAllowed;
            this.status = Status;
        }
        
        public CourseSchedule builder() {
            return new CourseSchedule(this);
        }
        
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
        
        private void validateCourseId(Long CourseId) {
            if (CourseId == null) {
                throw new IllegalArgumentException("Course ID should not be null");
            }
        }
        
        private void validateInstructorId(Long InstructorId) {
            if (InstructorId == null) {
                throw new IllegalArgumentException("Insructor ID should not be null");
            }
        }
        
        private void validateVenueId(Long VenueId) {
            if (VenueId == null) {
                throw new IllegalArgumentException("Venue ID should not be null");
            }
        }
        
        private void validateMinRequired(Integer MinRequired) {
            if (MinRequired == 0 || MinRequired == null) {
                throw new IllegalArgumentException("Min Required should not be zero or null");
            }
        }
        
        private void validateMaxAllowed(Integer MaxAllowed) {
            if (MaxAllowed == 0 || MaxAllowed == null) {
                throw new IllegalArgumentException("Max Allowed should not be zero or null");
            }
        }
        
        private void validateStatus(char Status) {
            if (Status == '\u0000' || Character.isWhitespace(Status)) {
                throw new IllegalArgumentException("Status should not be null");
            }
        }
    }
}
