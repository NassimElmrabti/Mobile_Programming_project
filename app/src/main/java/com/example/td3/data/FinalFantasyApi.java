package com.example.td3.data;

import com.example.td3.presentation.model.RestFinalFantasyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FinalFantasyApi {

    @GET("FinalFantasyApi.json")
    Call<RestFinalFantasyResponse> getFinalFantasyResponse();
}
