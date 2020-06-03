package com.fujitsu.ph.tsup.domain.deguzman;


public class Course {
    private Long id;
    private String courseName;

    protected Course() {

    }

    private Course(Builder builder) {
        this.id = builder.id;
        this.courseName = builder.courseName;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public static class Builder {
        private Long id;
        private String courseName;

        public Builder(String courseName) {
            validateCourseName(courseName);

            this.courseName = courseName;
        }

        public Course build() {
            return new Course(this);
        }

        private void validateCourseName(String courseName) {
            if (courseName == null || courseName.isEmpty() || courseName.length() < 5 || courseName.length() > 100) {
                throw new IllegalArgumentException("Course Name should not be empty");
            }

        }
    }

}
