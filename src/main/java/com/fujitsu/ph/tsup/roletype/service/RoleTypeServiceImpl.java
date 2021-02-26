/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
//0.01    | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02    | 2021/02/15 | WS) rl.naval          | Updated
//0.03    | 2021/02/17 | WS) j.sayaboc         | Updated
//0.04    | 2021/02/18 | WS) i.fajardo         | Updated
//0.05    | 2021/02/23 | WS) s.labador         | Updated
//0.06    | 2021/02/24 | WS) p.cui             | Updated
//==================================================================================================
/**
 * <pre>
 * JavaBean for RoleTypeServiceImpl
 * 
 * <pre>
 * 
 * @version 0.06
 * @author rl.naval
 * @author j.sayaboc
 * @author i.fajardo
 * @author s.labador
 * @author p.cui
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
        if (StringUtils.isEmpty(roleTypeResult)) {
            throw new IllegalArgumentException("No role types available");
        }
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

    /**
     * Find if Role name is already existing
     * 
     * @param rolename Role name
     * @param id Role id
     * @return isRoleExisting
     */
    @Override
    public boolean findIfRoleNameExists(String rolename, Long id) {
        Set<RoleType> roleList = roleTypeDao.findIfRoleNameExists(rolename, id);
        boolean isRoleExisting = false;

        try {
            if (!roleList.isEmpty()) {
                isRoleExisting = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRoleExisting;
    }

    /**
     * Loads all role types
     * 
     * @return roleTypeDao.loadAllRoleType
     */
    @Override
    public Set<RoleType> loadAllRoleType() {
        return roleTypeDao.loadAllRoleType();
    }

    /**
     * Loads all role types with pagination
     * 
     * @return roleTypeDao.loadAllRoleType
     */
    @Override
    public Set<RoleType> loadAllRoleType(int pageSize, int page) {
        return roleTypeDao.loadAllRoleType(pageSize,page);
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

    /**
     * Method for updating Role Type
     * 
     * @param id Role id
     * @param roleType RoleType
     */
    @Override
    public void updateRoleType(Long id, RoleType roleType) {
        roleTypeDao.updateRoleType(id, roleType);
    }

    @Override
    public Set<RoleType> findRoleTypeByKeyword(String keyword) {
        Set<RoleType> roleFormList = roleTypeDao.findRoleTypeByKeyword(keyword);
        try {
            if (StringUtils.isEmpty(roleFormList)) {
                throw new IllegalArgumentException("No Role Types Available.");
            } else {
                return roleFormList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return roleFormList;
    }
}