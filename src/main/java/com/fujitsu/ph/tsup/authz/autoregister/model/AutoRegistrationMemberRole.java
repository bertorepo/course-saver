/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.authz.autoregister.model;

//=======================================================
//$Id: 
//Project Name: Training Sign Up
//Class Name: AutoRegistrationMemberRole.java
//
//<<Modification History>>
//Version | Date       | Updated by       | Content
//--------+------------+------------------+---------------
//0.01    | 2021/06/07 | WS) R.Gaquit	  | Created
//=======================================================


/**
 * AutoRegistrationMemberRole class
 * 
 * @author r.gaquit	 (New Creation by: r.gaquit)
 * @version 0.01
 */
public class AutoRegistrationMemberRole {
    private Long id;
    private String roleType;

    protected AutoRegistrationMemberRole() {

    }

    /**
     * AutoRegistrationMemberRole Constructor
     * @param Builder builder
     */
    private AutoRegistrationMemberRole(Builder builder) {
        this.id = builder.id;
        this.roleType = builder.roleType;
    }

    /**
     * Getter method for AutoRegistrationMemberRole Id
     * 
     * @return AutoRegistrationMemberRole id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for AutoRegistrationMemberRole Id
     * 
     * @param AutoRegistrationMemberRole id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for AutoRegistrationMemberRole Member Role
     * 
     * @return AutoRegistrationMemberRole member role
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * Setter method for AutoRegistrationMemberRole Member Role
     * 
     * @param AutoRegistrationMemberRole member role
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "AutoRegistrationDepartment [id=" + id + ", role type=" + roleType + "]";
    }

    /**
     * Builder Class
     * @author s.maluya
     *
     */
    public static class Builder {
        private Long id;
        private String roleType;

        /**
         * Builder Constructor
         * @param id
         * @param roleType
         */
        public Builder(Long id, String roleType) {
            validateId(id);
            validateroleType(roleType);

            this.id = id;
            this.roleType = roleType;
        }

        public AutoRegistrationMemberRole build() {
            return new AutoRegistrationMemberRole(this);
        }

        /**
         * Validate AutoRegistrationMemberRole id if null or empty
         * @param id
         */
        private void validateId(Long id) {
            if (id == null || id == 0) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }

        /**
         * Validate AutoRegistrationMemberRole department if null or empty
         * @param roleType
         */
        private void validateroleType(String roleType) {
            if (roleType == null || roleType.isEmpty()) {
                throw new IllegalArgumentException("Member Role should not be empty");
            }
        }

    }

}
