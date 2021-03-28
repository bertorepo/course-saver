package com.fujitsu.ph.tsup.attendance.web;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import org.springframework.security.test.context.support.WithSecurityContext;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//===============================================================================
//$Id:PR03$
//Project Name : Training Sign up
//System Name  : Attendance process 
//Class Name   : Attend.java
//
//<<Modification History>>
//Version | Date       | Updated By                            | Content
//--------+------------+---------------------------------------+-----------------
//0.01    | 07/10/2020 | WS) K.Abad, WS) J.Iwarat, WS) R.Ramos | New Creation
//===============================================================================
/**
* <pre>
* Custom mock user interface for controller testing
* </pre>
* 
* @version 0.01
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/
@Documented
@Retention(RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    long id() default 1L;
    String username() default "l.lorenzo" ;
}
