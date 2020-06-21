package com.example.td3.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FinalFantasy implements Parcelable {
    private String name;
    private String annee;
    private String plateforme;
    private String ventes;
    private String ImageUrl;
    private String NumeroDeTop;
    private String Description;

    protected FinalFantasy(Parcel in) {
        name = in.readString();
        annee = in.readString();
        plateforme = in.readString();
        ventes = in.readString();
        ImageUrl = in.readString();
        NumeroDeTop = in.readString();
        Description = in.readString();
    }

    public static final Creator<FinalFantasy> CREATOR = new Creator<FinalFantasy>() {
        @Override
        public FinalFantasy createFromParcel(Parcel in) {
            return new FinalFantasy(in);
        }

        @Override
        public FinalFantasy[] newArray(int size) {
            return new FinalFantasy[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAnnee() {
        return annee;
    }

    public String getPlateforme() { return plateforme;}

    public String getVentes() {
        return ventes;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getNumeroDeTop() {
        return NumeroDeTop;
    }

    public String getDescription(){return Description;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(annee);
        dest.writeString(plateforme);
        dest.writeString(ventes);
        dest.writeString(ImageUrl);
        dest.writeString(NumeroDeTop);
        dest.writeString(Description);
    }
}
