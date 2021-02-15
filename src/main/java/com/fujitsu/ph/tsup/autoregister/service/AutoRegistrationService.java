/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.autoregister.service;

import java.util.List;

import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;

/**
 * AutoRegistrationService class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
public interface AutoRegistrationService {

    // Method for adding new member
    void addAutoRegistration(AutoRegistration autoRegistration);

    // Method for loading all Departments
    List<AutoRegistrationDepartment> getAllDepartments();

}
