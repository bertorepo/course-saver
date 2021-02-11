/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.model;

/**
 * RoleTypeForm class
 * 
 * @author rl.naval (New Creation by: rl.naval)
 * @version Revision: 0.01 Date: 2021-02-05
 */
public class RoleTypeForm {
    private Long id;
    private String rolename;
    private String roledesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Override
    public String toString() {

        return "[ID " + id + " Rolename " + rolename + " Roledesc " + roledesc + "]";

    }

}
