//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : TsupAuthProvider.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.auth.provider.FpiLdapAuthenticationProvider;
import com.fujitsu.ph.tsup.authz.domain.EmployeeAuth;
import com.fujitsu.ph.tsup.authz.service.AuthorizationService;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * <pre>
 * It is a Class for employee authentication
 * </pre>
 * 
 * @version 0.01
 * @author WS) J.Macabudbud
 */
@Component
public class TsupAuthProvider extends FpiLdapAuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(TsupAuthProvider.class);

	@Autowired
	private AuthorizationService authorizationService;

	@Override
	/**
	 * Setting the user details in FpiUser
	 * 
	 * @param username
	 * @return FpiUser details
	 */
	protected FpiUser getUser(String username) {
		FpiUser fpiUser = new FpiUser();

		Employee employee = authorizationService.findDetailsByUsername(username);
		if(employee == null) {
			fpiUser.setUserName(username);
			return fpiUser;
		}

		fpiUser.setId(employee.getId());
		fpiUser.setEmployeeNumber(employee.getNumber());
		fpiUser.setFirstName(employee.getFirstName());
		fpiUser.setLastName(employee.getLastName());
		fpiUser.setUserName(username);

		Set<EmployeeAuth> roles = authorizationService.findByUsername(username);
		List<String> rolesList = new ArrayList<>();

		for (EmployeeAuth role : roles) {
			Set<String> itr = role.getAuthzSet();
			for (String i : itr) {
				rolesList.add(i);
			}
		}
		
		fpiUser.setRoles(rolesList);
		
		logger.debug("User:{}", fpiUser);

		return fpiUser;
	}

}
