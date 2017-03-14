package com.verne.geocode.model;


public class Location {
    private String Address;

    public Location(String address) {
        Address = address;
    }

    public Location(){
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
