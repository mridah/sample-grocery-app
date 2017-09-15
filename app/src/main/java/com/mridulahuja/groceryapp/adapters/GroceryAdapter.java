package com.mridulahuja.groceryapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mridulahuja.groceryapp.R;
import com.mridulahuja.groceryapp.models.Grocery;
import com.mridulahuja.groceryapp.models.GroceryListImages;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by mridul ahuja on 15/9/17.
 */

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.MyViewHolder> {

    private List<Grocery> groceryList;
    private Context holderContext;
    private View holderView;
    private Activity activity;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemName;
        private TextView txtPrice;
        private TextView txtUnit;
        private ImageView imgItem;

        public MyViewHolder(View view) {
            super(view);
            holderView = view;
            holderContext = view.getContext();
            txtItemName = (TextView) view.findViewById(R.id.txtItemName);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtUnit = (TextView) view.findViewById(R.id.txtUnit);
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
        }
    }


    public GroceryAdapter(List<Grocery> groceryList) {
        this.groceryList = groceryList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Grocery grocery = groceryList.get(position);
        holder.txtItemName.setText(grocery.getItemName());
        String price = "\u20B9" + grocery.getPrice();
        holder.txtPrice.setText(price);
        holder.txtUnit.setText(grocery.getUnit());

        GroceryListImages params = new GroceryListImages(holder.imgItem, grocery.getImgUrl());
        new LoadImage().execute(params);

    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }



    private class LoadImage extends AsyncTask<GroceryListImages, Void, Drawable> {

        private ImageView imgView;

        protected Drawable doInBackground(GroceryListImages... imgDetails) {
            String imgUrl = imgDetails[0].getImgUrl();
            imgView = imgDetails[0].getImgView();

            try {
                InputStream is = (InputStream) new URL(imgUrl).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");
                return d;
            } catch (Exception e) {
                System.out.println("Exception = " + e);
                return null;
            }
        }

        protected void onPreExecute() {

        }

        protected void onPostExecute(Drawable result) {
         /* Image loaded...*/

            imgView.setImageDrawable(result);

        }
    }




}
