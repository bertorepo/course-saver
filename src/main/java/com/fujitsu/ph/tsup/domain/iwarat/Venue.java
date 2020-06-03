package com.fujitsu.ph.tsup.domain.iwarat;


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
            validateId(id);
            validateName(name);

            this.id = id;
            this.name = name;
        }

        public Venue build() {
            return new Venue(this);
        }

        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Id should not be null");
            }
        }

        private void validateName(String name) {
            if (name == null || name.isEmpty() || name.length() < 1 || name.length() > 100) {
                throw new IllegalArgumentException("Venue name should not be empty");
            }
        }
    }

}