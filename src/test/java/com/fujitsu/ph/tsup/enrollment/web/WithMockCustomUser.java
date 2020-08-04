package com.fujitsu.ph.tsup.enrollment.web;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import org.springframework.security.test.context.support.WithSecurityContext;

@Documented
@Retention(RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

	long id() default 1L;

	String username() default "l.lorenzo";

}
