package com.fujitsu.ph.tsup.domain.oviedo;

public class ApplicationException extends RuntimeException{
	private static final long serialVersionUID = 2253098711666393964L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}
}
