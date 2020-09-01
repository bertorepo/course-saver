/*
 * Copyright (C) 2020 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.course.model;

/**
 * CourseForm class
 * 
 * @author c.lepiten (New Creation by: c.Lepiten)
 * @version Revision: 0.01 Date: 2020-08-28
 */
public class CourseForm {

    private Long id;
    private String name;
    private String detail;

    /**
     * Getter method for Course Id
     * 
     * @return Course Id
     */
    public Long getId() {

        return id;

    }

    /**
     * Setter method for Course Id
     * 
     * @param id Course Id
     */
    public void setId(Long id) {

        this.id = id;

    }

    /**
     * Getter method for Course Name
     * 
     * @return Course name
     */
    public String getName() {

        return name;

    }

    /**
     * Setter method for Course Name
     * 
     * @param name Course Name
     */
    public void setName(String name) {

        this.name = name;

    }

    /**
     * Getter Method for Course Detail
     * 
     * @return Course Detail
     */
    public String getDetail() {

        return detail;

    }

    /**
     * Setter Method for Course Detail
     * 
     * @param detail Course Detail
     */
    public void setDetail(String detail) {

        this.detail = detail;

    }

    @Override
    public String toString() {

        return "[ID " + id + " Name " + name + " Detail " + detail + "]";

    }
}
