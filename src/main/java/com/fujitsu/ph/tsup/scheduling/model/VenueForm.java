package com.fujitsu.ph.tsup.scheduling.model;

//=======================================================
//$Id: PR02$
//Project Name: Training Sign Up
//Class Name: VenueForm.java
//
//<<Modification History>>
//Version | Date       | Updated by      | Content
//--------+------------+-----------------+---------------
//0.01    | 06/22/2020 | WS) JC. Jimenez | New Creation
//0.02    | 06/07/2021 | WS) R. Gemparo  | Fixed toString return
//
//=======================================================

/**
 * <pre>
 * It is a JavaBean for VenueForm.
 * <pre>
 * @version 0.01
 * @author jc.jimenez
 *
 */

public class VenueForm {
    
    /**
     * Venue Id
     */
    private Long id;
    
    /**
     * Venue Name
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
        return "VenueForm [id = " + id + ", name = " + name + "]";
    }
}
