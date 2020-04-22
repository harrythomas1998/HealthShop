package com.example.assignment;

import com.google.firebase.database.Exclude;

public class User implements UserType{

    private String email;
    private String firstName;
    private String lastName;
    private String key;
    private Address address;


    public User(){

    }

    public User(String email, String firstName, String lastName, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Exclude
    public String getKey(){ return key; }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String login() {
        return null;
    }
}
