package com.quicklyn.quicklyn;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends Activity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mButtonSignIn;
    private TextView mLoginMessage;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialization
        mUsername = (EditText) findViewById(R.id.edit_username);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mButtonSignIn = (Button) findViewById(R.id.button_login);
        mLoginMessage = (TextView) findViewById(R.id.login_message);

        mButtonSignIn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                mDatabase = FirebaseDatabase.getInstance().getReference();

                // Validates the User name and Password for admin, admin
                if (mUsername.getText().toString().equals("admin") && mPassword.getText().toString().equals("admin")) {
                    mLoginMessage.setText(R.string.login_successful);
                } else {
                    mLoginMessage.setText(R.string.error_login_fail);
                }
            }
        });
    }

}











