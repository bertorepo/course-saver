package com.fujitsu.ph.tsup.domain.jimenez;

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
        
        public Builder(Long id, String empno, String fname, String lname, String email, String uname) {
            validateId(id);
            validateEmpNum(empno);
            validateFirstName(fname);
            validateLastName(lname);
            validateEmailAddress(email);
            validateUserName(uname);
            
            this.id = id;
            this.empNum = empno;
            this.firstName = fname;
            this.lastName = lname;
            this.emailAddress = email;
            this.userName = uname;
        }
        
        public Builder(String empno, String fname, String lname, String email, String uname) {
            validateEmpNum(empno);
            validateFirstName(fname);
            validateLastName(lname);
            validateEmailAddress(email);
            validateUserName(uname);
            
            this.empNum = empno;
            this.firstName = fname;
            this.lastName = lname;
            this.emailAddress = email;
            this.userName = uname;
            
        }
        
        public Employee builder() {
            return new Employee(this);
        }
        
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
        
        private void validateEmpNum(String empno) {
            if (empno == null) {
                throw new IllegalArgumentException("Employee Number should not be null");
            } else if (empno.length() > 10) {
                throw new IllegalArgumentException("Employee Number Length exceeds the Limit");
            }
        }
        
        private void validateFirstName(String fname) {
            if (fname == null) {
                throw new IllegalArgumentException("First Name should not be null");
            } else if (fname.isEmpty()) {
                throw new IllegalArgumentException("First Name should not be Empty");
            } else if (fname.length() > 50) {
                throw new IllegalArgumentException("First Name Length exceeds the Limit");
            }
        }
        
        private void validateLastName(String lname) {
            if (lname== null) {
                throw new IllegalArgumentException("Last Name should not be null");
            } else if (lname.isEmpty()) {
                throw new IllegalArgumentException("Last Name should not be Empty");
            } else if (lname.length() > 50) {
                throw new IllegalArgumentException("Last Name Length exceeds the Limit");
            }
        }
        
        private void validateEmailAddress(String email) {
            if (email == null) {
                throw new IllegalArgumentException("Email Address should not be null");
            } else if (email.isEmpty()) {
                throw new IllegalArgumentException("Email Address should not be Empty");
            } else if (email.length() > 50) {
                throw new IllegalArgumentException("Email Address Length exceeds the Limit");
            }
        }
        
        private void validateUserName(String uname) {
            if (uname == null) {
                throw new IllegalArgumentException("Username should not be null");
            } else if (uname.isEmpty()) {
                throw new IllegalArgumentException("Username should not be Empty");
            } else if (uname.length() > 50) {
                throw new IllegalArgumentException("Username Length exceeds the Limit");
            }
        }
    }
}
