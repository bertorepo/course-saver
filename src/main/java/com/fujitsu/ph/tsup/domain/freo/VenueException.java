package com.fujitsu.ph.tsup.domain.freo;

public class VenueException extends RuntimeException {
	   private static final long serialVersionUID = 2253098711666393964L;
	    
	    public VenueException(String vn) {
	        super(vn);
	    }
	    
	    public VenueException(String vn, Throwable cause) {
	        super(vn, cause);
	    }
}