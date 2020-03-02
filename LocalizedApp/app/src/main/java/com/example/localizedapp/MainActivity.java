package com.example.localizedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int[] images = {R.drawable.beard_gopher, R.drawable.gopher};
    private ImageView image;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        image.setImageResource(images[index]);

    }

    public void nextButtonOnClick(View view){
        index++;
        if(index >= images.length) {
            index = 0;
        }

        image.setImageResource(images[index]);
    }

    public void prevButtonClick(View view) {
        index--;
        if(index < 0) {
            index = images.length - 1;
        }

        image.setImageResource(images[index]);
    }
}
