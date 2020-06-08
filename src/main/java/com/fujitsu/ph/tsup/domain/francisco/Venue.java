package com.fujitsu.ph.tsup.domain.francisco;

public class Venue {
    private Long id;
    private String name;

    protected Venue() {

    }

    private Venue(Builder builder) {
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
            validateName(name);
            this.id = id;
            this.name = name;
        }

        public Venue build() {
            return new Venue(this);
        }

        private void validateName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("venue name should not be empty!");
            } else if (name.length() > 100) {
                throw new IllegalArgumentException("venue name should not exceed 100 characters!");
            }
        }
    }
}
