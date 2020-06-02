package com.fujitsu.ph.tsup.employee.management.model;

public class EMPLOYEE_CREATE {	
	private long ID;
	private String NUMBER;
	private String LAST_NAME;
	private String FIRST_NAME;
	private String EMAIL_ADDRESS;
	private String USERNAME;
	private String submit;

	
	public Long getID() {
		return ID;
	}
	public void setID(Long ID) {
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
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	@Override
	public String toString() {
		return "EMPLOYEE_CREATE [ID=" + ID + ", NUMBER="
				+ NUMBER + ", LAST_NAME=" + LAST_NAME + ", FIRST_NAME=" + FIRST_NAME
				+ ", EMAIL_ADDRESS=" + EMAIL_ADDRESS + ", USERNAME=" + USERNAME + ", submit="
				+ submit + "]";
	}
	
	
}
//Mark Lumontad
//Mark Lumontad
