package com.example.td3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    private static final String TAG = "NewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra("selected_FF")) {
            FinalFantasy FF = getIntent().getParcelableExtra("selected_FF");
            Log.d(TAG, "onCreate: ");
        }
    }
}
