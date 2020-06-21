package com.example.td3.presentation.controller;

import android.content.SharedPreferences;

import com.example.td3.Constants;
import com.example.td3.Singletons;
import com.example.td3.presentation.model.FinalFantasy;
import com.example.td3.presentation.model.RestFinalFantasyResponse;
import com.example.td3.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){

        List<FinalFantasy> FinalFantasyList = getDataFromCache();
        if (FinalFantasyList != null)
        {
            view.showList(FinalFantasyList);
        }else {
            makeApiCall();
        }

    }

    private void makeApiCall(){

        Call<RestFinalFantasyResponse> call = Singletons.getFinalFantasyApi().getFinalFantasyResponse();
        call.enqueue(new Callback<RestFinalFantasyResponse>() {
            @Override
            public void onResponse(Call<RestFinalFantasyResponse> call, Response<RestFinalFantasyResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<FinalFantasy> FinalFantasyList = response.body().getResults();
                    saveList(FinalFantasyList);
                    view.showList(FinalFantasyList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestFinalFantasyResponse> call, Throwable t) {
                view.showError();
            }
        });


    }

    private void saveList(List<FinalFantasy> finalFantasyList) {
        String jsonString = gson.toJson(finalFantasyList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_FINAL_FANTASY_LIST, jsonString)
                .apply();

    }

    private List<FinalFantasy> getDataFromCache(){
        String jsonFinalFantasy = sharedPreferences.getString(Constants.KEY_FINAL_FANTASY_LIST, null);

        if(jsonFinalFantasy == null){
            return null;
        }else {

            Type listType = new TypeToken<List<FinalFantasy>>() {
            }.getType();
            return gson.fromJson(jsonFinalFantasy, listType);
        }
    }


    public void onItemClick(FinalFantasy finalFantasy){
        view.navigateUpToDetails(finalFantasy);
    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
