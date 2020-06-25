/**
 * 
 */
package com.fujitsu.ph.tsup.authz.dao;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;

/**
 * @author j.macabudbud
 *
 */
public interface AuthorizationDao {
	/**
	 * It will find employee with the given username
	 * @param username
	 * @return
	 */
	EmployeeAuth findByUsername(String username);
}
