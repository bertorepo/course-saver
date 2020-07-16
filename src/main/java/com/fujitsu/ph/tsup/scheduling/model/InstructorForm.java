package com.fujitsu.ph.tsup.scheduling.model;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: CourseForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) JC. Jimenez | New Creation
//
//=======================================================

/**
 * <pre>
 * It is a JavaBean for InstructorForm.
 * <pre>
 * @version 0.01
 * @author jc.jimenez
 *
 */

public class InstructorForm {
    
    /**
     * Instructor Id
     */
    private Long id;
    
    /**
     * Instructor Name (LASTNAME, FIRSTNAME)
     */
    private String name;
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "InstructorForm [id = " + id + ", name = " + name + "]";
    }
}
