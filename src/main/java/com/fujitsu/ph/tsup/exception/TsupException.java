/**
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.exception;

//==================================================================================================
//Project Name : Training Sign Up
//Class Name   : TsupException.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/06/30 | WS) mi.aguinaldo       | Initial Version
//==================================================================================================

public class TsupException extends RuntimeException {

    /**
     * @param message
     * @param cause
     */
    public TsupException(String message, Throwable cause) {
	super(message, cause);

    }

    /**
     * @param message
     */
    public TsupException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public TsupException(Throwable cause) {
	super(cause);
    }

}
