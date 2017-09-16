package com.mridulahuja.groceryapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mridulahuja.groceryapp.R;
import com.mridulahuja.groceryapp.models.Grocery;
import com.mridulahuja.groceryapp.models.GroceryListImages;
import com.mridulahuja.groceryapp.tools.BitmapTools;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

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

        private ImageView minusBtn;
        private ImageView plusBtn;

        private TextView currentQuantity;

        public MyViewHolder(View view) {
            super(view);
            holderView = view;
            holderContext = view.getContext();
            txtItemName = (TextView) view.findViewById(R.id.txtItemName);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtUnit = (TextView) view.findViewById(R.id.txtUnit);
            imgItem = (ImageView) view.findViewById(R.id.imgItem);

            minusBtn = (ImageView) view.findViewById(R.id.btnMinus);
            plusBtn = (ImageView) view.findViewById(R.id.btnPlus);
            currentQuantity = (TextView) view.findViewById(R.id.txtQuantity);
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Grocery grocery = groceryList.get(position);
        holder.txtItemName.setText(grocery.getItemName());
        String price = "\u20B9" + grocery.getPrice();
        holder.txtPrice.setText(price);
        holder.txtUnit.setText(grocery.getUnit()+"          ");

        String imageName = grocery.getImgUrl().hashCode()+"";

        BitmapTools bitmapTools = new BitmapTools(holderContext);
        Bitmap img = bitmapTools.loadImageFromStorage(Environment.getExternalStorageDirectory() + "/GroceryApp/imgCache/", imageName);

        if(img!=null) {
            holder.imgItem.setImageBitmap(img);
        }
        else {
            GroceryListImages params = new GroceryListImages(imageName,
                    holder.imgItem,
                    grocery.getImgUrl());
            new LoadImage().execute(params);
        }

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                LinearLayout layout = (LinearLayout) holder.plusBtn.getParent();
                TextView txtQuantity = (TextView) layout.findViewById(R.id.txtQuantity);
                int quantity = Integer.parseInt(txtQuantity.getText().toString());

                quantity += 1;

                holder.currentQuantity.setText(quantity+"");
            }
        });


        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                LinearLayout layout = (LinearLayout) holder.minusBtn.getParent();
                TextView txtQuantity = (TextView) layout.findViewById(R.id.txtQuantity);
                int quantity = Integer.parseInt(txtQuantity.getText().toString());

                if(quantity>0)
                    quantity -= 1;

                holder.currentQuantity.setText(quantity+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }



    private class LoadImage extends AsyncTask<GroceryListImages, Void, Drawable> {

        private ImageView imgView;
        private String imgName;
        private String imgUrl;

        protected Drawable doInBackground(GroceryListImages... imgDetails) {
            imgUrl = imgDetails[0].getImgUrl();
            imgView = imgDetails[0].getImgView();
            imgName = imgDetails[0].getFileName();

            try {
                InputStream is = (InputStream) new URL(imgUrl).getContent();
                Drawable d = Drawable.createFromStream(is, "mrid");
                return d;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPreExecute() {

        }

        protected void onPostExecute(Drawable result) {

            imgView.setImageDrawable(result);

            Bitmap imgBitmap = ((BitmapDrawable)result).getBitmap();
            BitmapTools bitmapTools = new BitmapTools(holderContext);
            bitmapTools.saveToStorage(imgBitmap, imgUrl.hashCode()+"");

        }
    }




}
