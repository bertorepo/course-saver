package com.fujitsu.ph.tsup.domain.lumontad;

public class Venue {
    private Long venueID;
    private String venueName;
    
    protected Venue() {    
    }
    
    private Venue(Builder builder) {
        this.venueID = builder.venueID;
        this.venueName = builder.venueName;
    }
    
    public Long getId() {
        return venueID;
    }

    public String getVenueName() {
        return venueName;
    }

    public static class Builder {
        private Long venueID;
        private String venueName;

        public Builder(String venueName) {
            validateVenueName(venueName);

            this.venueName = venueName;
        }
        
        public Venue build() {
            return new Venue(this);
        }
        
        private void validateVenueName(String venueName) {
            if (venueName == null) {
                throw new IllegalArgumentException("Venue Name cannot be Empty");
            }
            else if (venueName.isEmpty()) {
                throw new IllegalArgumentException("Venue Name cannot be Empty");
            }
            else if (venueName.length() < 10) {
                throw new IllegalArgumentException("Venue Name cannot be Empty");
            }
            else if (venueName.length() > 50) {
                throw new IllegalArgumentException("Venue Name cannot be Empty");
            }  
        }
    }
}
