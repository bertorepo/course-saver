//==================================================================================================																																											
// Project Name : Training Sign Up
// System Name  : CoursesConducted																																								
// Class Name   : CoursesConducted.java 																																											
//																																											
// <<Modification History>> 																																											
// Version | Date       | Updated By            | Content																																											
//---------+------------+-----------------------+--------------------------------------------------- 																																											
// 1.0.0   | 2021/02/22 | WS)J.Barbadillo       | New Creation		
// 1.0.1   | 2021/03/08 | WS)J.Barbadillo       | Updated
//==================================================================================================
package com.fujitsu.ph.tsup.course.model;

import java.time.ZonedDateTime;

/**
 * <pre>
 * The controller for CoursesConducted
 * </pre>
 * 
 * @author j.barbadillo
 * @version 1.0.0
 */

public class CoursesConducted {

	/**
     *  Id
     */
    private Long id;
    
    /**
     * Course Name
     */
    private String name;
    
    /**
     *	Scheduled Start Date and Time
     */
	private ZonedDateTime scheduledStartDateTime;
	
	/**
     *	Scheduled End Date and Time
     */
	private ZonedDateTime scheduledEndDateTime;
	
    /**
     *	Rescheduled Start Date and Time
     */
	private ZonedDateTime rescheduledStartDateTime;
	
	/**
     *	Rescheduled End Date and Time
     */
	private ZonedDateTime rescheduledEndDateTime;
	
	
    /**
     * Empty Constructor for ConductedCourse class
     */
    protected CoursesConducted() {

    }

    /** ConductedCourse Constructor
     * @param builder Builder
     */
    private CoursesConducted(Builder builder) {
    	 this.id = builder.id;
    	 this.name = builder.name;
         this.scheduledStartDateTime = builder.scheduledStartDateTime;
         this.scheduledEndDateTime = builder.scheduledEndDateTime;  
         this.rescheduledStartDateTime = builder.rescheduledStartDateTime;
         this.rescheduledEndDateTime = builder.rescheduledEndDateTime;
       
    }
    
    /**
     * Getter method for  Id
     * 
     * @return Id
     */ 
    public Long getId() {
		return id;
	}
    
    /**
     * Setter method for  Id
     * 
     * @param id  Id
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * Getter method for Course Name
     * 
     * @return Course name
     */
	public String getCourseName() {
		return name;
	}

	
	/**
     * Setter method for Course Name
     * 
     * @param name Course Name
     */
	public void setCourseName(String name) {
		this.name = name;
	}

	/**
     * Getter method for scheduledStartDateTime
     * 
     * @return scheduledStartDateTime
     */
	public ZonedDateTime getScheduledStartDateTime() {
		return scheduledStartDateTime;
	}
	
