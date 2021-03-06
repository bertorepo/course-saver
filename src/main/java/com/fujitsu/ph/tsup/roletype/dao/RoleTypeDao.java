/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.dao;

import java.util.Set;

import com.fujitsu.ph.tsup.roletype.domain.RoleType;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeDao.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02    | 2021/02/15 | WS) rl.naval          | Updated
//0.03    | 2021/02/17 | WS) c.sinda           | Updated
//0.04    | 2021/02/22 | WS) j.sayaboc         | Updated
//0.05    | 2021/02/23 | WS) s.labador         | Updated
//0.06    | 2021/02/24 | WS) p.cui             | Updated
//0.07    | 2021/03/11 | WS) p.cui             | Updated
//==================================================================================================

/**
 * RoleTypeDao class
 * 
 * @version 0.07
 * @author rl.naval
 * @author c.sinda
 * @author s.labador
 * @author j.sayaboc
 * @author p.cui
 */

public interface RoleTypeDao {

    // Method for finding Role Type by ID
    RoleType findRoleById(Long id);

    // Method for searching Role by Name
    Set<RoleType> findRoleTypeByName(String rolename);

    // Method for searching if Role is already existing
    Set<RoleType> findIfRoleNameExists(String rolename, Long id);

    // Method for searching Role by keyword
    Set<RoleType> findRoleTypeByKeyword(String keyword);
    
    // Method for searching Role by keyword with pagination
    Set<RoleType> findRoleTypeByKeyword(String keyword, int pageSize, int page);

    // Method for loading all Role Type in Role View
    Set<RoleType> loadAllRoleType();

    //Method for loading all Role Type in Role View with pagination
    Set<RoleType> loadAllRoleType(int total, int page);

    // Method for deleting Role Type by Id
    void deleteRoleTypeById(Long id);

    // Method for creating new Role Type
    void createRoleType(RoleType role);

    // Method for updating Role Type
    void updateRoleType(Long id, RoleType roleType);

}
