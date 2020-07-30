package com.twu;

import java.util.Date;

public class SuperNews extends HotNews{
    //int priority = 1;

    public SuperNews(String description, Date date){
        super(description,date);
    }

    public void updateHeat(int number){
        super.heat += number * 2;
    }

}