	/**
     * Setter method for scheduledStartDateTime
     * 
     * @param name scheduledStartDateTime
     */
	public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
		this.scheduledStartDateTime = scheduledStartDateTime;
	}
	
	
	/**
     * Getter method for scheduledEndDateTime
     * 
     * @return scheduledEndDateTime
     */
	public ZonedDateTime getScheduledEndDateTime() {
		return scheduledEndDateTime;
	}
		
	/**
     * Setter method for scheduledEndDateTime
     * 
     * @param name scheduledEndDateTime
     */
	public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
		this.scheduledEndDateTime = scheduledEndDateTime;
	}

	/**
     * Getter method for rescheduledStartDateTime
     * 
     * @return rescheduledStartDateTime
     */	
	public ZonedDateTime getRescheduledStartDateTime() {
		return rescheduledStartDateTime;
	}
	
	/**
     * Setter method for rescheduledStartDateTime
     * 
     * @param name rescheduledStartDateTime
     */
	public void setRescheduledStartDateTime(ZonedDateTime rescheduledStartDateTime) {
		this.rescheduledStartDateTime = rescheduledStartDateTime;
	}

	/**
     * Getter method for rescheduledEndDateTime
     * 
     * @return rescheduledEndDateTime
     */	
	public ZonedDateTime getRescheduledEndDateTime() {
		return rescheduledEndDateTime;
	}
	
	/**
     * Setter method for rescheduledEndDateTime
     * 
     * @param name rescheduledEndDateTime
     */
	public void setRescheduledEndDateTime(ZonedDateTime rescheduledEndDateTime) {
		this.rescheduledEndDateTime = rescheduledEndDateTime;
	}


	/** Builder Class
     * @author j.barbadillo
     *
     */
    public static class Builder {
    	
    	/**
         *  Id
         */
        private Long id;
        
        /**
         * Course Name
         */
        private String name;
        
        /**
         *	Scheduled Start Date and Time
         */
    	private ZonedDateTime scheduledStartDateTime;
    	
    	/**
         *	Scheduled End Date and Time
         */
    	private ZonedDateTime scheduledEndDateTime;
    	
        /**
         *	Rescheduled Start Date and Time
         */
    	private ZonedDateTime rescheduledStartDateTime;
    	
    	/**
         *	Rescheduled End Date and Time
         */
    	private ZonedDateTime rescheduledEndDateTime;
        
    	 /**
         * <pre>
         * Creates a new instance of Builder for courses conducted. 
         * It validates and sets the argument into the Builder instance variables. 
         * </pre>
         * 
         * @param id
         * @param name
         * @param scheduledStartDateTime
         * @param scheduledEndDateTime
         * @param rescheduledStartDateTime
         * @param rescheduledEndDateTime
         */
		public Builder(Long id, String name, ZonedDateTime scheduledStartDateTime, 
	             ZonedDateTime scheduledEndDateTime, ZonedDateTime rescheduledStartDateTime,
	             ZonedDateTime rescheduledEndDateTime) {
			 
			 validateId(id);
			 validateName(name);
			 validateScheduledStartDateTime(scheduledStartDateTime);
			 validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);
			 validateRescheduledStartDateTime(rescheduledStartDateTime);
			 validateRescheduledEndDateTime(rescheduledEndDateTime, rescheduledStartDateTime);
			 
	         this.id = id;
	         this.name = name;
	         this.scheduledStartDateTime = scheduledStartDateTime;
	         this.scheduledEndDateTime = scheduledEndDateTime;  
	         this.rescheduledStartDateTime = rescheduledStartDateTime;
	         this.rescheduledEndDateTime = rescheduledEndDateTime;
	       
	    }
		
		 /**
         * <pre>
         * Creates a new instance of Builder for courses conducted. 
         * It validates and sets the argument into the Builder instance variables. 
         * </pre>
         * 
         * @param id
         * @param name
         * @param scheduledStartDateTime
         * @param rescheduledStartDateTime
         * @param scheduledEndDateTime
         */
		public Builder(Long id, String name, ZonedDateTime scheduledStartDateTime, 
	             ZonedDateTime rescheduledStartDateTime, ZonedDateTime scheduledEndDateTime) {
			 
			 validateId(id);
			 validateName(name);
			 validateScheduledStartDateTime(scheduledStartDateTime);
			 validateRescheduledStartDateTime(rescheduledStartDateTime);
			 validateScheduledEndDateTime(scheduledEndDateTime, scheduledStartDateTime);
			 
	         this.id = id;
	         this.name = name;
	         this.scheduledStartDateTime = scheduledStartDateTime;
	         this.rescheduledStartDateTime = rescheduledStartDateTime;
	         this.scheduledEndDateTime = scheduledEndDateTime;
	         
	       
	    }
		
		public CoursesConducted build() {
			return new CoursesConducted(this);
		}
        
		/**
         * <pre>
         * Validate the name based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param name
         */
        private void validateName(String name ) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name should not be empty");
            }
        }      
			
		/**
         * <pre>
         * Validate the id based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param id
         */
        private void validateId(Long id) {
            if (id == null || id == 0L) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }      
        
        /**
         * <pre>
         * Validate the Scheduled Start Date and Time based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param scheduledStartDateTime
         */
        private void validateScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
            if (scheduledStartDateTime == null || String.valueOf(scheduledStartDateTime).isEmpty()) {
                throw new IllegalArgumentException("Scheduled Start Date and Time should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the Scheduled End Date and Time based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param scheduledEndDateTime
         * @param scheduledStartDateTime
         */
        private void validateScheduledEndDateTime(ZonedDateTime scheduledEndDateTime, 
                ZonedDateTime scheduledStartDateTime) {
            if (scheduledEndDateTime == null || String.valueOf(scheduledEndDateTime).isEmpty()) {
                throw new IllegalArgumentException("Scheduled End Date and Time should not be empty");
            } else if (scheduledStartDateTime.isAfter(scheduledEndDateTime)) {
                throw new IllegalArgumentException("Scheduled end date and time should be greater than or "
                        + "equal to the the scheduled start date and time");
                
            }
        }
        
        /**
         * <pre>
         * Validate the Rescheduled Start Date and Time based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param rescheduledStartDateTime
         */
        private void validateRescheduledStartDateTime(ZonedDateTime rescheduledStartDateTime) {
            if (rescheduledStartDateTime == null || String.valueOf(rescheduledStartDateTime).isEmpty()) {
                throw new IllegalArgumentException("Rescheduled Start Date and Time should not be empty");
            }
        }

        /**
         * <pre>
         * Validate the rescheduled End Date and Time based on the condition below. 
         * If it is invalid then throw an IllegalArgumentException with the corresponding message.
         * </pre>
         * 
         * @param rescheduledEndDateTime
         * @param rescheduledStartDateTime
         */
        private void validateRescheduledEndDateTime(ZonedDateTime rescheduledEndDateTime, 
                ZonedDateTime rescheduledStartDateTime) {
            if (rescheduledEndDateTime == null || String.valueOf(rescheduledEndDateTime).isEmpty()) {
                throw new IllegalArgumentException("Rescheduled End Date and Time should not be empty");
            } else if (rescheduledStartDateTime.isAfter(rescheduledEndDateTime)) {
                throw new IllegalArgumentException("recheduled end date and time should be greater than or "
                        + "equal to the the rescheduled start date and time");
                
            }
        }
    }

}
