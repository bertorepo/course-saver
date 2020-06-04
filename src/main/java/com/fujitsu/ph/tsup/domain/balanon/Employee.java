package com.fujitsu.ph.tsup.domain.balanon;

import java.time.ZonedDateTime;

public class Employee {
    
    private Long Id;
    private String EmployeeNum;
    private String LastName;
    private String EmailAdd;
    private String UserName;
    
    private Employee(Builder builder) {
        this.Id = builder.Id;
        this.EmployeeNum = builder.EmployeeNum;
        this.LastName = builder.LastName;
        this.EmailAdd = builder.EmailAdd;
        this.UserName = builder.UserName;
    }
    
    public Long getId() {
        return Id;
    }
    public String getEmployeeNum() {
        return EmployeeNum;
    }
    public String getLastName() {
        return LastName;
    }
    public String getEmailAdd() {
        return EmailAdd;
    }
    public String getUserName() {
        return UserName;
    }
    
    public static class Builder {
        private Long Id;
        private String EmployeeNum;
        private String LastName;
        private String EmailAdd;
        private String UserName;
        
        public Builder(String EmployeeNum, String LastName, String EmailAdd, String UserName) {
            validateEmployeeNum(EmployeeNum);
            validateLastName(LastName);
            validateEmailAdd(EmailAdd);
            validateUserName(UserName);
            
            this.EmployeeNum = EmployeeNum;
            this.LastName = LastName;
            this.EmailAdd = EmailAdd;
            this.UserName = UserName;
        }
        
        private void validateEmployeeNum(String EmployeeNum) {
            if (EmployeeNum == null || EmployeeNum.isEmpty() || EmployeeNum.length() > 10) {
                throw new IllegalArgumentException("Provide the Emplyoee Number properly");
            }
        }
        private void validateLastName(String LastName) {
            if (LastName == null || LastName.isEmpty() || LastName.length() > 50) {
                throw new IllegalArgumentException("Provide the Last Name properly");
            }
        }
        private void validateEmailAdd(String EmailAdd) {
            if (EmailAdd == null || EmailAdd.isEmpty() || EmailAdd.length() > 50) {
                throw new IllegalArgumentException("Provide the EmailAdd properly");
            }
        }
        private void validateUserName(String UserName) {
            if (UserName == null || UserName.isEmpty() || UserName.length() > 50) {
                throw new IllegalArgumentException("Provide the UserNameer properly");
            }
        }
        
        
        
        
        
        
    }
}
