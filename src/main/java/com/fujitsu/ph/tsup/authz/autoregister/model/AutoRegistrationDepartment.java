/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.authz.autoregister.model;

/**
 * AutoRegistrationDepartment class
 * 
 * @author s.maluya (New Creation by: s.maluya)
 * @version 0.01
 */
public class AutoRegistrationDepartment {
    private Long id;
    private String department;

    protected AutoRegistrationDepartment() {

    }

    /**
     * AutoRegistrationDepartment Constructor
     * @param Builder builder
     */
    private AutoRegistrationDepartment(Builder builder) {
        this.id = builder.id;
        this.department = builder.department;
    }

    /**
     * Getter method for AutoRegistrationDepartment Id
     * 
     * @return AutoRegistrationDepartment id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for AutoRegistrationDepartment Id
     * 
     * @param AutoRegistrationDepartment id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter method for AutoRegistrationDepartment Department
     * 
     * @return AutoRegistrationDepartment department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Setter method for AutoRegistrationDepartment Department
     * 
     * @param AutoRegistrationDepartment department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "AutoRegistrationDepartment [id=" + id + ", department=" + department + "]";
    }

    /**
     * Builder Class
     * @author s.maluya
     *
     */
    public static class Builder {
        private Long id;
        private String department;

        /**
         * Builder Constructor
         * @param id
         * @param department
         */
        public Builder(Long id, String department) {
            validateId(id);
            validateDepartment(department);

            this.id = id;
            this.department = department;
        }

        public AutoRegistrationDepartment build() {
            return new AutoRegistrationDepartment(this);
        }

        /**
         * Validate AutoRegistrationDepartment id if null or empty
         * @param id
         */
        private void validateId(Long id) {
            if (id == null || id == 0) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }

        /**
         * Validate AutoRegistrationDepartment department if null or empty
         * @param department
         */
        private void validateDepartment(String department) {
            if (department == null || department.isEmpty()) {
                throw new IllegalArgumentException("Department should not be empty");
            }
        }

    }

}
