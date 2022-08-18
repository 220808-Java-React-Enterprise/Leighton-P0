package com.revature.gomart.models;

public class Customer extends User {
    private String region;
    private String hometown;

    public Customer(){}

    public Customer(String id, String name, String username, String password, String email, String region, String hometown) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.region = region;
        this.hometown = hometown;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                "region='" + region + '\'' +
                ", hometown='" + hometown + '\'' +
                '}';
    }
}
