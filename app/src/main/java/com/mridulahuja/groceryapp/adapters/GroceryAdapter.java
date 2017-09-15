package com.mridulahuja.groceryapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mridulahuja.groceryapp.R;
import com.mridulahuja.groceryapp.models.Grocery;

import java.util.List;

/**
 * Created by mridul ahuja on 15/9/17.
 */

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.MyViewHolder> {

    private List<Grocery> groceryList;
    private Context holderContext;
    private View holderView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemName;
        private TextView txtPrice;
        private TextView txtUnit;
        private ImageView imgItem;

        public MyViewHolder(View view) {
            super(view);
            holderView = view;
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
        Grocery grocery = groceryList.get(position);
        holder.txtItemName.setText(grocery.getItemName());
        String price = "\u20B9" + grocery.getPrice();
        holder.txtPrice.setText(price);
        holder.txtUnit.setText(grocery.getUnit());
    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }






}
