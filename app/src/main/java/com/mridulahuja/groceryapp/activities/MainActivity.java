package com.mridulahuja.groceryapp.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mridulahuja.groceryapp.R;
import com.mridulahuja.groceryapp.adapters.GroceryAdapter;
import com.mridulahuja.groceryapp.models.Grocery;
import com.mridulahuja.groceryapp.tools.HeightResizeAnimation;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerLinearLayout;

    private List<Grocery> groceryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroceryAdapter mAdapter;

    private DisplayMetrics displayMetrics;
    private int screenHeight;
    private int screenWidth;


    private RelativeLayout layoutPricebar;
    private TextView lblItemCount;
    private TextView lblTotalPrice;

    private int totalItemsSelected = 0;
    private float totalItemsSelectedPrice = 0;

    private Boolean isPricebarVisible = false;
    private int pricebarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        /*
        * setting handlers
        */
        containerLinearLayout = (LinearLayout) findViewById(R.id.layoutMainContainer);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutPricebar = (RelativeLayout) findViewById(R.id.layoutPricebar);
        lblItemCount = (TextView) findViewById(R.id.lblItemCount);
        lblTotalPrice = (TextView) findViewById(R.id.lblTotalPrice);


        ViewTreeObserver pricebarvto = layoutPricebar.getViewTreeObserver();
        pricebarvto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layoutPricebar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layoutPricebar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                //int width  = layout.getMeasuredWidth();
                pricebarHeight = layoutPricebar.getMeasuredHeight();
            }
        });


        mAdapter = new GroceryAdapter(groceryList, new GroceryAdapter.GroceryAdapterListener() {
            @Override
            public void minusButtonOnClickListener(View v, int position) {
                LinearLayout layout = (LinearLayout) v.getParent();
                TextView txtQuantity = (TextView) layout.findViewById(R.id.txtQuantity);
                TextView txtPrice = (TextView) layout.findViewById(R.id.txtPrice);
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                float unitPrice = Float.parseFloat(txtPrice.getText().toString().replace("\u20B9", ""));
                if(quantity>0) {
                    quantity -= 1;
                    totalItemsSelected -= 1;
                    totalItemsSelectedPrice -= unitPrice;
                }

                txtQuantity.setText(quantity+"");
                showHidePricebar();
            }

            @Override
            public void plusButtonOnClickListener(View v, int position) {
                LinearLayout layout = (LinearLayout) v.getParent();
                TextView txtQuantity = (TextView) layout.findViewById(R.id.txtQuantity);
                TextView txtPrice = (TextView) layout.findViewById(R.id.txtPrice);
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                float unitPrice = Float.parseFloat(txtPrice.getText().toString().replace("\u20B9", ""));
                quantity += 1;
                totalItemsSelected += 1;
                totalItemsSelectedPrice += unitPrice;

                txtQuantity.setText(quantity+"");
                showHidePricebar();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        populateGroceries();


        File f = new File(android.os.Environment.getExternalStorageDirectory(),File.separator+"GroceryApp/imgCache/");
        if(!f.exists()) {
            f.mkdirs();
        }

    }


    private void populateGroceries() {
        groceryList.clear();

        Grocery grocery = new Grocery("Watermelon", 80f, "1 pc", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6N0gBY5jRVaOa7m88OEzt-L8SyfxEFGp6ZuKTZJDwBwOqCXCU");
        groceryList.add(grocery);

        grocery = new Grocery("Apple", 60f, "1 Kg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0hwr8auNvnc7yjv27PIll4300ukdk6lehKNjgt_J3vRhe0RD3");
        groceryList.add(grocery);

        grocery = new Grocery("Banana", 30f, "1 Kg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjo6oNScWxGGHMpMoJvxo1RS7Eu_sXiz3Pgl4ZfBIoTwurMi-h");
        groceryList.add(grocery);

        grocery = new Grocery("Bread", 30f, "1 Kg", "http://www.freepngimg.com/thumb/bread/13-bread-png-image-thumb.png");
        groceryList.add(grocery);


        grocery = new Grocery("Watermelon", 80f, "1 pc", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6N0gBY5jRVaOa7m88OEzt-L8SyfxEFGp6ZuKTZJDwBwOqCXCU");
        groceryList.add(grocery);

        grocery = new Grocery("Apple", 60f, "1 Kg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0hwr8auNvnc7yjv27PIll4300ukdk6lehKNjgt_J3vRhe0RD3");
        groceryList.add(grocery);

        grocery = new Grocery("Banana", 30f, "1 Kg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjo6oNScWxGGHMpMoJvxo1RS7Eu_sXiz3Pgl4ZfBIoTwurMi-h");
        groceryList.add(grocery);

        grocery = new Grocery("Bread", 30f, "1 Kg", "http://www.freepngimg.com/thumb/bread/13-bread-png-image-thumb.png");
        groceryList.add(grocery);

        grocery = new Grocery("Watermelon", 80f, "1 pc", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6N0gBY5jRVaOa7m88OEzt-L8SyfxEFGp6ZuKTZJDwBwOqCXCU");
        groceryList.add(grocery);

        grocery = new Grocery("Apple", 60f, "1 Kg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0hwr8auNvnc7yjv27PIll4300ukdk6lehKNjgt_J3vRhe0RD3");
        groceryList.add(grocery);

        grocery = new Grocery("Banana", 30f, "1 Kg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjo6oNScWxGGHMpMoJvxo1RS7Eu_sXiz3Pgl4ZfBIoTwurMi-h");
        groceryList.add(grocery);

        grocery = new Grocery("Bread", 30f, "1 Kg", "http://www.freepngimg.com/thumb/bread/13-bread-png-image-thumb.png");
        groceryList.add(grocery);

        mAdapter.notifyDataSetChanged();
    }


    private void showHidePricebar() {
        if(totalItemsSelected>0) {
            String quantity = totalItemsSelected + " items";
            String price = "\u20B9"+Math.round(totalItemsSelectedPrice);
            lblItemCount.setText(quantity);
            lblTotalPrice.setText(price);

            if(!isPricebarVisible) {
                isPricebarVisible = true;

                layoutPricebar.setVisibility(View.VISIBLE);
            }

        }
        else {
            if(isPricebarVisible) {
                layoutPricebar.setVisibility(View.GONE);
                isPricebarVisible = false;
            }

        }
    }
}
