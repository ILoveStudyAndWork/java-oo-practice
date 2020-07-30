package com.twu;

import java.util.*;

public class Main {
    static User currentUser = null;
    static UserList userList = new UserList();
    static HotNewsList hotNewsList = new HotNewsList();
    static Scanner in = new Scanner(System.in);
    //static Boolean isUserExist = null;
    //static Boolean isHotNewsExist = false;
    static int type = 0;

    public static void main(String[] args) {

        AskForIdentity();


    }

    //交互，询问用户身份
    private static void AskForIdentity() {
        System.out.println("请输入你的身份");
        System.out.println("1.普通用户  2.管理员 3.退出系统");
        int identity = in.nextInt();
        SpecifyTheUser(identity);
        String userName = in.next();
        currentUser = userList.login(userName);
        if (identity == 2) {
            AskForOptionSuper();
        } else {
            AskForOption();
        }
    }

    //由输入赋值用户类型
    private static void SpecifyTheUser(int identity) {
        if (identity == 1) {
            currentUser = new User();

        } else if (identity == 2) {
            currentUser = new SuperUser();

        } else {
            //程序结束
            System.exit(1);
        }
        System.out.println("请输入用户名");

    }

    //交互，询问用户操作
    private static void AskForOption() {
        System.out.println("请选择你的操作");
        System.out.println("1.查看热搜列表  2.给热搜投票 3.购买热搜 4.添加热搜 5.登出");
        int op = in.nextInt();
        switch (op) {
            case 1: {
                hotNewsList.showAll();
                break;
            }
            case 2: {
                AskForVote();
                break;
            }
            case 3: {
                AskForBuy();
                break;
            }
            case 4: {
                AskForAddNews();
                break;
            }
            case 5: {
                AskForIdentity();
                break;
            }
            default: {
                System.out.println("请重新输入有效数字");
                AskForOption();
            }
        }
        AskForOption();
    }

    //管理员的操作 交互，询问用户操作
    private static void AskForOptionSuper() {
        System.out.println("请选择你的操作");
        System.out.println("1.查看热搜列表  2.添加热搜  3.添加超级热搜 4.登出");
        int op = in.nextInt();
        switch (op) {
            case 1: {
                hotNewsList.showAll();
                break;
            }
            case 2: {
                AskForAddNews();
                break;
            }
            case 3: {
                AskForAddSuperNews();
                break;
            }
            case 4: {
                AskForIdentity();
                break;
            }

            default: {
                System.out.println("请重新输入有效数字");
                AskForOption();
            }
        }
        AskForOptionSuper();
    }

    public static void AskForVote() {
        System.out.println("请输入要投票的热搜描述(如果不存在则自动添加热搜)");
        String des = in.next();
        HotNews news = hotNewsList.getNews(des);

        System.out.format("请输入你要投的票数，不可超过您当前的剩余票数%d\n", currentUser.getVotesRemain());
        int voteThisRound = in.nextInt();
        if (voteThisRound <= currentUser.getVotesRemain()) {
            currentUser.vote(hotNewsList, news, voteThisRound);
        } else {
            System.out.println("您当前票数不足，投票失败");
            AskForOption();
        }

    }

    public static void AskForAddSuperNews() {
        System.out.println("请输入热搜的描述");
        String des = in.next();
        //当存在时不可添加，直接返回,不存在时添加并且更新排行版
        Boolean isContain = hotNewsList.addToList(des, 1);
        if (isContain) {
            System.out.println("热搜已存在，添加失败");
        } else {
            System.out.println("添加成功");
        }
    }

    //超级热搜
    public static void AskForAddNews() {
        System.out.println("请输入热搜的描述");
        String des = in.next();
        //当存在时不可添加，直接返回,不存在时添加并且更新排行版
        Boolean isContain = hotNewsList.addToList(des, 0);
        if (isContain) {
            System.out.println("热搜已存在，添加失败");
        } else {
            System.out.println("添加成功");
        }
    }

    public static void AskForBuy() {
        System.out.println("请输入需要购买的热搜的描述");
        String des = in.next();
        HotNews news = hotNewsList.getNews(des);
        if (hotNewsList.isHotNewsExist()) {
            QueryAndBuy(des);
        } else {
            System.out.println("热搜不存在，购买失败");
            AskForOption();
        }
    }

    private static void QueryAndBuy(String des) {
        System.out.println("请输入需要购买的排名");
        int rank = in.nextInt();
        System.out.println("请输入购买金额，必须大于当前拍卖价：" + hotNewsList.QueryPrice(rank) + "才能购买成功");
        int money = in.nextInt();
        if (money > hotNewsList.QueryPrice(rank)) {
            currentUser.buyHotNews(hotNewsList, money, des, rank);
            System.out.println("购买成功");
        } else {
            System.out.println("钱不够，购买失败");
        }
    }

}
