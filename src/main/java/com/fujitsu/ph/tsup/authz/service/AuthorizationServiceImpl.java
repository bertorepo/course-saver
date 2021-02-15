//==================================================================================================
// Project Name :  Training Sign Up Project
// System Name  : Training Sign Up Project
// Class Name   : AuthorizationServiceImpl.java
//
// <<Modification History>>
// Version | Date       | Updated By            | Content
// --------+------------+-----------------------+---------------------------------------------------
// 1.0.0   | 2020/07/29 | WS) J.Macabudbud      | Initial Version
//==================================================================================================
package com.fujitsu.ph.tsup.authz.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.authz.dao.AuthorizationDao;
import com.fujitsu.ph.tsup.authz.domain.EmployeeAuth;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * <pre>
 * Service Implementation for User Authorization
 * </pre>
 * 
 * @author WS) J.Macabudbud
 * @version 0.01 Revision Date: 2021-02-15
 * 
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private AuthorizationDao authorizationDao;

    /**
     * <pre>
     * Find Employee by Username
     * </pre>
     * 
     * @param username
     * @return Set<EmployeeAuth>
     * @throws IllegalArgumentException
     * 
     */
    @Override
    public Set<EmployeeAuth> findByUsername(String username) {
        try {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username should not be empty.");
            }
            return authorizationDao.findByUsername(username);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Can't access employee auth data.");
        }
    }

    /**
     * <pre>
     * Find Employee Details by Username
     * </pre>
     * 
     * @param username
     * @return Employee
     * @throws IllegalArgumentException
     * 
     */
    @Override
    public Employee findDetailsByUsername(String username) {
        try {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username should not be empty.");
            }
            return authorizationDao.findDetailsByUsername(username);
        } catch (DataAccessException e) {
            /*
             * Author : k.sala
             * 
             * return null instead of throwing the exception
             */
            // throw new IllegalArgumentException("Can't access employee data.");
            return null;
        }
    }

}
