package com.twu;

import java.util.Date;

public class SuperUser extends User{
    //add some function

    public void addSuperNews(HotNewsList list, String name){
        list.addToList(name,1);
    }

}
