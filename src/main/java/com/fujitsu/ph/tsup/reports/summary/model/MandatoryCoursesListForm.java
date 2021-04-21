/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.reports.summary.model;

/**
 * MandatoryCoursesListForm class
 * 
 * @author z.deguia (New Creation by: z.deguia)
 * @version Revision: 0.01 Date: 2021-04-21
 */

public class MandatoryCoursesListForm {
    
    /**
     * Total Number of JDU Members  
     */
    private Long totalNoOfJDUMem;
    
    /**
     * Total Number of JDU Members Who Finished the training
     */
    private Long totalNoOfJDUMemFin;
    
    /**
     * Total Number of JDU Members Last Week Who Finished the training
     */
    private Long totalNoOfJDUMemFinLastWk;
    
    /**
     * Percentage Finished As of Today  
     */
    private Double percentFinToday;
    
    /**
     * Percentage Finished as of Last Week  
     */
    private Double percentFinLastWk;

    public Long getTotalNoOfJDUMem() {
        return totalNoOfJDUMem;
    }

    public void setTotalNoOfJDUMem(Long totalNoOfJDUMem) {
        this.totalNoOfJDUMem = totalNoOfJDUMem;
    }

    public Long getTotalNoOfJDUMemFin() {
        return totalNoOfJDUMemFin;
    }

    public void setTotalNoOfJDUMemFin(Long totalNoOfJDUMemFin) {
        this.totalNoOfJDUMemFin = totalNoOfJDUMemFin;
    }

    public Long getTotalNoOfJDUMemFinLastWk() {
        return totalNoOfJDUMemFinLastWk;
    }

    public void setTotalNoOfJDUMemFinLastWk(Long totalNoOfJDUMemFinLastWk) {
        this.totalNoOfJDUMemFinLastWk = totalNoOfJDUMemFinLastWk;
    }

    public Double getPercentFinToday() {
        return percentFinToday;
    }

    public void setPercentFinToday(Double percentFinToday) {
        this.percentFinToday = percentFinToday;
    }

    public Double getPercentFinLastWk() {
        return percentFinLastWk;
    }

    public void setPercentFinLastWk(Double percentFinLastWk) {
        this.percentFinLastWk = percentFinLastWk;
    }

    @Override
    public String toString() {
        return "MandatoryCoursesListForm [totalNoOfJDUMem=" + totalNoOfJDUMem + ", totalNoOfJDUMemFin="
                + totalNoOfJDUMemFin + ", totalNoOfJDUMemFinLastWk=" + totalNoOfJDUMemFinLastWk
                + ", percentFinToday=" + percentFinToday + ", percentFinLastWk=" + percentFinLastWk + "]";
    }

}
