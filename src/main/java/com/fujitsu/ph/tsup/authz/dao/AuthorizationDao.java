/**
 * 
 */
package com.fujitsu.ph.tsup.authz.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;

/**
 * @author j.macabudbud
 *
 */
public interface AuthorizationDao {
	/**
	 * It will find employee with the given username
	 * @param username
	 * @return Set<EmployeeAuth>
	 */
	Set<EmployeeAuth> findByUsername(String username);
}
