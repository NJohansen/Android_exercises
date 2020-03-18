package com.example.threadexercise2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeService {
    @GET("jokes/random")
    Call<Joke> randomJoke();
}
