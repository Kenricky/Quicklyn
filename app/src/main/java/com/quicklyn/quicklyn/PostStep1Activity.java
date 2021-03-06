package com.quicklyn.quicklyn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostStep1Activity extends AppCompatActivity {

    private Button mButtonContinue;
    private EditText mPostTitle;
    private EditText mPostDescription;
    private EditText mPostPrice;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_step1);
        setTitle(R.string.title_new_post);


        mButtonContinue = (Button) findViewById(R.id.post1_button_continue);
        mPostTitle = (EditText) findViewById(R.id.title_text);
        mPostDescription = (EditText) findViewById(R.id.description_text);
        mPostPrice = (EditText) findViewById(R.id.price_text);

        mButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    Intent intent = new Intent(PostStep1Activity.this, PostStep2Activity.class);
                    PostStep3Activity.setTitle(mPostTitle.getText().toString(), intent);
                    PostStep3Activity.setDescription(mPostDescription.getText().toString(), intent);
                    PostStep3Activity.setPrice(Double.valueOf(mPostPrice.getText().toString()), intent);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm() {
        if(mPostTitle.getText().toString().equals("") || mPostTitle.getText().length() < 5){
            Toast.makeText(PostStep1Activity.this, R.string.invalid_title, Toast.LENGTH_SHORT).show();
            mPostTitle.requestFocus();
            return false;
        }
        if(mPostDescription.getText().toString().trim().equals("") || mPostDescription.getText().length() < 5){
            Toast.makeText(PostStep1Activity.this, R.string.invalid_description, Toast.LENGTH_SHORT).show();
            mPostDescription.requestFocus();
            return false;
        }
        if(mPostPrice.getText().toString().equals("") || new Double(mPostPrice.getText().toString()) <= 0){
            Toast.makeText(PostStep1Activity.this, R.string.invalid_price, Toast.LENGTH_SHORT).show();
            mPostPrice.requestFocus();
            return false;
        }
        return true;
    }

}
