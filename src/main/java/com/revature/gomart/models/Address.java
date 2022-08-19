package com.revature.gomart.models;

public class Address {
    private String id;
    private String fullName;
    private String street1;
    private String street2;
    private String city;
    private String region;
    private String user_id;

    public Address() {

    }

    public Address(String id, String name, String street1, String street2, String city, String region, String user_id) {
        this.id = id;
        this.fullName = name;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.region = region;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "fullName='" + fullName + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
