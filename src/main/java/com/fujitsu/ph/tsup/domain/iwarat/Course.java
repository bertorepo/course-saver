package com.fujitsu.ph.tsup.domain.iwarat;

public class Course {
    private Long id;
    private String name;

    protected Course() {

    }

    private Course(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
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
            validateName(name);

            this.id = id;
            this.name = name;
        }

        public Course build() {
            return new Course(this);
        }

        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Id should not be null");
            }
        }

        private void validateName(String name) {
            if (name == null || name.isEmpty() || name.length()<1 || name.length()>100) {
                throw new IllegalArgumentException("Course name should not be empty");
            }
        }
    }

}
