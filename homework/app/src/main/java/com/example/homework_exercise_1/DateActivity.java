package com.example.homework_exercise_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateActivity extends BaseActivity {

    public DatePicker dateOfBirthPicker;
    public String name;
    public String adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        dateOfBirthPicker = (DatePicker) findViewById(R.id.dateOfBirthPicker);

        dateOfBirthPicker.updateDate(1994, 8, 6);


        ( findViewById(R.id.nextButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity();
            }
        });

        if(currentUser.dateOfBirth != 0){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(currentUser.dateOfBirth * 1000);
            dateOfBirthPicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
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

    private void goToNextActivity() {
        Intent intent = getIntent();
        name = intent.getStringExtra(Constants.NAME_KEY);
        adress = intent.getStringExtra(Constants.ADRESS_KEY);

        intent.setClass(this, SummaryActivity.class);
        intent.putExtra(Constants.ADRESS_KEY, adress);
        intent.putExtra(Constants.NAME_KEY, name);

        int day = dateOfBirthPicker.getDayOfMonth();
        int month = dateOfBirthPicker.getMonth();
        int year = dateOfBirthPicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(calendar.getTime());
        intent.putExtra(Constants.DATE_KEY, formatedDate);

        currentUser.dateOfBirth = calendar.getTimeInMillis() / 1000;
        db.userDAO().update(currentUser);
        startActivity(intent);
    }
}
