package com.twu;

import java.util.Date;

public class HotNews {
    int rank = 0;
    String description;
    int heat = 0;
    int currentPrice = 0;
    int priority = 0;

    Date createTime = new Date();


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public HotNews(String description){
//        this.description = description;
//    }
    public HotNews(String description,Date createTime){
        this.description = description;
        this.createTime = createTime;
    }

    public void updateHeat(int number){
        heat += number;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeat() {
        return heat;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}
