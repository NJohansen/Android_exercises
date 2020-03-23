package com.example.serviceexercise1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class NextActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);
        initViews();
        Log.i("service_exercise",
                "JokeAndroidService onDestroy - Current Thread ID-"
                        + Thread.currentThread().getId()
                        + " for thread"
                        + Thread.currentThread().getName());

        //Declares standard animations for textSwitcher
        textSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        textSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
