package com.afrobaskets.App.bean;

/**
 * Created by asdfgh on 10/13/2017.
 */

public class CartBean {
    private String name;
    private Integer weight;
    private Integer price;

    public CartBean(){

    }

    public CartBean(String name, Integer weight, Integer price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }



}
