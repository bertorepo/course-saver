/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.model;

/**
 * MandatoryCoursesForm class
 * 
 * @author z.deguia (New Creation by: z.deguia)
 * @version Revision: 0.01 Date: 2021-04-21
 */

public class MandatoryCourses {

    /**
     * Total Number of JDU Members
     */
    private Long id;

    /**
     * Total Number of JDU Members Who Finished the training
     */
    private String name;

    /**
     * Empty Constructor for MandatoryCoursesForm class
     */
    protected MandatoryCourses() {

    }

    /**
     * MandatoryCoursesForm Constructor
     * @param builder Builder
     */
    private MandatoryCourses(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Builder Class
     * @author z.deguia
     *
     */
    public static class Builder {

        private Long id;
        private String name;
        
        /**
         * Builder Constructor
         * @param id
         * @param name
         */
        public Builder(Long id, String name) {

            validateId(id);
            validateName(name);

            this.id = id;
            this.name = name;

        }
      

        /**
         * Builder Constructor
         * @param name
         * @param detail
         */

        public MandatoryCourses build() {

            return new MandatoryCourses(this);

        }

        /**
         * Validate id if null or 0
         * @param id
         */
        private void validateId(Long id) {
  
            if (id == null || id == 0L) {
                
                throw new IllegalArgumentException("Id should not be empty");
                
            }

        }
        
        /**
         * Validate name if null or empty
         * @param name
         */
        private void validateName(String name) {
            
            if (name == null || name.isEmpty()) {
                
                throw new IllegalArgumentException("Name should not be empty");
                
            }

        }
        
        

    }

}
