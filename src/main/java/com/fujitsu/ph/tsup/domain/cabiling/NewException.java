package com.fujitsu.ph.tsup.domain.cabiling;

public class NewException extends RuntimeException {

	private static final long serialVersionUID = 2253098711666393964L;

	public NewException(String message) {
			super(message);
		}

	public NewException(String message, Throwable cause) {
			super(message, cause);
		}
}