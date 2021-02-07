/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.role.model;

/**
 * RoleType class
 * 
 * @author p.cui (New Creation by: p.cui)
 * @version Revision: 0.01 Date: 2021-02-05
 */
public class RoleType {

    private Long id;
    private String roleType;
    private String description;

    /**
     * Empty Constructor for RoleType class
     */
    protected RoleType() {

    }

    /** RoleType Constructor
     * @param builder Builder
     */
    private RoleType(Builder builder) {

        this.id = builder.id;
        this.roleType = builder.roleType;
        this.description = builder.description;
    }

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
     * Getter method for RoleType Type
     * 
     * @return RoleType Type
     */
    public String getRoleType() {

        return roleType;

    }

    /**
     * Setter method for RoleType Type
     * 
     * @param name RoleType Type
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
     * @param detail RoleType Description
     */
    public void setDescription(String description) {

        this.description = description;

    }

    @Override
    public String toString() {
        return "RoleType [ID + " +id +"Type " + roleType +" Description " +description;
    }

    /** Builder Class
     * @author p.cui
     *
     */
    public static class Builder {

        private Long id;
        private String roleType;
        private String description;

        /** Builder Constructor
         * @param roleType
         */
        public Builder(String roleType) {

            validateRoleType(roleType);

            this.roleType = roleType;

        }

        /** Builder Constructor
         * @param id
         * @param roleType
         */
        public Builder(Long id, String roleType) {

            validateId(id);
            validateRoleType(roleType);

            this.id = id;
            this.roleType = roleType;

        }
        
        /** Builder Constructor
         * @param roleType
         * @param description
         */
        
        public Builder(String roleType, String description) {
        	
        	validateRoleType(roleType);
        	validateDescription(description);
        	
        	this.roleType = roleType;
        	this.description = description;
        	
        }
 
        /** Builder Constructor
         * @param description
         * @return
         */
        public Builder description(String description) {

            validateDescription(description);
            this.description = description;

            return this;

        }

        public RoleType build() {

            return new RoleType(this);

        }

        /** Validate roletype type if null or empty
         * @param RoleType Type
         */
        private void validateRoleType(String roleType) {

            if (roleType.equals(null) || roleType.isEmpty()) {

                throw new IllegalArgumentException(
                        "Role Type should not be empty");

            }

        }

        /**
         * Validate RoleType id if null or 0
         * @param id
         */
        private void validateId(Long id) {

            if (id == null || id == 0) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }

        /**
         * Validate RoleType Description if null or empty
         * @param description RoleType
         */
        private void validateDescription(String description) {

            if (description.equals(null) || description.isEmpty()) {

                throw new IllegalArgumentException("Description should not be empty");

            }

        }

    }

}
