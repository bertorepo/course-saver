package com.fujitsu.ph.tsup.employee.management.model;

public class EmployeeUpdateForm {
	private int ID;
	private String NUMBER;
	private String LAST_NAME;
	private String FIRST_NAME;
	private String EMAIL_ADDRESS;
	private String USERNAME;
	private String SUBMIT;
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getNUMBER() {
		return NUMBER;
	}
	public void setNUMBER(String NUMBER) {
		this.NUMBER = NUMBER;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String LAST_NAME) {
		this.LAST_NAME = LAST_NAME;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String FIRST_NAME) {
		this.FIRST_NAME = FIRST_NAME;
	}
	public String getEMAIL_ADDRESS() {
		return EMAIL_ADDRESS;
	}
	public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) {
		this.EMAIL_ADDRESS = EMAIL_ADDRESS;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String USERNAME) {
		this.USERNAME = USERNAME;
	}
	public String getSUBMIT() {
		return SUBMIT;
	}
	public void setSUBMIT(String SUBMIT) {
		this.SUBMIT = SUBMIT;
	}
	
	@Override
	public String toString() {
		return "EMPLOYEE_UPDATE [ID=" + ID + ", NUMBER="
				+ NUMBER + ", LAST_NAME=" + LAST_NAME + ", FIRST_NAME=" + FIRST_NAME
				+ ", EMAIL_ADDRESS=" + EMAIL_ADDRESS + ", USERNAME=" + USERNAME + ", SUBMIT="
				+ SUBMIT + "]";
	}
}
