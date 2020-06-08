package com.fujitsu.ph.tsup.domain.cabiling;

public class CourseException extends RuntimeException {

	private static final long serialVersionUID = 2253098711666393964L;

	public CourseException(String message) {
			super(message);
		}

	public CourseException(String message, Throwable cause) {
			super(message, cause);
		}
}
