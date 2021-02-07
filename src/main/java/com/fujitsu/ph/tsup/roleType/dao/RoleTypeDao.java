/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roleType.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.roleType.model.RoleType;

/**
 * RoleManagementDao class
 * 
 * @author p.cui (New Creation by: p.cui)
 * @version Revision: 0.01 Date: 2021-02-05
 */
public interface RoleTypeDao {

    // Method for loading all roletype
    Set<RoleType> loadAllRoleType();
    
    // Method for creating roletype
    void createRoleType(RoleType role);

    // Method for updating roletype
    void updateRoleType();

    // Method for deleting roletype
    void deleteRoleType();
    
}
