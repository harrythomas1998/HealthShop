package com.example.assignment;

public class Address {

    String line1;
    String town;
    String county;
    String eircode;

    public Address(){}


    public Address(String line1, String town, String county, String eircode) {
        this.line1 = line1;
        this.town = town;
        this.county = county;
        this.eircode = eircode;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }
}
