package com.example.td3;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.td3.data.FinalFantasyApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static FinalFantasyApi finalFantasyApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static FinalFantasyApi getFinalFantasyApi(){
        if(finalFantasyApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            finalFantasyApiInstance = retrofit.create(FinalFantasyApi.class);
        }
        return finalFantasyApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
