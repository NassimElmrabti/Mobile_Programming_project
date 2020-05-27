package com.example.td3;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewActivity extends AppCompatActivity {

    private static final String TAG = "NewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        if(getIntent().hasExtra("selected_FF")) {
            FinalFantasy FF = getIntent().getParcelableExtra("selected_FF");
            displayGame(FF);
            //Spanable string

        }
    }

    public void displayGame(FinalFantasy FF_to_Display) {
        setTitle(FF_to_Display.getName());
        Picasso.get().load(FF_to_Display.getImageUrl()).resize(300, 400).into((ImageView) findViewById(R.id.imageView3));
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(FF_to_Display.getVentes());
    }

}
