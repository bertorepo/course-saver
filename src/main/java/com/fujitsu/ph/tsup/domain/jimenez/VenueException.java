package com.fujitsu.ph.tsup.domain.jimenez;

public class VenueException extends RuntimeException{
    private static final long serialVersionUID = 2253098711666393964L;
    
    public VenueException(String err) {
        super(err);
    }
    
    public VenueException(String err, Throwable cause) {
        super(err, cause);
    }
}

