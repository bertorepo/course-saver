package com.fujitsu.ph.tsup.coursemanagement.model;

public class Course {

    private Long id;
    private String name;
    private String detail;

    protected Course() {

    }

    private Course(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
        this.detail = builder.detail;
    }

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getDetail() {

        return detail;

    }

    public void setDetail(String detail) {

        this.detail = detail;

    }

    @Override
    public String toString() {
        return "Course [ID + " +id +"Name " + name +"Detail " +detail;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String detail;

        public Builder(String name) {

            validateName(name);

            this.name = name;

        }

        public Builder(Long id, String name) {

            validateId(id);
            validateName(name);

            this.id = id;
            this.name = name;

        }

        public Builder detail(String detail) {

            validateDetail(detail);
            this.detail = detail;

            return this;

        }

        public Course build() {

            return new Course(this);

        }

        private void validateName(String name) {

            if (name.equals(null) || name.isEmpty()) {

                throw new IllegalArgumentException(
                        "Course should not be empty");

            }

        }

        private void validateId(Long id) {

            if (id == null || id == 0) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }

        private void validateDetail(String detail) {

            if (detail.equals(null) || detail.isEmpty()) {

                throw new IllegalArgumentException(
                        "detail should not be empty");

            }

        }

    }

}
