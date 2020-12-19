package com.example.angkoters.rest;

import com.example.angkoters.model.RootAngkotModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("ruteangkot") //endpoint
    Call<RootAngkotModel> getData();
}