package com.fujitsu.ph.tsup.domain.angara;

public class Course {
    private String courseName;
    private Long id;

    private Course(Builder builder) {
        this.courseName = builder.courseName;
        this.id = builder.id;
    }

    Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }
    
    public static class Builder {
        public String courseName;
        private Long id;

        public Builder(String courseName, Long id) {
            ValidateCourseName(courseName);

            this.courseName = courseName;
            this.id = id;
        }

        private void ValidateCourseName(String courseName) {
            if (courseName.isEmpty() || courseName == null || courseName.length() > 50 || courseName.length() <= 5) {
                throw new IllegalArgumentException("Provide the course name.");
            }
        }

    }
}
