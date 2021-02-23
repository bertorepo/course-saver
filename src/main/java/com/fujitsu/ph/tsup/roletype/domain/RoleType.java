/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.domain;

import org.springframework.util.StringUtils;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleType.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02    | 2021/02/16 | WS) s.labador         | Updated
//==================================================================================================

/**
 * <pre>
 * Role Type class
 * 
 * <pre>
 * 
 * @version 0.02
 * @author rl.naval
 * @author s.labador
 */
public class RoleType {

    private Long id;
    private String rolename;
    private String roledesc;

    /**
     * Empty Constructor for RoleType class
     */
    protected RoleType() {

    }

    /**
     * Role type Constructor
     * @param builder Builder
     */
    private RoleType(Builder builder) {
        this.id = builder.id;
        this.rolename = builder.rolename;
        this.roledesc = builder.roledesc;
    }

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

    /**
     * Builder Class
     */
    public static class Builder {
        private Long id;
        private String rolename;
        private String roledesc;

        public Builder(String rolename) {
            validateRolename(rolename);
            this.rolename = rolename;
        }

        public Builder(Long id, String rolename) {
            validateId(id);
            validateRolename(rolename);
            this.id = id;
            this.rolename = rolename;
        }

        public Builder(String rolename, String roledesc) {
            validateRolename(rolename);
            validateRoleDesc(roledesc);

            this.rolename = rolename;
            this.roledesc = roledesc;
        }

        public Builder roledesc(String roledesc) {
            validateRoleDesc(roledesc);
            this.roledesc = roledesc;

            return this;
        }

        public RoleType build() {
            return new RoleType(this);
        }

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

        /**
         * Validate role name if null or empty
         * @param rolename Role Name
         */
        private void validateRolename(String rolename) {
            if (StringUtils.isEmpty(rolename)) {

                throw new IllegalArgumentException("Role name should not be empty");

            }
        }

        /**
         * Validate role id if null or empty
         * @param id Role id
         */
        private void validateId(Long id) {

            if (id == null || id == 0) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }

        /**
         * Validate role description if null or empty
         * @param roledesc Role description
         */
        private void validateRoleDesc(String roledesc) {

            if (StringUtils.isEmpty(roledesc)) {

                throw new IllegalArgumentException("Role Description should not be empty");

            }

        }
    }

}
