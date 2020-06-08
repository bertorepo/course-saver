package com.fujitsu.ph.tsup.domain.francisco;

public class ApplicationException extends RuntimeException{

    private static final long serialVersionUID = 5860276419849695422L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
