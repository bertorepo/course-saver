package com.fujitsu.ph.tsup.scheduling.web;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import com.fujitsu.ph.auth.model.FpiUser;


public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory <WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        FpiUser principal = new FpiUser();
        principal.setId(customUser.id());
        principal.setUserName(customUser.username());
        
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", principal.getGrantedAuthorities());
        
        context.setAuthentication(auth);
        return context;
    }

} 
