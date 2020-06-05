package com.fujitsu.ph.tsup.domain.jimenez;

public class CourseScheduleException extends RuntimeException{
    private static final long serialVersionUID = 2253098711666393964L;
    
    public CourseScheduleException(String err) {
        super(err);
    }
    
    public CourseScheduleException(String err, Throwable cause) {
        super(err, cause);
    }
}
