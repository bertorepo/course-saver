package com.fujitsu.ph.tsup.domain.jimenez;

public class CourseException extends RuntimeException{
    private static final long serialVersionUID = 2253098711666393964L;
    
    public CourseException(String err) {
        super(err);
    }
    
    public CourseException(String err, Throwable cause) {
        super(err, cause);
    }
}

