package com.mridulahuja.groceryapp.models;

/**
 * Created by mridul ahuja on 15/9/17.
 */

public class Grocery {
    private String itemName;
    private Float price;
    private String unit;
    private String imgUrl;

    public Grocery(String itemName,
                   Float price,
                   String unit,
                   String imgUrl) {
        this.itemName = itemName;
        this.price = price;
        this.unit = unit;
        this.imgUrl = imgUrl;
    }

    /*
    * setters
    */

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /*
    * getters
    */

    public String getItemName() {
        return this.itemName;
    }

    public Float getPrice() {
        return this.price;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }


}
