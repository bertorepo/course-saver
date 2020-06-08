package com.fujitsu.ph.tsup.domain.angara;

public class Venue {
    private Long id;
    private String venueName;

    protected Venue() {
    }

    private Venue(Builder builder) {
        this.id = builder.id;
        this.venueName = builder.venueName;
    }

    String getVenueName() {
        return venueName;
    }

    Long getId() {
        return id;
    }

    public static class Builder {
        public String venueName;
        public Long id;

        public Builder(String venueName, Long id) {
            ValidateVenueName(venueName);
            ValidateId(id);

            this.venueName = venueName;
            this.id = id;
        }

        public Builder(String venueName2, String string) {
            // TODO Auto-generated constructor stub
        }

        private void ValidateId(Long id) {
            if (id == 0 || id == null || id <= 10 || id >= 15) {
                throw new IllegalArgumentException("Provide appropriate Id number.");
            }
        }

        private void ValidateVenueName(String venueName) {
            if (venueName.isEmpty() || venueName == null || venueName.length() <= 3 || venueName.length() >= 50) {
                throw new IllegalArgumentException("Provide appropriate venue name.");
            }

        }

        public Venue build() {
            // TODO Auto-generated method stub
            return null;
        }

        

    }
}
