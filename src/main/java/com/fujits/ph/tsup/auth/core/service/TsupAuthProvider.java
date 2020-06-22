package com.fujits.ph.tsup.auth.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fujitsu.ph.auth.model.FpiUser;
import com.fujitsu.ph.auth.provider.FpiLdapAuthenticationProvider;
import com.fujitsu.ph.tsup.authz.core.service.AuthorizationService;
import com.fujitsu.ph.tsup.employee.management.model.Employee;
import com.fujitsu.ph.tsup.employee.management.service.EmployeeService;


@Component
public class TsupAuthProvider extends FpiLdapAuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(TsupAuthProvider.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AuthorizationService authorizationService;
	
	@Override
	protected FpiUser getUser(String username) {
		FpiUser fpiUser = new FpiUser();
		
		Employee employee = employeeService.findByUserName(username);
		fpiUser.setEmployeeNumber(employee.getNumber());
		fpiUser.setId(employee.getId());
		fpiUser.setFirstName(employee.getName().getFirstName());
		fpiUser.setLastName(employee.getName().getLastName());
		fpiUser.setUserName(username);
		
		List<String> roles = authorizationService.findByUserName(username);
		fpiUser.setRoles(roles);
		
		logger.debug("User:{}", fpiUser);
		
		return fpiUser;
	}

}
