package com.quicklyn.quicklyn;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mButtonLogin;
    private Button mButtonCreateAccount;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.edit_username);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonCreateAccount = (Button) findViewById(R.id.button_crete_account);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mButtonLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                if(validateForm()) {

                    Query query = mDatabase.child("users")
                            .orderByChild("email")
                            .equalTo(mUsername.getText().toString());

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            for (DataSnapshot user: snapshot.getChildren()) {
                                if (user.child("password").getValue().equals(mPassword.getText().toString())){
                                    startActivity(new Intent(LoginActivity.this, PostStep1Activity.class));
                                    break;
                                }
                            }
                            Toast.makeText(LoginActivity.this, R.string.error_invalid_credentials, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(LoginActivity.this, R.string.error_invalid_credentials, Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

        mButtonCreateAccount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });
    }

    private boolean validateForm() {
        if (mUsername.getText().toString().equals("") || !mUsername.getText().toString().contains("@")) {
            Toast.makeText(LoginActivity.this, R.string.error_email_required, Toast.LENGTH_LONG).show();
            return false;
        }
        if (mPassword.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, R.string.error_password_required, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}











