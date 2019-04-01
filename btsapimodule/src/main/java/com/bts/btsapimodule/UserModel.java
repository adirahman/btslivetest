package com.bts.btsapimodule;

public class UserModel extends SignUpModel {

    public String password;

    public UserModel(String username, String email, String encryted_password, String phone, String address, String city, String country, String name, String postcode) {
        super(username, email, encryted_password, phone, address, city, country, name, postcode);
    }
}
