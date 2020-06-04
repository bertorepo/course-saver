package com.fujitsu.ph.tsup.domain.deguzman;

public class Venue {
    private Long id;
    private String venueName;

    protected Venue() {

    }

    private Venue(Builder builder) {
        this.id = builder.id;
        this.venueName = builder.venueName;
    }

    public Long getId() {
        return id;
    }

    public String getVenueName() {
        return venueName;
    }

    public static class Builder {
        private Long id;
        private String venueName;

        public Builder(String venueName) {
            validateVenueName(venueName);

            this.venueName = venueName;
        }

        public Venue build() {
            return new Venue(this);
        }

        private void validateVenueName(String venueName) {
            if (venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("Venue Name should not be empty");
            } else if(venueName.length() < 5) {
                throw new IllegalArgumentException("Venue Name should not be less than 5 characters");
            } else if(venueName.length() > 100) {
                throw new IllegalArgumentException("Venue Name should not be more than 100 characters");
            }

        }
    }
}
