/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.authz.autoregister.service;

import java.util.List;

import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistrationDepartment;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * AutoRegistrationService class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
public interface AutoRegistrationService {

    // Method for adding new member
    int addAutoRegistration(AutoRegistration autoRegistration);

    // Method for loading all Departments
    List<AutoRegistrationDepartment> getAllDepartments();
    
    // Method for fetching Employee Details with the given Employee Number
    Employee findDetailsByEmployeeNumber(String employeeNumber);

}
