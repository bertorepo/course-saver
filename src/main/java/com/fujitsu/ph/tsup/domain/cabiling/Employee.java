package com.fujitsu.ph.tsup.domain.cabiling;

public class Employee {
	private Long id;
    private String empNum;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    
    protected Employee() {
    }
    
    private Employee(Builder build) {
        this.id = build.id;
        this.empNum = build.empNum;
        this.firstName = build.firstName;
        this.lastName = build.lastName;
        this.emailAddress = build.emailAddress;
        this.userName = build.userName;
        
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmpNum() {
        return empNum;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public static class Builder {
        private Long id;
        private String empNum;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String userName;
        
        public Builder(Long id, String empnum, String fname, String lname, String emailadd, String uname) {
            validateId(id);
            validateEmpNum(empnum);
            validateFirstName(fname);
            validateLastName(lname);
            validateEmailAddress(emailadd);
            validateUserName(uname);
            
            this.id = id;
            this.empNum = empnum;
            this.firstName = fname;
            this.lastName = lname;
            this.emailAddress = emailadd;
            this.userName = uname;
        }
        
        public Builder(String empnum, String fname, String lname, String emailadd, String uname) {
            validateEmpNum(empnum);
            validateFirstName(fname);
            validateLastName(lname);
            validateEmailAddress(emailadd);
            validateUserName(uname);
            
            this.empNum = empnum;
            this.firstName = fname;
            this.lastName = lname;
            this.emailAddress = emailadd;
            this.userName = uname;
            
        }
        
        public Employee builder() {
            return new Employee(this);
        }
        
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }
        }
        
        private void validateEmpNum(String empnum) {
            if (empnum == null) {
                throw new IllegalArgumentException("Employee number cannot be null");
            } else if (empnum.length() > 10) {
                throw new IllegalArgumentException("Employee number exceeds the Limit");
            }
        }
        
        private void validateFirstName(String fname) {
            if (fname == null) {
                throw new IllegalArgumentException("First name cannot be null");
            } else if (fname.isEmpty()) {
                throw new IllegalArgumentException("First name cannot be Empty");
            } else if (fname.length() > 50) {
                throw new IllegalArgumentException("First Name exceeds the Limit");
            }
        }
        
        private void validateLastName(String lname) {
            if (lname== null) {
                throw new IllegalArgumentException("Last name cannot be null");
            } else if (lname.isEmpty()) {
                throw new IllegalArgumentException("Last name cannot be Empty");
            } else if (lname.length() > 50) {
                throw new IllegalArgumentException("Last name exceeds the Limit");
            }
        }
        
        private void validateEmailAddress(String emailadd) {
            if (emailadd == null) {
                throw new IllegalArgumentException("Email address cannot be null");
            } else if (emailadd.isEmpty()) {
                throw new IllegalArgumentException("Email address cannot be Empty");
            } else if (emailadd.length() > 50) {
                throw new IllegalArgumentException("Email address exceeds the Limit");
            }
        }
        
        private void validateUserName(String uname) {
            if (uname == null) {
                throw new IllegalArgumentException("Username cannot be null");
            } else if (uname.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be Empty");
            } else if (uname.length() > 50) {
                throw new IllegalArgumentException("Username exceeds the Limit");
            }
        }
    }

}
