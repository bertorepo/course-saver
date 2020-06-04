package com.fujitsu.ph.tsup.domain.jimenez;


public class Venue {
    private Long id;
    private String name;
    
    protected Venue() {
    }
    
    private Venue(Builder build) {
        this.id = build.id;
        this.name = build.name;
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
            validateVenueName(name);
            
            this.id = id;
            this.name = name;
        }
        
        public Venue builder() {
            return new Venue(this);
        }
        
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
        
        private void validateVenueName(String name) {
            if (name == null) {
                throw new NullPointerException("Venue Name should not be null");
            } else if (name.isEmpty()) {
                throw new IllegalArgumentException("Venue Name should not be Empty");
            } else if (name.length() > 100) {
                throw new IllegalArgumentException("Venue Name Length exceeds the Limit");
            }
        }
    }
}
