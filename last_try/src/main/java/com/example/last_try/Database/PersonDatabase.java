package com.example.last_try.Database;


import com.example.last_try.Models.User;

import java.util.ArrayList;

public interface PersonDatabase
{
    public void create(User new_person);
    public ArrayList<User> get_all();
    public User read_user(String uname, String upassword);
    public User get_by_id(int UserID);

}
