package com.example.sendy.recyclerview;

/**
 * Created by Sendy on 05-Jul-16.
 */
public class Itemlist {

    public String name,symbol;
    public String price;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public Itemlist()
    {
        this.name = name;
        this.price = price;
    }

    public Itemlist(String name, String price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}
