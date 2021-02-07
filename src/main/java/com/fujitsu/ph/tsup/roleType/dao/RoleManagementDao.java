/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.role.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.role.model.Course;

/**
 * RoleManagementDao class
 * 
 * @author p.cui (New Creation by: p.cui)
 * @version Revision: 0.01 Date: 2021-02-05
 */
public interface RoleManagementDao {

    // Method for loading all roles
    Set<Role> loadAllRoleType();
    
    // Method for creating courses
    void createRoleType(Role role);

    // Method for updating role
    void updateRoleType();

    // Method for deleting Course by Id
    void deleteRoleType();
    
}
