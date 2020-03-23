package com.example.serviceexercise1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    TextSwitcher textSwitcher;
    TextView textViewCounter;

    protected JokeAndroidService jokeAndroidService;
    protected boolean mBound;

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("service_exercise",
                    "JokeAndroidService onDestroy - Current Thread ID-"
                            + Thread.currentThread().getId()
                            + " for thread"
                            + Thread.currentThread().getName());

            // We've bound to JokeAndroidService, cast the IBinder and get JokeAndroidService instance
            JokeAndroidService.JokeAndroidServiceBinder binder = (JokeAndroidService.JokeAndroidServiceBinder) service;
            jokeAndroidService = binder.getService();
            mBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, JokeAndroidService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void initViews(){
        textSwitcher = findViewById(R.id.joke_holder);
        textViewCounter = findViewById(R.id.tvJokeCounter);

        findViewById(R.id.btnUpdateCounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    textViewCounter.setText(String.valueOf(jokeAndroidService.getJokeCounter()));
                }
            }
        });

        findViewById(R.id.btnUpdateJoke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    textSwitcher.setText(jokeAndroidService.getNewestJoke());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
