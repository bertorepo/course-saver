package com.fujitsu.ph.tsup.domain.freo;

public class CourseScheduleException extends RuntimeException {
	 private static final long serialVersionUID = 2253098711666393964L;
	    
	    public CourseScheduleException(String e) {
	        super(e);
	    }
	   
	    public CourseScheduleException(String e, Throwable cause) {
	        super(e, cause);
	    }
}