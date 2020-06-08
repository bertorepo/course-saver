package com.fujitsu.ph.tsup.domain.freo;

public class EmployeeException extends RuntimeException{ 
	 private static final long serialVersionUID = 2253098711666393964L;
	    
	    public EmployeeException(String ee) {
	        super(ee);
	    }
	    public EmployeeException(String ee, Throwable cause) {
	        super(ee, cause);
	    }
}