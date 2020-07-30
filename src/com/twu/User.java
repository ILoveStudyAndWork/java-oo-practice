package com.twu;

public class User {
    String userName;
    int isOnline = 1 ;//1 means online 0 means offline
    int votesRemain = 10 ;

    public User(){

    }

    public User(String name){
        this.userName = name;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getVotesRemain() {
        return votesRemain;
    }

    public void setVotesRemain(int votesRemain) {
        this.votesRemain = votesRemain;
    }

    //option 1.view the list 2.vote 3.addHotView 4.Buy 5.exit
    public void viewTheList (HotNewsList list){

    }

    public void vote (HotNewsList list,HotNews news, int number){
        votesRemain -= number;
        news.updateHeat(number);
        list.updateRank();
    }

//    public void addHotNews (HotNewsList list, String name){
//
//    }

    public void buyHotNews (HotNewsList list, int money,String description,int rank){
        list.addBuyNews(description,money,rank);
    }
}
