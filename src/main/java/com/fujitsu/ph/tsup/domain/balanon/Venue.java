package com.fujitsu.ph.tsup.domain.balanon;

public class Venue {
    private Long Id;
    private String Venue;

    private Venue(Builder builder) {
        this.Id = builder.Id;
        this.Venue = builder.Venue;
    }

    public Long getId() {
        return Id;
    }

    public String getVenue() {
        return Venue;
    }

    public static class Builder {
        private Long Id;
        private String Venue;

        public Builder(String Venue) {
            validateVenue(Venue);

            this.Venue = Venue;
        }

        private void validateVenue(String Venue) {
            if (Venue == null || Venue.isEmpty() || Venue.length() < 1 || Venue.length() > 101) {
                throw new IllegalArgumentException("Venue should not be empty.");
            }

        }

    }

}
