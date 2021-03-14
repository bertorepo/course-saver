/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.authz.autoregister.dao;

import java.util.List;

import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistrationDepartment;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * AutoRegistrationDao class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
public interface AutoRegistrationDao {

    // Method for adding new member
    void addAutoRegistration(AutoRegistration autoRegistration);

    // Method for loading all Departments
    List<AutoRegistrationDepartment> getAllDepartments();
    
    // Method for fetching Employee Details with the given Employee Number
    Employee findDetailsByEmployeeNumber(String employeeNumber);
}
