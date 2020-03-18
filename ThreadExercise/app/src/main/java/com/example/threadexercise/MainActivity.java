package com.example.threadexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextSwitcher;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextSwitcher textSwitcher;
    Thread workThread;

    //Semaphore
    volatile boolean running = true;

    public static String random(){
        Random generator = new Random();
        StringBuilder rStringBuilder = new StringBuilder();

        int randLength = generator.nextInt(100);
        char tempChar;

        for (int i = 0; i < randLength ; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            rStringBuilder.append(tempChar);
        }
        return rStringBuilder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fiend textswitcher view
        textSwitcher = findViewById(R.id.joke_holder);

        //Declare the standard animations
        textSwitcher.setInAnimation(this, android.R.anim.fade_in);
        textSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        //Create a thread
        workThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running){
                    final String randomString = random();
                    //New runnable that changes text in the textswitcher
                    textSwitcher.post(new Runnable() {
                        @Override
                        public void run() {
                            textSwitcher.setText(randomString);
                        }
                    });

                    //Make the thread sleep for 10 second (10.000 ms)
                    try{
                        Thread.sleep(10000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        workThread.start();
    }

    @Override
    protected void onDestroy(){
        //Stop running the thread
        running = false;
        super.onDestroy();

        try{
            workThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
}

}