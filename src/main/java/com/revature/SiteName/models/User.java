package com.revature.SiteName.models;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String region;
    private String hometown;
    private  

    public User() {

    }

    public User(String firstName, String lastName, String username, String password, String region, String hometown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.region = region;
        this.hometown = hometown;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", region='" + region + '\'' +
                ", hometown='" + hometown + '\'' +
                '}';
    }
}
