package com.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        recyclerView.setHasFixedSize(true);

        //Gridlayout creates two columns
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        //generate random numbers
        random = new Random();
        ArrayList<Integer> numbers = generateNumbers(200);

        //create adapter
        RecyclerView.Adapter adapter = new MyAdapter(numbers);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Integer> generateNumbers(int amount){
        ArrayList<Integer> temporary = new ArrayList<>();
        for (int i =0; i < amount; i++){
            temporary.add(random.nextInt(10000));
        }
        return temporary;
    }
}
