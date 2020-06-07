package com.fujitsu.ph.tsup.domain.abad;

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

        public Builder(Long id, String venueName) {
            validateVenueName(venueName);

            this.id = id;
            this.venueName = venueName;
        }
        
        public Builder(String venueName) {
            validateVenueName(venueName);

            this.venueName = venueName;
        }
        
        public Venue build() {
            return new Venue(this);
        }
        
        private void validateVenueName(String venueName) {
            if (venueName == null || venueName.isEmpty()) {
                throw new IllegalArgumentException("venue name should not be empty");
            }
            else if (venueName.length() < 10) {
                throw new IllegalArgumentException("venue name should not be less than 10 characters");
            }
            else if (venueName.length() > 50 ) {
                throw new IllegalArgumentException("venue name should not exceeds by 50 characters");   
            }
        }
    }
}