package com.example.randompicturesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        recyclerView.setHasFixedSize(true);

        //Gridlayout creates two columns
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        //create adapter
        RecyclerView.Adapter adapter = new ImageAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
