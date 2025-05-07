package com.example.lab4.network;

import com.example.lab4.models.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1/search.json")
    Call<List<Location>> getLocationData(@Query("key") String apiKey, @Query("q") String query);

}


