package com.example.last_try.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{
    public int id;
    public String username;
    public String phone;
    public String email;
    public String password;
    public String Role;
    public String approved;

    public User(){}
    public User(@JsonProperty("id")int id, @JsonProperty("name")String username, @JsonProperty("phone")String phone
    , @JsonProperty("email")String email, @JsonProperty("password")String password, @JsonProperty("role")String role,
                @JsonProperty("approved")String approved)
    {
        this.id=id;
        this.username=username;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.Role=role;
        this.approved=approved;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}