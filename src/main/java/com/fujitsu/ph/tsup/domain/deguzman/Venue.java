package com.fujitsu.ph.tsup.domain.deguzman;

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

        public Builder(String name) {
            validateName(name);

            this.name = name;
        }

        public Venue build() {
            return new Venue(this);
        }

        private void validateName(String name) {
            if (name == null || name.isEmpty() || name.length() < 5 || name.length() > 100) {
                throw new IllegalArgumentException("Venue Name should not be empty");
            }

        }
    }
}
