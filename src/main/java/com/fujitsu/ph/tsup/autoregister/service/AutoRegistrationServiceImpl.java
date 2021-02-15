/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.autoregister.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.autoregister.dao.AutoRegistrationDao;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistration;
import com.fujitsu.ph.tsup.autoregister.model.AutoRegistrationDepartment;

/**
 * AutoRegistrationServiceImpl class
 * 
 * @author k.sala (New Creation by: k.sala)
 * @version 0.01
 */
@Service
public class AutoRegistrationServiceImpl implements AutoRegistrationService {

    @Autowired
    AutoRegistrationDao autoRegistrationDao;

    // Method for adding new member
    @Override
    public void addAutoRegistration(AutoRegistration autoRegistration) {

        autoRegistrationDao.addAutoRegistration(autoRegistration);
    }

    /**
     * @author s.maluya Get all Departments
     */
    @Override
    public List<AutoRegistrationDepartment> getAllDepartment() {

        return autoRegistrationDao.getAllDepartment();
    }

}
