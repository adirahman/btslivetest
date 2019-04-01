package com.bts.btsapimodule;

public class SignUpModel {
    public String username;
    public String email;
    public String encrypted_password;
    public String phone;
    public String address;
    public String city;
    public String country;
    public String name;
    public String postcode;

    public SignUpModel(String username, String email, String encryted_password, String phone, String address, String city, String country, String name, String postcode) {
        this.username = username;
        this.email = email;
        this.encrypted_password = encryted_password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.country = country;
        this.name = name;
        this.postcode = postcode;
    }
}
