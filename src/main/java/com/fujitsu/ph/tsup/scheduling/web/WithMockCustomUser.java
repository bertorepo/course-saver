package com.fujitsu.ph.tsup.scheduling.web;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import org.springframework.security.test.context.support.WithSecurityContext; 

@Documented
@Retention(RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    long id();
    String username();
}
