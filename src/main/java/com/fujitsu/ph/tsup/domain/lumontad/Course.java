package com.fujitsu.ph.tsup.domain.lumontad;

public class Course {
    private Long courseID;
    private String courseName;

    protected Course() {

    }

    private Course(Builder builder) {
        this.courseID = builder.courseID;
        this.courseName = builder.courseName;
    }

    public Long getId() {
        return courseID;
    }

    public String getName() {
        return courseName;
    }

    public static class Builder {
        private Long courseID;
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
