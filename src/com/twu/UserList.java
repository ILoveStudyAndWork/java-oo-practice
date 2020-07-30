package com.twu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserList {
    List<User> allUser = new ArrayList<>();

    public User login(String name){
        User user;
        Iterator<User> ui = allUser.iterator();
        while (ui.hasNext()){
            user = ui.next();
            if(user.getUserName().equals(name)){
                return user;
            }
        }
        //找不到的话就新建
        User newUser = new User(name);
        allUser.add(newUser);
        return newUser;
    }




}
