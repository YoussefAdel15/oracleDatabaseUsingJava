package com.sci.models;

public class Customer {
    private int customerID;
    private String lastName;
    private String phoneNumber;
    private String email;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Customers{" +
                "customer_id=" + customerID +
                ", last_name='" + lastName + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
