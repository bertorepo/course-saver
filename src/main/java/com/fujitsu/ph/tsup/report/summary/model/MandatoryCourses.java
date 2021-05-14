/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.report.summary.model;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * MandatoryCoursesForm class
 * 
 * @author z.deguia (New Creation by: z.deguia)
 * @version Revision: 0.01 Date: 2021-04-21
 */

public class MandatoryCourses {

    /**
     * Course ID
     */
    private Long id;

    /**
     * Course name
     */
    private String name;
    /**
     * List of Mandatory Courses
     */
    private Set<MandatoryCourses> mandatoryCoursesSet;
    
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

    public Set<MandatoryCourses> getMandatoryCoursesSet() {
        return mandatoryCoursesSet;
    }

    public void setMandatoryCoursesSet(Set<MandatoryCourses> mandatoryCoursesSet) {
        this.mandatoryCoursesSet = mandatoryCoursesSet;
    }

    @Override
    public String toString() {
        return "MandatoryCourses [id=" + id + ", name=" + name + ", mandatoryCoursesSet="
                + mandatoryCoursesSet + "]";
    }
    
}
