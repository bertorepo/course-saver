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
	 * The data access interface for authorization related database access
	 * 
	 * @param username
	 * @return
	 */
	EmployeeAuth findByUsername(String username);
}
