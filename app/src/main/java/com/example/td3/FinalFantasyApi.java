package com.example.td3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinalFantasyApi {

    @GET("FinalFantasyApi.json")
    Call<RestFinalFantasyResponse> getFinalFantasyResponse();
}
