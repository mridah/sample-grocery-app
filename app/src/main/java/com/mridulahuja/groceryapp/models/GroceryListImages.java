package com.mridulahuja.groceryapp.models;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by mridul ahuja on 15/9/17.
 */

public class GroceryListImages {
    private ImageView imgView;
    private String imgUrl;

    public GroceryListImages(ImageView imgView, String imgUrl){
        this.imgView = imgView;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public ImageView getImgView() {
        return this.imgView;
    }

}
