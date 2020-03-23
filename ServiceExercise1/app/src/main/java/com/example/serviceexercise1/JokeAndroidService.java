package com.example.serviceexercise1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.Html;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeAndroidService  extends Service {

    private final String url = "https://api.icndb.com/";
    private long jokeCounter;
    private String newestJoke;
    private volatile boolean running;

    //Retrofit stuff
    private Retrofit retrofit;
    private JokeWebService jokeWebService;

    //Thread Stuff
    private Thread workerThread;

    //Binder given to clients
    private final IBinder mBinder = new JokeAndroidServiceBinder();


    /**
     * Class used for the client Binder. Because we know this service always
     * runs in the same process as its clients, we dont need to deal with IPC
     */
    public class JokeAndroidServiceBinder extends Binder {
        JokeAndroidService getService() {
            // Return this instance of JokeAndroidService so clients can call public methods
            return JokeAndroidService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        jokeCounter = 0;
        running = true;

        //Instantiates Retrofrit
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create retrofit instance of JokeService
        jokeWebService = retrofit.create(JokeWebService.class);

        //Create a thread
        workerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                //Make sure the thread is still supposed to run.
                while (running) {
                    //Use jokeservice to get a joke
                    Call<Joke> joke = jokeWebService.randomJoke();
                    try {
                        //Convert joke to string, formatting it, avoiding html escape character
                        String joke_txt = Html.fromHtml(joke.execute().body().getValue().getJoke()).toString();
                        newestJoke = joke_txt;
                        jokeCounter++;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Have thread sleep for 10 seconds (10.000 ms)
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //start the thread
        workerThread.start();
    }

    public String getNewestJoke() {
        return newestJoke;
    }

    public long getJokeCounter(){
        return jokeCounter;
    }

    @Override
    public void onDestroy() {
        // Stop running the thread
        running = false;
        super.onDestroy();
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("service_exercise",
                "JokeAndroidService onDestroy - Current Thread ID-"
                        + Thread.currentThread().getId()
                        + " for thread"
                        + Thread.currentThread().getName());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


}
