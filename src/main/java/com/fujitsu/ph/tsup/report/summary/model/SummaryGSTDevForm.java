//==================================================================================================                                                                                                                                                                            
// Project Name : Training Sign Up
// System Name  : SummaryGSTDevForm
// Class Name   : SummaryGSTDevForm.java                                                                                                                                                                             
//                                                                                                                                                                          
// <<Modification History>>                                                                                                                                                                             
// Version | Date       | Updated By            | Content                                                                                                                                                                           
//---------+------------+-----------------------+---------------------------------------------------                                                                                                                                                                            
// 1.0.0   | 2021/04/20 | WS)G.Cabiling         | New Creation                                                                                                                                                                          
//==================================================================================================    
package com.fujitsu.ph.tsup.report.summary.model;

/**
 * <pre>
 * The model for G3CC standardization training for dev
 * </pre>
 * 
 * @author g.cabiling
 * @version 1.0.0
 */

public class SummaryGSTDevForm {
    /* Total Number of JDU Dev */
    private Long TotalNoJDUDevValue;

    /* Total Number of JDU Dev Last Week */
    private Long TotalNoJDUDevLastWeekValue;

    /* Total Number of Original Members */
    private Long TotalNoOrigMemValue;

    /* Total Number of New Members */
    private Long TotalNoNewMemValue;

    /* Total Number of JDU Dev who finished the Training */
    private Long TotalNoJDUDevFinValue;

    /* Total Number of JDU Devs Last Week Who Finished the training */
    private Long TotalNoJDUDevLastWkFinValue;

    /* Percentage Finished As of Today (Total Devs and also Total Courses */
    private Long PercentageFinTodayValue;

    /* Percentage Finished As of Last Week (Total Devs and also Total Courses) */
    private Long PercentageFinLastWkValue;

    public Long getTotalNoJDUDevValue() {
        return TotalNoJDUDevValue;
    }

    public void setTotalNoJDUDevValue(Long totalNoJDUDevValue) {
        this.TotalNoJDUDevValue = totalNoJDUDevValue;
    }

    public Long getTotalNoJDUDevLastWeekValue() {
        return TotalNoJDUDevLastWeekValue;
    }

    public void setTotalNoJDUDevLastWeekValue(Long totalNoJDUDevLastWeekValue) {
        this.TotalNoJDUDevLastWeekValue = totalNoJDUDevLastWeekValue;
    }

    public Long getTotalNoOrigMemValue() {
        return TotalNoOrigMemValue;
    }

    public void setTotalNoOrigMemValue(Long totalNoOrigMemValue) {
        this.TotalNoOrigMemValue = totalNoOrigMemValue;
    }

    public Long getTotalNoNewMemValue() {
        return TotalNoNewMemValue;
    }

    public void setTotalNoNewMemValue(Long totalNoNewMemValue) {
        this.TotalNoNewMemValue = totalNoNewMemValue;
    }

    public Long getTotalNoJDUDevFinValue() {
        return TotalNoJDUDevFinValue;
    }

    public void setTotalNoJDUDevFinValue(Long totalNoJDUDevFinValue) {
        this.TotalNoJDUDevFinValue = totalNoJDUDevFinValue;
    }

    public Long getTotalNoJDUDevLastWkFinValue() {
        return TotalNoJDUDevLastWkFinValue;
    }

    public void setTotalNoJDUDevLastWkFinValue(Long totalNoJDUDevLastWkFinValue) {
        this.TotalNoJDUDevLastWkFinValue = totalNoJDUDevLastWkFinValue;
    }

    public Long getPercentageFinTodayValue() {
        return PercentageFinTodayValue;
    }

    public void setPercentageFinTodayValue(Long percentageFinTodayValue) {
        this.PercentageFinTodayValue = percentageFinTodayValue;
    }

    public Long getPercentageFinLastWkValue() {
        return PercentageFinLastWkValue;
    }

    public void setPercentageFinLastWkValue(Long percentageFinLastWkValue) {
        this.PercentageFinLastWkValue = percentageFinLastWkValue;
    }

    @Override
    public String toString() {
        return "SummaryGSTDevForm[TotalNoJDUDevValue = " + TotalNoJDUDevValue
                + ", TotalNoJDUDevLastWeekValue =" + TotalNoJDUDevLastWeekValue + ", TotalNoOrigMemValue ="
                + TotalNoOrigMemValue + "," + ",TotalNoNewMemValue =" + TotalNoNewMemValue
                + ", TotalNoJDUDevFinValue =" + TotalNoJDUDevFinValue + ". TotalNoJDUDevLastWkFinValue ="
                + TotalNoJDUDevLastWkFinValue + "," + ", PercentageFinTodayValue =" + PercentageFinTodayValue
                + ", PercentageFinLastWkValue =" + PercentageFinLastWkValue + " ]";
    }

}
