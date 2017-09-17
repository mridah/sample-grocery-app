package com.mridulahuja.groceryapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mridulahuja.groceryapp.R;
import com.mridulahuja.groceryapp.adapters.GroceryAdapter;
import com.mridulahuja.groceryapp.models.Grocery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Grocery> groceryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GroceryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new GroceryAdapter(groceryList, new GroceryAdapter.GroceryAdapterListener() {
            @Override
            public void minusButtonOnClickListener(View v, int position) {
                LinearLayout layout = (LinearLayout) v.getParent();
                TextView txtQuantity = (TextView) layout.findViewById(R.id.txtQuantity);
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                if(quantity>0)
                quantity -= 1;

                txtQuantity.setText(quantity+"");
            }

            @Override
            public void plusButtonOnClickListener(View v, int position) {
                LinearLayout layout = (LinearLayout) v.getParent();
                TextView txtQuantity = (TextView) layout.findViewById(R.id.txtQuantity);
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                quantity += 1;

                txtQuantity.setText(quantity+"");

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






        mAdapter.notifyDataSetChanged();
    }
}
