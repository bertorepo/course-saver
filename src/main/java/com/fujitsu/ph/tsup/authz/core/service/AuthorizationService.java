//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : AuthorizationService.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.core.service;

import java.util.Set;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;
import com.fujitsu.ph.tsup.employee.management.model.Employee;

/**
 * <pre>
 * Service Interface for Authorization
 * </pre>
 * 
 * @author WS) J.Macabudbud
 * @version 0.01
 * 
 */
public interface AuthorizationService {
	/**
	 * <pre>
	 * Find employee authorization set
	 * </pre>
	 * 
	 * @param username
	 * @return Set<EmployeeAuth>
	 */
	Set<EmployeeAuth> findByUsername(String username);

	/**
	 * <pre>
	 * Find employee details
	 * </pre>
	 * 
	 * @param username
	 * @return Employee
	 */
	Employee findDetailsByUsername(String username);

}
