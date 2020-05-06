package com.example.td3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://raw.githubusercontent.com/kalash94/Mobile_Programming_project/API_Rest/app/src/main/java/com/example/td3/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       makeApiCall();
    }

    private void showList(List<FinalFantasy> FinalFantasyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        mAdapter = new ListAdapter(FinalFantasyList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FinalFantasyApi FinalFantasyApi = retrofit.create(FinalFantasyApi.class);

        Call<RestFinalFantasyResponse> call = FinalFantasyApi.getFinalFantasyResponse();
        call.enqueue(new Callback<RestFinalFantasyResponse>() {
            @Override
            public void onResponse(Call<RestFinalFantasyResponse> call, Response<RestFinalFantasyResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<FinalFantasy> FinalFantasyList = response.body().getResults();
                    showList(FinalFantasyList);
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestFinalFantasyResponse> call, Throwable t) {
                showError();
            }
        });


    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
