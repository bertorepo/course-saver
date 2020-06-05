package com.fujitsu.ph.tsup.domain.freo;

public class EmpException extends RuntimeException {
	
	private static final long serialVersionUID = 2253098711666393964L;

	public EmpException(String message) {
		super(message);
	}
	
	public EmpException(String message, Throwable cause) {
		super(message, cause);
	}
}
