package com.quicklyn.quicklyn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostStep3Activity extends AppCompatActivity {

    private TextView mPostTitle;

    private static final String TITLE = "title_data";
    private static final String DESCRIPTION = "description_data";
    private static final String PRICE = "price_data";
    private static final String LOCATION = "location_data";
    private static final String PLACE_TYPE = "place_type_data";
    private static final String DATE = "date_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_step3);
    }

    public static void setTitle(String title, Intent intent){
        intent.putExtra(TITLE, title);
    }
    public static void setDescription(String description, Intent intent){
        intent.putExtra(DESCRIPTION, description);
    }
    public static void setPrice(double price, Intent intent){
        intent.putExtra(PRICE, price);
    }
    public static void setLocation(String location, Intent intent){
        intent.putExtra(LOCATION, location);
    }
    public static void setPlaceType(String placeType, Intent intent){
        intent.putExtra(PLACE_TYPE, placeType);
    }
    public static void setDate(String date, Intent intent){
        intent.putExtra(DATE, date);
    }
}
