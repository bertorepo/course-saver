/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.roletype.domain.RoleType;

/**
 * RoleTypeDao class
 * 
 * @author rl.naval (New Creation by: rl.naval)
 * @version Revision: 0.01 Date: 2021-02-05
 */

public interface RoleTypeDao {
	
	//Method for finding Role Type by ID
	RoleType findRoleById(Long id);
	
	//Method for searching Role by Name
	Set<RoleType> findRoleTypeByName(String rolename);
	
	//Method for loading all Role Type in Role View
	Set<RoleType> loadAllRoleType();
	
	// Method for deleting Role Type by Id
    void deleteRoleTypeById(Long id);
    
    //Method for creating new Role Type
    void createRoleType(RoleType role);
    
    //Method for updating Role Type
    void updateRoleType(RoleType roleType);
}
