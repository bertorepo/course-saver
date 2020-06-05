package com.fujitsu.ph.tsup.domain.macabugao;

public class IllegalApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 2253098711666393964L;
	
	public IllegalApplicationException(String message) {
	      super(message);
	   }
	public IllegalApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	}

