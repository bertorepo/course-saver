/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.authz.autoregister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.authz.autoregister.dao.AutoRegistrationDao;
import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.authz.autoregister.model.AutoRegistrationDepartment;
import com.fujitsu.ph.tsup.common.domain.Employee;

/**
 * AutoRegistrationServiceImpl class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
@Service
public class AutoRegistrationServiceImpl implements AutoRegistrationService {

    @Autowired
    private AutoRegistrationDao autoRegistrationDao;

    // Method for adding new member
    @Override
    public int addAutoRegistration(AutoRegistration autoRegistration) {
    	try {
            if (autoRegistration == null) {
                throw new IllegalArgumentException("Please check data should not be empty.");
            }
            return autoRegistrationDao.addAutoRegistration(autoRegistration);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Can't access employee data.");
        }
    }

    /**
     * @author s.maluya Get all Departments
     */
    @Override
    public List<AutoRegistrationDepartment> getAllDepartments() {
    	try {
            if (autoRegistrationDao.getAllDepartments() == null) {
                throw new IllegalArgumentException("Cannot load data from Department.");
            }
            return autoRegistrationDao.getAllDepartments();
        } catch (DataAccessException e) {
        	throw new IllegalArgumentException("Can't access department data.");
        }
    }

    @Override
    public Employee findDetailsByEmployeeNumber(String employeeNumber) {
        try {
            if (employeeNumber == null || employeeNumber.isEmpty()) {
                throw new IllegalArgumentException("Employee Number should not be empty.");
            }
            return autoRegistrationDao.findDetailsByEmployeeNumber(employeeNumber);
        } catch (DataAccessException e) {
            return null;
        }
    }

}
