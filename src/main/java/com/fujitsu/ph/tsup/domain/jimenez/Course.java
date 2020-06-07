package com.fujitsu.ph.tsup.domain.jimenez;

public class Course {
    private Long id;
    private String name;
    
    protected Course() {
    }
    
    private Course(Builder build) {
        this.id = build.id;
        this.name = build.name;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public static class Builder {
        private Long id;
        private String name;
        
        public Builder(Long id, String name) {
            validateId(id);
            validateCourseName(name);
            
            this.id = id;
            this.name = name;
        }
        
        public Builder(String name) {
            validateCourseName(name);
            
            this.name = name;
        }
        
        public Course builder() {
            return new Course(this);
        }
        
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
        
        private void validateCourseName(String name) {
            if (name == null) {
                throw new NullPointerException("Course Name should not be null");
            } else if (name.isEmpty()) {
                throw new IllegalArgumentException("Course Name should not be Empty");
            } else if (name.length() > 100) {
                throw new IllegalArgumentException("Course Name Length exceeds the Limit");
            }
        }
    }

}
