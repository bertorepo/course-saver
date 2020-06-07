package com.fujitsu.ph.tsup.domain.freo;

public class CourseException extends RuntimeException {
	
	private static final long serialVersionUID = 2253098711666393964L;

	public CourseException(String crsmsg) {
		super(crsmsg);
	}
	
	public CourseException(String crsmsg , Throwable cause) {
		super(crsmsg , cause);
	}
}