package com.mitali.devproblem.clientapi.client;

import com.mitali.devproblem.clientapi.annotation.SouthAfricanId;
import com.mitali.devproblem.clientapi.annotation.UniqueId;
import com.mitali.devproblem.clientapi.annotation.UniqueMobileNumber;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Client {

    @NotEmpty(message="First Name can not be empty")
    private String firstName;

    @NotEmpty(message="Last Name can not be empty")
    private String lastName;

    @UniqueMobileNumber(message = "This Mobile Number already exists")
    private String mobileNumber;

    @NotNull(message="Id can not be null")
    @UniqueId(message = "This Client Id already exists")
    @SouthAfricanId(message = "This is invalid South African id.Please enter correct format.")
    private String id;

    private String address;

    public Client(String firstName, String lastName, String mobileNumber, String id, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.id = id;
        this.address = address;
    }

    public Client() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
