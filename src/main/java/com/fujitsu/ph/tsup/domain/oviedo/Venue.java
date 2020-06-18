package com.fujitsu.ph.tsup.domain.oviedo;

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
        public Builder(Long id, String venueName) {
            validateVenueName(venueName);

            this.id = id;
            this.venueName = venueName;
        }

        public Venue build() {
            return new Venue(this);
        }

        private void validateVenueName(String venueName) {
            if (venueName == null || venueName.isEmpty())
                throw new IllegalArgumentException("Venue Name should not be empty");

        }
    }
}
