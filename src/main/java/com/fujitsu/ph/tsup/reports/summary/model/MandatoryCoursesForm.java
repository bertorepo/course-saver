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

public class MandatoryCoursesForm {
    
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

    @Override
    public String toString() {
        return "MandatoryCoursesListForm [totalNoOfJDUMem=" + totalNoOfJDUMem + ", totalNoOfJDUMemFin="
                + totalNoOfJDUMemFin + ", totalNoOfJDUMemFinLastWk=" + totalNoOfJDUMemFinLastWk + "]";
    }
    
    
}
