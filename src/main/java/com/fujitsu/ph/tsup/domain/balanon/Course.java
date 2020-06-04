package com.fujitsu.ph.tsup.domain.balanon;

public class Course {
    private Long Id;
    private String CourseName;
  
    
    private Course(Builder builder)
    {
        this.Id = builder.Id;
        this.CourseName = builder.CourseName;
    }
    
    public Long getId() {
        return Id;
    }
    public String getCourseName() {
        return CourseName;
    }
    
    public static class Builder {
        private Long Id;
        private String CourseName;
        
        public Builder(String CourseName) {
            validateCourseName(CourseName);
            
            this.CourseName = CourseName;
        }
        
        private void validateCourseName(String CourseName) {
            if (CourseName == null || CourseName.isEmpty()) {
                throw new IllegalArgumentException("Provide the name of the course properly");
            }
            
        }
    
    }

}
