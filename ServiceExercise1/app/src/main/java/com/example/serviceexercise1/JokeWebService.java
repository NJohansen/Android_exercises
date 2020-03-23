package com.example.serviceexercise1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeWebService {
    @GET("jokes/random")
    Call<Joke> randomJoke();
}
