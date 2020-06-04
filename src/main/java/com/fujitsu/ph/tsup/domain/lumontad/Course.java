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
            if (courseName == null) {
                throw new IllegalArgumentException("Course Name cannot be Empty!");
            }else if(courseName.isEmpty()) {
                throw new IllegalArgumentException("Course Name cannot be Empty!");
            }else if(courseName.length() < 5) {
                throw new IllegalArgumentException("Course Name is less than the Required 5 Characters!");
            }else if (courseName.length() > 100) {
                throw new IllegalArgumentException("Course Name is greater than the Required 100 Characters!");
            }

        }
    }

}
