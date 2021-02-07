/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roleType.model;

/**
 * RoleTypeForm class
 * 
 * @author p.cui (New Creation by: p.cui)
 * @version Revision: 0.01 Date: 2021-02-08
 */
public class RoleTypeForm {

    private Long id;
    private String roleType;
    private String description;

    /**
     * Getter method for RoleType Id
     * 
     * @return RoleType Id
     */
    public Long getId() {

        return id;

    }

    /**
     * Setter method for RoleType Id
     * 
     * @param id RoleType Id
     */
    public void setId(Long id) {

        this.id = id;

    }

    /**
     * Getter method for RoleType Name
     * 
     * @return RoleType roleType
     */
    public String getRoleType() {

        return roleType;

    }

    /**
     * Setter method for RoleType Name
     * 
     * @param name RoleType Name
     */
    public void setRoleType(String roleType) {

        this.roleType = roleType;

    }

    /**
     * Getter Method for RoleType Description
     * 
     * @return RoleType Description
     */
    public String getDescription() {

        return description;

    }

    /**
     * Setter Method for RoleType Description
     * 
     * @param description RoleType Description
     */
    public void setDescription(String description) {

        this.description = description;

    }

    @Override
    public String toString() {

        return "[ID " + id + " RoleType " + roleType + " Description " + description + "]";

    }
}