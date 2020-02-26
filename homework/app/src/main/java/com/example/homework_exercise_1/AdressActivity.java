package com.example.homework_exercise_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdressActivity extends AppCompatActivity {

    private EditText getAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        getAdress = findViewById(R.id.getAdress);

        ((Button) findViewById(R.id.nextButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

        ((Button) findViewById(R.id.previousButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrevious();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Constants.TAG, "SummaryActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Constants.TAG, "SummaryActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(Constants.TAG, "SummaryActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Constants.TAG, "SummaryActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Constants.TAG, "SummaryActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Constants.TAG, "SummaryActivity onDestroy");
    }

    private void goToNextActivity(){
        if(!TextUtils.isEmpty(getAdress.getText())) {

            //get the intent from first
            Intent i = getIntent();
            i.setClass(this, DateActivity.class);

            i.putExtra(Constants.ADRESS_KEY, getAdress.getText().toString());
            startActivity(i);
        }
    }

    private void goPrevious(){
        Intent i = getIntent();
        i.setClass(this, MainActivity.class);

        startActivity(i);
    }
}
