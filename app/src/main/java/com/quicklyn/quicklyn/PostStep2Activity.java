package com.quicklyn.quicklyn;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class PostStep2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private EditText mLocation;
    private RadioGroup mTypeGroup;
    private EditText mOtherText;
    private EditText mPostDate;
    private Button mContinueButton;

    private DatePickerDialog mDatePickerDialog;

    private static final int PLACE_PICKER_REQUEST = 99;


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_step2);

        mLocation = (EditText) findViewById(R.id.post_location);
        mTypeGroup = (RadioGroup) findViewById(R.id.type_group);
        mOtherText = (EditText) findViewById(R.id.other_text);
        mPostDate = (EditText) findViewById(R.id.post_date);
        mContinueButton = (Button) findViewById(R.id.post2_button_continue);


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

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(PostStep2Activity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        mTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.other_radio){
                    mOtherText.setVisibility(View.VISIBLE);
                }else{
                    mOtherText.setVisibility(View.GONE);
                    mOtherText.setText("");
                }
            }
        });

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    Intent intent = new Intent(PostStep2Activity.this, PostStep3Activity.class);
                    intent.putExtras(getIntent());
                    PostStep3Activity.setLocation(mLocation.getText().toString(), intent);
                    PostStep3Activity.setDate(mPostDate.getText().toString(), intent);

                    String placeType;
                    if (mTypeGroup.getCheckedRadioButtonId() == R.id.other_radio){
                        placeType = mOtherText.getText().toString();
                    }else{
                        placeType = ((RadioButton)findViewById(mTypeGroup.getCheckedRadioButtonId())).getText().toString();
                    }
                    PostStep3Activity.setPlaceType(placeType, intent);

                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateData() {
        if(mLocation.getText().toString().trim().equals("")){
            Toast.makeText(PostStep2Activity.this, R.string.invalid_location, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(mTypeGroup.getCheckedRadioButtonId() == RadioGroup.NO_ID){
            Toast.makeText(PostStep2Activity.this, R.string.invalid_place_type, Toast.LENGTH_SHORT).show();
            return false;
        }else if(mTypeGroup.getCheckedRadioButtonId() == R.id.other_radio && mOtherText.getText().toString().trim().equals("")){
            Toast.makeText(PostStep2Activity.this, R.string.invalid_place_other, Toast.LENGTH_SHORT).show();
            return false;
        }


        try {
            if(mPostDate.getText().toString().equals("") ||
                    dateFormat.parse(mPostDate.getText().toString()).getTime() < System.currentTimeMillis()){
                Toast.makeText(PostStep2Activity.this, R.string.invalid_date, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (ParseException e) {
            Toast.makeText(PostStep2Activity.this, R.string.invalid_date, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                mLocation.setText(place!=null ? place.getAddress() : "");
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        mPostDate.setText(dateFormat.format(cal.getTime()));
    }
}
