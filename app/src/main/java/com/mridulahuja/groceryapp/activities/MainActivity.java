package com.mridulahuja.groceryapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mridulahuja.groceryapp.R;
import com.mridulahuja.groceryapp.adapters.GroceryAdapter;
import com.mridulahuja.groceryapp.models.Grocery;

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

        mAdapter = new GroceryAdapter(groceryList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        populateGroceries();
    }


    private void populateGroceries() {
        groceryList.clear();

        Grocery grocery = new Grocery("Watermelon", 80f, "1 pc", "");
        groceryList.add(grocery);

        grocery = new Grocery("Apple", 60f, "1 Kg", "");
        groceryList.add(grocery);

        grocery = new Grocery("Banana", 30f, "1 Kg", "");
        groceryList.add(grocery);



        mAdapter.notifyDataSetChanged();
    }
}
