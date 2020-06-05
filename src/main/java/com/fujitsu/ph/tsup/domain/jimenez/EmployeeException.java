package com.fujitsu.ph.tsup.domain.jimenez;

public class EmployeeException extends RuntimeException{
    private static final long serialVersionUID = 2253098711666393964L;
    
    public EmployeeException(String err) {
        super(err);
    }
    
    public EmployeeException(String err, Throwable cause) {
        super(err, cause);
    }
}

