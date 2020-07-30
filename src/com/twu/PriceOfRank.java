package com.twu;

public class PriceOfRank {
    int price;
    int rank;

    public PriceOfRank(int price, int rank) {
        this.price = price;
        this.rank = rank;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
