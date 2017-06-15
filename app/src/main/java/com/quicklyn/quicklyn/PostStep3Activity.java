package com.quicklyn.quicklyn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostStep3Activity extends AppCompatActivity {

    private TextView mPostTitle;
    private TextView mPostLocation;
    private TextView mPostPrice;
    private TextView mPostDescription;
    private TextView mPostDate;
    private Button mButtonCreatePost;

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
        setTitle(R.string.title_new_post);

        mPostTitle = (TextView) findViewById(R.id.post_title);
        mPostLocation = (TextView) findViewById(R.id.post_location);
        mPostPrice = (TextView) findViewById(R.id.post_price);
        mPostDescription = (TextView) findViewById(R.id.post_description);
        mPostDate = (TextView) findViewById(R.id.post_date);
        mButtonCreatePost = (Button) findViewById(R.id.button_create_post);

        mPostTitle.setText(getIntent().getStringExtra(TITLE));
        mPostLocation.setText(getIntent().getStringExtra(LOCATION));
        mPostPrice.setText(String.valueOf(getIntent().getDoubleExtra(PRICE, 0)));
        mPostDescription.setText(getIntent().getStringExtra(DESCRIPTION));
        mPostDate.setText(getIntent().getStringExtra(DATE));

        mButtonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.setTitle(mPostTitle.getText().toString());
                post.setLocation(mPostLocation.getText().toString());
                post.setPrice(new Double(mPostPrice.getText().toString()));
                post.setDescription(mPostDescription.getText().toString());
                post.setDateCreated(System.currentTimeMillis());
                post.setDate(mPostDate.getText().toString());



                Toast.makeText(PostStep3Activity.this, "Data inserted", Toast.LENGTH_SHORT ).show();

            }
        });
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
