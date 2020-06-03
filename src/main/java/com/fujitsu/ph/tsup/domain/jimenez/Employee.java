package com.fujitsu.ph.tsup.domain.jimenez;

public class Employee {
    private Long id;
    private Long empNum;
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
    
    public Long getEmpNum() {
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
        private Long empNum;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String userName;
        
        public Builder(Long id, Long empno, String fname, String lname, String email, String uname) {
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
        
        public Employee builder() {
            return new Employee(this);
        }
        
        private void validateId(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("ID should not be null");
            }
        }
        
        private void validateEmpNum(Long empno) {
            if (empno == null) {
                throw new IllegalArgumentException("Employee Number should not be null");
            }
        }
        
        private void validateFirstName(String fname) {
            if ((fname == null)||(fname.isEmpty())) {
                throw new IllegalArgumentException("First Name should not be null nor Empty");
            }
        }
        
        private void validateLastName(String lname) {
            if ((lname == null)||(lname.isEmpty())) {
                throw new IllegalArgumentException("Last Name should not be null nor Empty");
            }
        }
        
        private void validateEmailAddress(String email) {
            if ((email == null)||(email.isEmpty())) {
                throw new IllegalArgumentException("Email Address should not be null nor Empty");
            }
        }
        
        private void validateUserName(String uname) {
            if ((uname == null)||(uname.isEmpty())) {
                throw new IllegalArgumentException("Username should not be null nor Empty");
            }
        }
    }
}
