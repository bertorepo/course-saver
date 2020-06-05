package com.fujitsu.ph.tsup.domain.abad;

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
        
        private void validateCourseId(Long courseId) {
            if (courseId == null) {
                throw new IllegalArgumentException("course id should not be empty");
            }
        }
            
        private void validateInstructorId(Long venueId) {
            if (venueId == null) {
                throw new IllegalArgumentException("venue id should not be empty");
            } 
        }
            
        private void validateVenueId(Long instructorId) {
            if (instructorId == null) {
                throw new IllegalArgumentException("instructor id should not be empty");
            }
        }
                
        private void validateMinRequired(int minRequired) {
            if (minRequired < 1) {
                throw new IllegalArgumentException("minimum number of participants should not be empty");
            }        
            else if(minRequired > 1000) {
                throw new IllegalArgumentException("minimum number of participants should not exceed by 1000");     
            }
        }
                    
        private void validateMaxAllowed(int maxAllowed) {
            if (maxAllowed > 1000) {
                 throw new IllegalArgumentException("maximum number of participants should not exceed by 1000");
            }
            else if(maxAllowed < 1) {
                throw new IllegalArgumentException("maximum number of participants should not be empty");  
            }
        }
                        
        private void validateStatus(String status) {
            if (status == null || status.isEmpty()) {
                 throw new IllegalArgumentException("status should not be empty");
            } 
       }       
    }
}