package com.quicklyn.quicklyn;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class PostStep2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText mLocation;
    private RadioGroup mTypeGroup;
    private EditText mOtherText;
    private EditText mPostDate;

    private DatePickerDialog mDatePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_step2);

        mLocation = (EditText) findViewById(R.id.post_location);
        mTypeGroup = (RadioGroup) findViewById(R.id.type_group);
        mOtherText = (EditText) findViewById(R.id.other_text);
        mPostDate = (EditText) findViewById(R.id.post_date);


        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        mDatePickerDialog = new DatePickerDialog(
                PostStep2Activity.this, PostStep2Activity.this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        mPostDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
