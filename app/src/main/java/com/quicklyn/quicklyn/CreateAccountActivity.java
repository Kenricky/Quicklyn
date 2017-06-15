package com.quicklyn.quicklyn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mButtonSignIn;
    private DatabaseReference mDatabase;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setTitle(R.string.create_account);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mName = (EditText) findViewById(R.id.edit_name);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password);
        mButtonSignIn = (Button) findViewById(R.id.button_crete_account);


        mButtonSignIn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(validateForm()){
                    User user = new User();
                    user.setName(mName.getText().toString());
                    user.setEmail(mEmail.getText().toString());
                    user.setPassword(mPassword.getText().toString());

                    mDatabase.child("users").push().setValue(user);
                    Toast.makeText(CreateAccountActivity.this, R.string.account_created, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private boolean validateForm() {
        if (mName.getText().toString().equals("")) {
            Toast.makeText(CreateAccountActivity.this, R.string.error_name_required, Toast.LENGTH_LONG).show();
            return false;
        }
        if (mEmail.getText().toString().equals("") || !mEmail.getText().toString().contains("@")) {
            Toast.makeText(CreateAccountActivity.this, R.string.error_email_required, Toast.LENGTH_LONG).show();
            return false;
        }
        if (mPassword.getText().toString().equals("")) {
            Toast.makeText(CreateAccountActivity.this, R.string.error_password_required, Toast.LENGTH_LONG).show();
            return false;
        }
        for(int i=0; i<mPassword.getText().toString().length(); i++){
            if(isNumeric(String.valueOf(mPassword.getText().toString().charAt(i)))){
                break;
            }
            Toast.makeText(CreateAccountActivity.this, R.string.error_password_has_no_number, Toast.LENGTH_LONG).show();
        }
        if (!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            Toast.makeText(CreateAccountActivity.this, R.string.error_password_match, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isNumeric(String x){
        try{
            Double.parseDouble(x);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
