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
//1.0.0   | 2021/02/05 | WS) rl.naval          | Initial Version
//==================================================================================================

@Service
public class RoleTypeServiceImpl implements RoleTypeService {
    @Autowired
    RoleTypeDao roleTypeDao;

    @Override
    public RoleType findRoleById(Long id) {
        RoleType roleTypeResult = roleTypeDao.findRoleById(id);
        return roleTypeResult;
    }

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

    @Override
    public Set<RoleType> loadAllRoleType() {
        return roleTypeDao.loadAllRoleType();
    }

    @Override
    public void deleteRoleTypeById(Long id) {
        roleTypeDao.deleteRoleTypeById(id);
    }

    @Override
    public void createRoleType(RoleType role) {
        roleTypeDao.createRoleType(role);
    }

    @Override
    public void updateRoleType(RoleType roleType) {
        roleTypeDao.updateRoleType(roleType);

    }

}
