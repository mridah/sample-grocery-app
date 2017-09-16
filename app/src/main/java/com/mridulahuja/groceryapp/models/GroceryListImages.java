package com.mridulahuja.groceryapp.models;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by mridul ahuja on 15/9/17.
 */

public class GroceryListImages {
    private String fileName;
    private ImageView imgView;
    private String imgUrl;

    public GroceryListImages(String fileName, ImageView imgView, String imgUrl){
        this.fileName = fileName;
        this.imgView = imgView;
        this.imgUrl = imgUrl;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public ImageView getImgView() {
        return this.imgView;
    }

}
