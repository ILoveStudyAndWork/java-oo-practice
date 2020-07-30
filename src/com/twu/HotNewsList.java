package com.twu;

import java.util.*;
public class HotNewsList {

    Boolean isContain = false;
    //后续改成private的
    List<HotNews> list = new ArrayList<>();
    int currentRank = 0;
    int type = 0;//0是普通热搜，1是超级热搜
    Map<Integer,Integer> price = new HashMap<>();
    //设置comparetor用来做比较
    Comparator<HotNews> prio_Vote_timeComparator = Comparator.comparing(HotNews::getPriority)
                                                            .thenComparing(HotNews::getHeat)
                                                            .thenComparing(HotNews::getCreateTime);
    Comparator<HotNews> rankComparator = Comparator.comparing(HotNews::getRank);
    //每次有新加入的热搜都调用此方法，不允许其他的直接访问
    public boolean addToList (String des,int type){
        this.type = type;
        getNews(des);
        if (!isContain){
            updateRank();
        }
        return isContain;
    }
    public int QueryPrice(int rank){
        list.stream().filter(news -> news.getRank()==rank).forEach(news -> price.put(news.rank,news.currentPrice));
        return price.get(rank);
    }
    //when someone buy ,it will check if the money is enough then update(replace)
    public void addBuyNews(String name, int money, int rank){
        //更新money，更新排名，将原本的替换掉
        HotNews news = getNews(name);
        news.setCurrentPrice(money);
        news.setRank(rank);
        price.put(rank,money);
        //直接替换掉原来排名第rank的热搜,不需要重新排名，先删了再计算自己的位置
        list.remove(news);
        list.set(rank-1,news);

        updateRank();
    }
    //when new hotNews update,this will be call
    public void updateRank (){
        //1.先把买的拿出来，2.剩下的元素按热度等排序 3.排序完毕之后加入刚刚的元素 4.再赋值排名
        //1.先把买的拿出来
        int size = list.size();
        List<Integer> rankList = new ArrayList<>(price.keySet());
        Collections.sort(rankList);
        //从买的列表rankList中一一找出并加入tempForBuyNews
        List<HotNews> tempForBuyNews = new ArrayList<>();
        Iterator<Integer> rankI = rankList.iterator();
        HotNews reRank;
        while (rankI.hasNext()){
            //取出该热搜的rank,计算之后的index
            int r = rankI.next();
            //取出对应的热搜
            reRank = list.get(r-1);
            reRank.setRank(r);
            tempForBuyNews.add(reRank);
        }
        list.removeAll(tempForBuyNews);
        //list.stream().forEach(o->System.out.println("排序之前--"+o.description+":"+o.getRank()));

        //2.剩下的元素根据优先级，票数，创建时间排序
        Collections.sort(list,prio_Vote_timeComparator);
        Collections.reverse(list);
        //list.stream().forEach(n->System.out.println("用优先级热度时间排序--"+n.description+":"+n.getRank()));

        //3.排序完毕之后加入刚刚的元素,元素的位置等于它的rank（拿出来的时候就赋值了）
        tempForBuyNews.stream().forEach(news->list.add(news.getRank()-1,news));

        //4.重新赋值rank
        Iterator<HotNews> hi = list.iterator();
        HotNews n;
        int r = 1;
        while (hi.hasNext()){
            n = hi.next();
            n.setRank(r);
            r++;
        }

        Collections.sort(list,rankComparator);
        //list.stream().forEach(nx->System.out.println("用rank排序---"+nx.description+":"+nx.getRank()));

    }

    public void updateVotes (int number){
        updateRank();
    }


    public List<HotNews> getList (){
        return list;
    }

    public  void showAll(){

        System.out.printf("%10s %35s %10s \n","Rank","Description","Heat");
        list.stream().forEach(news->{System.out.format("%10d %35s %10d\n",
                news.getRank(), news.getDescription(), news.getHeat());});
    }

    //查询当前列表中是否存在news，存在则返回已存在的对象，否则返回新建的news，并加入排行榜
    public  HotNews getNews(String description){

        isContain = false;
        Iterator<HotNews> newsIter = getList().iterator();
        HotNews news;
        while (newsIter.hasNext()){
            news = newsIter.next();
            if (news.getDescription().equals(description)){
                isContain = true;
                return news;
            }
        }
        //没找到则新建
        HotNews newNews;
        if (type==0){
            newNews = new HotNews(description,new Date());
        }else {
            newNews = new SuperNews(description,new Date());
        }

        getList().add(newNews);
        return newNews;

    }
    public boolean isHotNewsExist(){
        return isContain;
    }


}
