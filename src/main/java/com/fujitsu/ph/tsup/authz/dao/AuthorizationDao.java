//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : AuthorizationDao.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.authz.domain.EmployeeAuth;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * <pre>
 * Dao Interface for Authorization
 * </pre>
 * 
 * @author WS) J.Macabudbud
 * @version 0.01
 */
public interface AuthorizationDao {
	/**
	 * <pre>
	 * It will find employee with the given username
	 * </pre>
	 * 
	 * @param username
	 * @return Set<EmployeeAuth>
	 */
	Set<EmployeeAuth> findByUsername(String username);

	/**
	 * <pre>
	 * It will find employee details wigth the given username
	 * </pre>
	 * 
	 * @param username
	 * @return Employee
	 */
	Employee findDetailsByUsername(String username);
}
