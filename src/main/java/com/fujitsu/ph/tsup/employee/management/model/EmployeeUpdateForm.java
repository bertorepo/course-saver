package com.fujitsu.ph.tsup.employee.management.model;

/*Iwarat, Jhon Harvey A*/
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class EmployeeUpdateForm {

    private Long id;
    @PositiveOrZero
    @Pattern(regexp = "[^.]+", message = "The dot character is not allowed")
    @NotBlank(message = "Employee number must not be empty")
    private String number;
    @Pattern(regexp = "[a-zA-Z]+", message = "must not contain special characters")
    @NotBlank(message = "Last name must not be empty")
    private String lastName;
    @Pattern(regexp = "[a-zA-Z]+", message = "must not contain special characters")
    @NotBlank(message = "first name must not be empty")
    private String firstName;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Must input a valid email")
    private String emailAddress;
    @Size(min = 8, message = "Username size must not less than 8")
    @NotBlank(message = "Username must not be empty")
    private String username;
    private String submit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    @Override
    public String toString() {
        return "EMPLOYEE_UPDATE [id=" + id + ", number=" + number + ", lastName=" + lastName + ", firstName="
                + firstName + ", emailAddress=" + emailAddress + ", username=" + username + ", submit=" + submit + "]";
    }
}
