package com.fujitsu.ph.tsup.auth.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.auth.provider.FpiLdapAuthenticationProvider;

/**
 * <pre>
 * It is a Class for employee authentication
 * </pre>
 * 
 * @version 0.01
 * @author j.macabudbud
 */
@Component
public class TsupAuthProvider extends FpiLdapAuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(TsupAuthProvider.class);
	
	/* Uncomment this once the Class for AuthorizationService is created */ 
	//@Autowired
	//private AuthorizationService authorizationService;
	
	@Override
	protected FpiUser getUser(String username) {
		FpiUser fpiUser = new FpiUser();
		
		fpiUser.setId(1L);
		fpiUser.setUserName(username);
		
		/* Uncomment this once the Class for AuthorizationService is created */ 
		//List<String> roles = authorizationService.findByUsername(username);
		//fpiUser.setRoles(roles);
		
		logger.debug("User:{}", fpiUser);
		
		return fpiUser;
	}

}
