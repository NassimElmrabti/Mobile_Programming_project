package com.example.td3.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.td3.Constants;
import com.example.td3.R;
import com.example.td3.Singletons;
import com.example.td3.presentation.model.FinalFantasy;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    private ImageView ImageView;
    private TextView txtYear;
    private TextView txtPlateform;
    private TextView txtNumberSells;
    private TextView txtDescription;





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        txtYear = findViewById(R.id.year_released);
        txtPlateform = findViewById(R.id.platform);
        txtNumberSells = findViewById(R.id.nombre_de_ventes);
        txtDescription = findViewById(R.id.description);



        Intent intent = getIntent();
        String FinalFantasyJson = intent.getStringExtra(Constants.KEY_FINAL_FANTASY_INTENT);
        FinalFantasy finalFantasy = Singletons.getGson().fromJson(FinalFantasyJson, FinalFantasy.class);
        showDetail(finalFantasy);

    }

    private void showDetail(FinalFantasy finalFantasy) {
        txtDetail.setText(finalFantasy.getName());
        Picasso.get().load(finalFantasy.getImageUrl()).resize(400, 500).into((ImageView) findViewById(R.id.logo_game));
        txtYear.setText(finalFantasy.getAnnee());
        txtPlateform.setText(finalFantasy.getPlateforme());
        txtNumberSells.setText(finalFantasy.getVentes());
        txtDescription.setText(finalFantasy.getDescription());
    }
}
