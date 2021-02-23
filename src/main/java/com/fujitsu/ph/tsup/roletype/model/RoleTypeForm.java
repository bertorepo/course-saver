/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.model;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeForm.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02    | 2021/02/16 | WS) s.labador         | Updated
//==================================================================================================

/**
 * <pre>
 * Role Type Form class
 * 
 * <pre>
 * 
 * @version 0.02
 * @author rl.naval
 * @author s.labador
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
