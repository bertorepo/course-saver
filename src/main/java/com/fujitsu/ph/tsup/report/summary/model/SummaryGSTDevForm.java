//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : SummaryGSTDevForm
// Class Name   : SummaryGSTDevForm.java                                                                                                                                                                             
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 0.0.1   | 2021/04/20 | WS)G.Cabiling         | New Creation
// 0.0.2   | 2021/04/29 | WS)G.Cabiling         | Update  
//==================================================================================================    
package com.fujitsu.ph.tsup.report.summary.model;

import java.time.ZonedDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.sun.istack.NotNull;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Summary of JDU Standardization Training for Dev
//Class Name   : SummaryGSTDevDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | ----/--/-- | WS) -                 | Initial Version
//0.02    | ----/--/-- | WS) g.cabiling        | Updated
//0.03    | 2021/06/14 | WS) m.padaca          | Updated
//==================================================================================================


/**
 * <pre>
 * The model for G3CC standardization training for dev
 * </pre>
 * 
 * @version 0.03
 * @author g.cabiling
 * @author m.padaca
 */

public class SummaryGSTDevForm {
    /* Total Number of JDU Dev */
    private int TotalNoJDUDevValue;

    /* Total Number of JDU Dev Last Week */
    private int TotalNoJDUDevLastWeekValue;

    /* Total Number of Original Members */
    private int TotalNoExistingMemValue;

    /* Total Number of New Members */
    private int TotalNoNewMemValue;

    /* Total Number of JDU Dev who finished the Training */
    private int TotalNoJDUDevFinValue;

    /* Total Number of JDU Devs Last Week Who Finished the training */
    private int TotalNoJDUDevLastWkFinValue;

    /* Percentage Finished As of Today (Total Devs and also Total Courses */
    private double PercentageFinTodayValue;

    /* Percentage Finished As of Last Week (Total Devs and also Total Courses) */
    private double PercentageFinLastWkValue;
    
    /**
     *	Scheduled Start Date and Time
     */
    @NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime scheduledStartDateTime;
	
	/**
     *	Scheduled End Date and Time
     */
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime scheduledEndDateTime;


    public int getTotalNoJDUDevValue() {
        return TotalNoJDUDevValue;
    }

    public void setTotalNoJDUDevValue(int totalNoJDUDevValue) {
        this.TotalNoJDUDevValue = totalNoJDUDevValue;
    }

    public int getTotalNoJDUDevLastWeekValue() {
        return TotalNoJDUDevLastWeekValue;
    }

    public void setTotalNoJDUDevLastWeekValue(int totalNoJDUDevLastWeekValue) {
        this.TotalNoJDUDevLastWeekValue = totalNoJDUDevLastWeekValue;
    }

    public int getTotalNoExistingMemValue() {
        return TotalNoExistingMemValue;
    }

    public void setTotalNoExistingMemValue(int TotalNoExistingMemValue) {
        this.TotalNoExistingMemValue = TotalNoExistingMemValue;
    }

    public int getTotalNoNewMemValue() {
        return TotalNoNewMemValue;
    }

    public void setTotalNoNewMemValue(int totalNoNewMemValue) {
        this.TotalNoNewMemValue = totalNoNewMemValue;
    }

    public int getTotalNoJDUDevFinValue() {
        return TotalNoJDUDevFinValue;
    }

    public void setTotalNoJDUDevFinValue(int totalNoJDUDevFinValue) {
        this.TotalNoJDUDevFinValue = totalNoJDUDevFinValue;
    }

    public int getTotalNoJDUDevLastWkFinValue() {
        return TotalNoJDUDevLastWkFinValue;
    }

    public void setTotalNoJDUDevLastWkFinValue(int totalNoJDUDevLastWkFinValue) {
        this.TotalNoJDUDevLastWkFinValue = totalNoJDUDevLastWkFinValue;
    }

    public double getPercentageFinTodayValue() {
        return PercentageFinTodayValue;
    }

    public void setPercentageFinTodayValue(double percentageFinTodayValue) {
        this.PercentageFinTodayValue = percentageFinTodayValue;
    }

    public double getPercentageFinLastWkValue() {
        return PercentageFinLastWkValue;
    }

    public void setPercentageFinLastWkValue(double percentageFinLastWkValue) {
        this.PercentageFinLastWkValue = percentageFinLastWkValue;
    }
    public ZonedDateTime getScheduledStartDateTime() {
		return scheduledStartDateTime;
	}

	public void setScheduledStartDateTime(ZonedDateTime scheduledStartDateTime) {
		this.scheduledStartDateTime = scheduledStartDateTime;
	}

	public ZonedDateTime getScheduledEndDateTime() {
		return scheduledEndDateTime;
	}

	public void setScheduledEndDateTime(ZonedDateTime scheduledEndDateTime) {
		this.scheduledEndDateTime = scheduledEndDateTime;
	}


    @Override
    public String toString() {
        return "SummaryGSTDevForm[TotalNoJDUDevValue = " + TotalNoJDUDevValue
                + ", TotalNoJDUDevLastWeekValue =" + TotalNoJDUDevLastWeekValue + ", TotalNoExistingMemValue ="
                + TotalNoExistingMemValue + "," + ",TotalNoNewMemValue =" + TotalNoNewMemValue
                + ", TotalNoJDUDevFinValue =" + TotalNoJDUDevFinValue + ". TotalNoJDUDevLastWkFinValue ="
                + TotalNoJDUDevLastWkFinValue + "," + ", PercentageFinTodayValue =" + PercentageFinTodayValue
                + ", PercentageFinLastWkValue =" + PercentageFinLastWkValue + " ]";
    }


	
}
