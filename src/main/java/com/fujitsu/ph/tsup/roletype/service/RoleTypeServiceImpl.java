/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujitsu.ph.tsup.roletype.dao.RoleTypeDao;
import com.fujitsu.ph.tsup.roletype.domain.RoleType;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeServiceImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01   | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02   | 2021/02/05 | WS) s.labador         | Updated
//==================================================================================================

/**
 * <pre>
 * JavaBean for RoleTypeServiceImpl
 * 
 * <pre>
 * 
 * @version 0.01
 * @author rl.naval
 */
@Service
public class RoleTypeServiceImpl implements RoleTypeService {
    @Autowired
    RoleTypeDao roleTypeDao;

    /**
     * Finds Role Type by id
     * 
     * @param id Role Type id
     * @return roleTypeResult
     * 
     */
    @Override
    public RoleType findRoleById(Long id) {
        RoleType roleTypeResult = roleTypeDao.findRoleById(id);
        return roleTypeResult;
    }

    /**
     * Finds Role Type by name
     * 
     * @param rolename Role name
     * @return roleFormList
     * 
     */
    @Override
    public Set<RoleType> findRoleTypeByName(String rolename) {

        Set<RoleType> roleFormList = roleTypeDao.findRoleTypeByName(rolename);

        try {
            if (roleFormList == null || roleFormList.isEmpty()) {
                return null;
            } else {
                return roleFormList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return roleFormList;
    }

    // Load all role type
    @Override
    public Set<RoleType> loadAllRoleType() {
        return roleTypeDao.loadAllRoleType();
    }

    // Delete role type by id
    @Override
    public void deleteRoleTypeById(Long id) {
        roleTypeDao.deleteRoleTypeById(id);
    }

    // Create role type
    @Override
    public void createRoleType(RoleType role) {
        roleTypeDao.createRoleType(role);
    }

    // Update role type
    @Override
    public void updateRoleType(RoleType roleType) {
        roleTypeDao.updateRoleType(roleType);

    }

}
