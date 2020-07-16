package com.fujitsu.ph.tsup.attendance.web;


import com.fujitsu.ph.auth.model.FpiUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
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
* Custom mock user for controller testing that implements for WithSecurityContextFactory
* </pre>
* 
* @version 0.01
* @author k.abad
* @author j.iwarat
* @author r.ramos
* 
*/

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
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
