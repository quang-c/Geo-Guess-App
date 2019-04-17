package com.example.geoguessswipe;

public class GeoObject {

    private String mGeoName;
    private int mGeoImageName;
    private boolean isEurope;

    public GeoObject(String mGeoName, int mGeoImageName, boolean isEurope) {

        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
        this.isEurope = isEurope;
    }

    public String getmGeoName() {
        return mGeoName;
    }

    public int getmGeoImageName() {
        return mGeoImageName;
    }

    public boolean isEurope() {
        return isEurope;
    }

    public static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
            "Denmark",
            "Canada",
            "Bangladesh",
            "Kazachstan",
            "Colombia",
            "Poland",
            "Malta",
            "Thailand"
    };
    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {

            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };

    public static final boolean[] PRE_DEFINED_IN_EUROPE= {

            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false
    };
}