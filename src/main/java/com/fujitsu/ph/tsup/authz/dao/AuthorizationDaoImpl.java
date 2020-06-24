/**
 * 
 */
package com.fujitsu.ph.tsup.authz.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;
import com.fujitsu.ph.tsup.authz.infrastructure.EmployeeAuthRepository;

/**
 * @author j.macabudbud
 *
 */
public class AuthorizationDaoImpl implements AuthorizationDao {
	@Autowired
	private EmployeeAuthRepository employeeAuthRepository;
	
	@Override
	public EmployeeAuth findByUsername(String username) {
		return (EmployeeAuth) employeeAuthRepository.findByUsername(username);
	}

}
