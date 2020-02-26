package com.example.homework_exercise_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    TextView adressText;
    TextView nameText;
    TextView dateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();

        adressText = findViewById(R.id.adressText);
        nameText = findViewById(R.id.nameText);
        dateText = findViewById(R.id.dateText);

        adressText.setText(intent.getStringExtra(Constants.ADRESS_KEY));
        nameText.setText(intent.getStringExtra(Constants.NAME_KEY));
        dateText.setText(intent.getStringExtra(Constants.DATE_KEY));
    }
}
